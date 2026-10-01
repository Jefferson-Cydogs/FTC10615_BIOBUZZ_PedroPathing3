
package org.firstinspires.ftc.teamcode.cydogs.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.cydogs.turret.Turret;
import org.firstinspires.ftc.teamcode.cydogs.vision.TagSight;

/**
 * Autonomous: find an AprilTag and keep the turret pointed at it.
 * Camera is mounted ON the turret, so when we turn the servo,
 * the camera turns too. That means:
 *   - if the tag is left of center, turn the turret left
 *   - if the tag is right of center, turn the turret right
 * If no tag is visible, the turret sweeps back and forth
 * between 0° and 270° until it finds one.
 * Robot config names must match:
 *   Servo  → "turret"
 *   Webcam → "Webcam 1"
 */
@Autonomous(name = "Turret Track", group = "cydogs")
public class TurretTrackAuto extends LinearOpMode {

    // ----- names in the Robot Controller configuration -----
    private static final String SERVO_NAME = "turret_servo";
    private static final String WEBCAM_NAME = "Webcam 1";

    // If |bearing| is smaller than this, we count as "locked on" (degrees)
    private static final double LOCK_TOLERANCE_DEG = 3.0;

    // How strongly we turn toward the tag (bigger = faster, may overshoot)
    private static final double TRACK_GAIN = 0.15;

    // How many degrees to move each loop while searching
    private static final double SEARCH_STEP_DEG = 2.0;

    // Camera helper
    private TagSight eyes;

    // Turret helper (0° to 270°)
    private Turret turret;

    // +1 = searching toward max, -1 = searching toward min
    private double searchDirection = 1.0;

    @Override
    public void runOpMode() {
        eyes = new TagSight(hardwareMap, WEBCAM_NAME);
        turret = new Turret(this, SERVO_NAME);
        turret.initialize();

        telemetry.addLine("Turret Track ready");
        telemetry.addLine("Servo: " + SERVO_NAME);
        telemetry.addLine("Camera: " + WEBCAM_NAME);
        telemetry.update();
        waitForStart();

        while (opModeIsActive()) {
            // Always update the camera first
            eyes.update();

            if (eyes.hasTarget) {
                trackTag();
            } else {
                searchForTag();
            }

            telemetry.addData("hasTarget", eyes.hasTarget);
            telemetry.addData("tagId", eyes.tagId);
            telemetry.addData("bearing (deg)", "%.1f", eyes.bearingDegrees);
            telemetry.addData("turret (deg)", "%.1f", turret.getAngleDegrees());
            telemetry.addData("mode", eyes.hasTarget ? "TRACK" : "SEARCH");
            telemetry.update();

            // Small pause so the servo and camera can keep up
            sleep(20);
        }

        eyes.close();
    }

    /**
     * We see a tag: turn the turret so the tag moves toward the center.
     * Camera is on the turret, so reducing bearing = aiming at the tag.
     */
    private void trackTag() {
        // Close enough — hold still
        if (Math.abs(eyes.bearingDegrees) <= LOCK_TOLERANCE_DEG) {
            return;
        }

        // Nudge toward the tag (bearing is in degrees)
        double nudge = eyes.bearingDegrees * TRACK_GAIN;
        turret.nudgeByDegrees(nudge);
    }

    /**
     * No tag: slowly sweep left and right.
     * When we hit 0° or 270°, reverse direction.
     */
    private void searchForTag() {
        if (turret.isAtMax()) {
            searchDirection = -1.0; // go back toward 0°
        } else if (turret.isAtMin()) {
            searchDirection = 1.0;  // go toward 270°
        }

        turret.nudgeByDegrees(searchDirection * SEARCH_STEP_DEG);
    }
}