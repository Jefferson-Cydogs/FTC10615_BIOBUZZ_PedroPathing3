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
 * TagSight = the robot's "eyes" for AprilTags.
 * What it does:
 *   - Looks through the webcam for AprilTags
 *   - Can look for RED tags, BLUE tags, or BOTH
 *   - Ignores upside-down tags (the down side of a hive)
 * How to use:
 *   1. Create once:  new TagSight(hardwareMap, TagSight.Alliance.RED)
 *   2. Each loop:    eyes.update()
 *   3. Read results: eyes.hasTarget, eyes.bearingDegrees, etc.
 * Webcam must be named "Webcam 1" in the robot configuration.
 */
public class TagSight {

    // ----- Alliance choices (pick one when you create TagSight) -----
    public enum Alliance {
        RED,   // only tags whose name contains "red"
        BLUE,  // only tags whose name contains "blue"
        BOTH   // any tag color is OK
    }

    // Webcam name in the Control Hub config (change here if your name is different)
    private static final String WEBCAM_NAME = "Webcam 1";

    // If |roll| is less than this, we treat the tag as right-side-up
    private static final double UPRIGHT_LIMIT = 90.0;

    // Camera tools from the FTC SDK
    private final AprilTagProcessor processor;
    private final VisionPortal portal;

    // Which alliance we care about
    private final Alliance alliance;

    // ----- Results (read these after update()) -----

    /** True if we found a good tag this frame */
    public boolean hasTarget = false;

    /** Left/right angle to the tag in degrees (0 = centered). Negative = left, positive = right */
    public double bearingDegrees = 0;

    /** Tag id number for a single tag; -1 if it was a cluster */
    public int tagId = -1;

    /** Name the SDK gave this tag (useful for telemetry) */
    public String targetName = "";

    /** True if the tag looked upright (not upside-down) */
    public boolean isUpright = false;

    /** Roll angle in degrees (for debugging) */
    public double rollDegrees = 0;

    /**
     * Create the eyes.
     *
     * @param hardwareMap  from the OpMode (always available as hardwareMap)
     * @param alliance     TagSight.Alliance.RED, BLUE, or BOTH
     */
    public TagSight(HardwareMap hardwareMap, Alliance alliance) {
        this.alliance = alliance;

        // Build the AprilTag detector
        processor = new AprilTagProcessor.Builder().build();

        // Connect detector to the webcam and start the camera
        portal = new VisionPortal.Builder()
                .setCamera(hardwareMap.get(WebcamName.class, WEBCAM_NAME))
                .addProcessor(processor)
                .build();
    }

    /**
     * Look at the newest camera frame and update hasTarget, bearingDegrees, etc.
     * Call this once every loop.
     */
    public void update() {
        // Clear last frame's results
        hasTarget = false;
        tagId = -1;
        bearingDegrees = 0;
        targetName = "";
        isUpright = false;
        rollDegrees = 0;

        // Ask the SDK what tags it sees right now
        List<AprilTagDetection> detections = processor.getDetections();
        if (detections == null || detections.isEmpty()) {
            return; // nothing in view
        }

        // Check each detection until we find one we like
        for (AprilTagDetection detection : detections) {

            // Case 1: a normal single AprilTag
            if (detection instanceof AprilTagSingleDetection) {
                AprilTagSingleDetection single = (AprilTagSingleDetection) detection;

                // Need pose data to know left/right and roll
                if (single.ftcPose == null) {
                    continue;
                }

                // Name from the library, or a fallback
                String name = "id " + single.id;
                if (single.metadata != null && single.metadata.name != null) {
                    name = single.metadata.name;
                }

                // Skip wrong alliance or upside-down tags
                if (!matchesAlliance(name)) {
                    continue;
                }
                if (!isUprightRoll(single.ftcPose.roll)) {
                    continue;
                }

                // This tag is good — save info and stop looking
                hasTarget = true;
                bearingDegrees = single.ftcPose.bearing;
                rollDegrees = single.ftcPose.roll;
                isUpright = true;
                tagId = single.id;
                targetName = name;
                return;
            }

            // Case 2: a BIOBUZZ hive "cluster" (two tags treated as one target)
            if (detection instanceof AprilTagClusterDetection) {
                AprilTagClusterDetection cluster = (AprilTagClusterDetection) detection;

                if (cluster.ftcPose == null) {
                    continue;
                }

                // Prefer shortName, then name, then a default
                String name = "cluster";
                if (cluster.metadata != null) {
                    if (cluster.metadata.shortName != null && !cluster.metadata.shortName.isEmpty()) {
                        name = cluster.metadata.shortName;
                    } else if (cluster.metadata.name != null) {
                        name = cluster.metadata.name;
                    }
                }

                if (!matchesAlliance(name)) {
                    continue;
                }
                if (!isUprightRoll(cluster.ftcPose.roll)) {
                    continue;
                }

                hasTarget = true;
                bearingDegrees = cluster.ftcPose.bearing;
                rollDegrees = cluster.ftcPose.roll;
                isUpright = true;
                tagId = -1; // clusters don't use a single id here
                targetName = name;
                return;
            }
        }
    }

    /**
     * True if this tag name is OK for our alliance setting.
     * RED  → name must include "red"
     * BLUE → name must include "blue"
     * BOTH → always OK
     */
    private boolean matchesAlliance(String name) {
        if (alliance == Alliance.BOTH) {
            return true;
        }
        if (name == null) {
            return false;
        }

        String lower = name.toLowerCase();

        if (alliance == Alliance.RED) {
            return lower.contains("red");
        }
        if (alliance == Alliance.BLUE) {
            return lower.contains("blue");
        }

        return true;
    }

    /**
     * True if the tag is right-side-up enough to count as the scorable face.
     */
    private boolean isUprightRoll(double roll) {
        return Math.abs(roll) < UPRIGHT_LIMIT;
    }

    /**
     * Turn the camera pipeline off when the OpMode ends (optional but tidy).
     */
    public void close() {
        if (portal != null) {
            portal.close();
        }
    }
}