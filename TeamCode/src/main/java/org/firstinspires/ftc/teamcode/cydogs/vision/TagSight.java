package org.firstinspires.ftc.teamcode.cydogs.vision;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.apriltag.AprilTagClusterDetection;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;
import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;
import org.firstinspires.ftc.vision.apriltag.AprilTagSingleDetection;

import com.qualcomm.robotcore.hardware.HardwareMap;

import java.util.List;

/**
 * Looks at the webcam and finds AprilTags.
 * Works with:
 *   - single tags (have an ID number)
 *   - BIOBUZZ clusters (hive cells; no single ID)
 * How to use:
 *   1. Create TagSight once in init
 *   2. Call update() every loop
 *   3. Read hasTarget, bearingDegrees, tagId, targetName
 */
public class TagSight {

    // Finds AprilTags in the camera image
    private final AprilTagProcessor processor;

    // Runs the camera and feeds frames to the processor
    private final VisionPortal portal;

    // True when we currently see a usable target
    public boolean hasTarget = false;

    // Left/right angle to the target (degrees)
    // Negative = target is left of center, positive = right of center
    public double bearingDegrees = 0;

    // Tag number for a single tag; -1 when the target is a cluster
    public int tagId = -1;

    // Name of what we locked onto (helpful for telemetry)
    public String targetName = "";

    /**
     * @param hardwareMap  from the OpMode
     * @param webcamName   must match the name in the Robot Controller config
     *                     (example: "Webcam 1")
     */
    public TagSight(HardwareMap hardwareMap, String webcamName) {
        // Build the AprilTag detector with default settings
        processor = new AprilTagProcessor.Builder().build();

        // Start the webcam and attach the detector
        portal = new VisionPortal.Builder()
                .setCamera(hardwareMap.get(WebcamName.class, webcamName))
                .addProcessor(processor)
                .build();
    }

    /**
     * Look at the newest camera frame and update:
     *   hasTarget, bearingDegrees, tagId, targetName
     * Call this once every loop.
     * SDK 12 note:
     *   getDetections() can return single tags OR clusters.
     *   BIOBUZZ hive tags usually show up as clusters, so we must accept both.
     */
    public void update() {
        hasTarget = false;
        tagId = -1;
        bearingDegrees = 0;
        targetName = "";

        List<AprilTagDetection> detections = processor.getDetections();
        if (detections == null || detections.isEmpty()) {
            return; // nothing in view
        }

        for (AprilTagDetection detection : detections) {

            // ----- Single tag (has an .id number) -----
            if (detection instanceof AprilTagSingleDetection) {
                AprilTagSingleDetection single = (AprilTagSingleDetection) detection;

                // Need pose data so we know left/right angle
                if (single.ftcPose == null) {
                    continue;
                }

                hasTarget = true;
                bearingDegrees = single.ftcPose.bearing;
                tagId = single.id;
                if (single.metadata != null) {
                    targetName = single.metadata.name;
                } else {
                    targetName = "id " + single.id;
                }
                return; // use the first good target
            }

            // ----- Cluster (BIOBUZZ hive cell) -----
            // The camera preview can show a cluster even when there is no single tag.
            if (detection instanceof AprilTagClusterDetection) {
                AprilTagClusterDetection cluster = (AprilTagClusterDetection) detection;

                if (cluster.ftcPose == null) {
                    continue;
                }

                hasTarget = true;
                bearingDegrees = cluster.ftcPose.bearing;
                tagId = -1; // clusters do not have one tag id
                if (cluster.metadata != null) {
                    targetName = cluster.metadata.shortName;
                } else {
                    targetName = "cluster";
                }
                return; // use the first good target
            }
        }
    }

    /** Turn the camera off when the OpMode ends. */
    public void close() {
        if (portal != null) {
            portal.close();
        }
    }
}