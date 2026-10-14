package org.firstinspires.ftc.teamcode.cydogs.auton;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.cydogs.components.Turret;
import org.firstinspires.ftc.teamcode.cydogs.vision.TagSight;

/**
 * Bench test: aim the turret at red / upright AprilTags.
 *
 * - If a good tag is visible, center it left/right (TRACK).
 * - If not, sweep the turret between min and max (SEARCH).
 *
 * Webcam name is inside TagSight ("Webcam 1").
 * Servo name is inside Turret ("TurretTurnerServo").
 */
@Autonomous(name = "Turret Track RED", group = "cydogs")
public class TurretTrackRedAuto extends LinearOpMode {

    // How close to center counts as locked (degrees of bearing)
    private static final double LOCK_TOLERANCE_DEG = 3.0;

    // How hard to nudge toward the tag
    private static final double TRACK_GAIN = 0.15;

    // How far to move each loop while searching (turret degrees)
    private static final double SEARCH_STEP_DEG = 1.0;

    private TagSight eyes;
    private Turret turret;

    // +1 toward max angle, -1 toward min angle
    private double searchDirection = 1.0;

    @Override
    public void runOpMode() {
        eyes = new TagSight(hardwareMap, TagSight.Alliance.RED);
        turret = new Turret(this);
        turret.initialize();

        telemetry.addLine("Turret Track RED ready");
        telemetry.addData("min deg", "%.1f", Turret.MIN_DEGREES);
        telemetry.addData("straight deg", "%.1f", Turret.STRAIGHT_DEGREES);
        telemetry.addData("max deg", "%.1f", Turret.MAX_DEGREES);
        telemetry.update();
        waitForStart();

        while (opModeIsActive()) {
            eyes.update();

            if (eyes.hasTarget) {
                trackTag();
            } else {
                searchForTag();
            }

            telemetry.addData("filter", "red + upright");
            telemetry.addData("hasTarget", eyes.hasTarget);
            telemetry.addData("target", eyes.targetName);
            telemetry.addData("bearing", "%.1f", eyes.bearingDegrees);
            telemetry.addData("roll", "%.1f", eyes.rollDegrees);
            telemetry.addData("upright", eyes.isUpright);
            telemetry.addData("turret deg", "%.1f", turret.getTurretDegrees());
            telemetry.addData("mode", eyes.hasTarget ? "TRACK" : "SEARCH");
            telemetry.update();

            sleep(20);
        }

        eyes.close();
    }

    /**
     * Move until the tag is near the horizontal center of the camera.
     * If it tracks the wrong way, use: -eyes.bearingDegrees * TRACK_GAIN
     */
    private void trackTag() {
        if (Math.abs(eyes.bearingDegrees) <= LOCK_TOLERANCE_DEG) {
            return;
        }
        double nudge = eyes.bearingDegrees * TRACK_GAIN;
        turret.nudgeByDegrees(nudge);
    }

    /** Sweep back and forth until a matching tag is found. */
    private void searchForTag() {
        if (turret.isAtMax()) {
            searchDirection = -1.0;
        } else if (turret.isAtMin()) {
            searchDirection = 1.0;
        }
        turret.nudgeByDegrees(searchDirection * SEARCH_STEP_DEG);
    }
}