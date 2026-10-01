package org.firstinspires.ftc.teamcode.cydogs.vision;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;
import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;
import org.firstinspires.ftc.vision.apriltag.AprilTagSingleDetection;

import com.qualcomm.robotcore.hardware.HardwareMap;

import java.util.List;

/**
 * Looks at the webcam and finds AprilTags.
 *
 * How to use:
 *   1. Create TagSight once in init
 *   2. Call update() every loop
 *   3. Read hasTarget, bearingDegrees, tagId
 */
public class TagSight {

    // The part that finds AprilTags in the picture
    private final AprilTagProcessor processor;

    // The part that runs the camera
    private final VisionPortal portal;

    // True when we see a usable tag right now
    public boolean hasTarget = false;

    // Left/right angle to the tag (degrees)
    // Negative = tag is left of center, positive = tag is right
    public double bearingDegrees = 0;

    // Number printed on the tag (-1 if none)
    public int tagId = -1;

    /**
     * @param hardwareMap  from the OpMode
     * @param webcamName   must match the name in the Robot Controller config
     */
    public TagSight(HardwareMap hardwareMap, String webcamName) {
        processor = new AprilTagProcessor.Builder().build();

        portal = new VisionPortal.Builder()
                .setCamera(hardwareMap.get(WebcamName.class, webcamName))
                .addProcessor(processor)
                .build();
    }

    /**
     * Check the newest camera frame.
     * Call this once every loop.
     */
    public void update() {
        hasTarget = false;
        tagId = -1;
        bearingDegrees = 0;

        List<AprilTagDetection> detections = processor.getDetections();
        if (detections == null || detections.isEmpty()) {
            return;
        }

        // SDK 12+: only a SINGLE tag has an .id field
        for (AprilTagDetection detection : detections) {
            if (!(detection instanceof AprilTagSingleDetection)) {
                continue;
            }

            AprilTagSingleDetection single = (AprilTagSingleDetection) detection;
            if (single.ftcPose == null) {
                continue;
            }

            hasTarget = true;
            bearingDegrees = single.ftcPose.bearing;
            tagId = single.id;
            return; // use the first good tag
        }
    }

    /** Turn the camera off when the OpMode ends. */
    public void close() {
        if (portal != null) {
            portal.close();
        }
    }
}