package org.firstinspires.ftc.teamcode.cydogs.turret;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.cydogs.basedevices.BaseServo;

/**
 * A turret that turns from 0 degrees to 270 degrees using a regular servo.
 * Students think in DEGREES.
 * The servo hardware uses positions from 0.0 to 1.0.
 * This class converts between the two.
 * Example:
 *   0°   → left end
 *   135° → middle
 *   270° → right end
 */
public class Turret {

    // Full mechanical range of this turret (degrees)
    public static final double MIN_DEGREES = 0.0;
    public static final double MAX_DEGREES = 270.0;

    // Reuses the team's simple servo helper
    private final BaseServo servo;

    // Last angle we commanded (degrees)
    private double currentDegrees;

    /**
     * @param opMode       the OpMode (needed by BaseServo)
     * @param hardwareName name of the servo in the robot config (example: "turret")
     */
    public Turret(OpMode opMode, String hardwareName) {
        // Servo position 0.0 = 0°, 1.0 = 270°
        servo = new BaseServo(
                opMode,
                hardwareName,
                Servo.Direction.FORWARD,
                0.0,   // min servo position
                1.0,   // max servo position
                0.5    // start in the middle (~135°)
        );

        currentDegrees = 135.0;
    }

    /** Must call once before moving the turret. */
    public void initialize() {
        servo.Initialize();
        setAngleDegrees(currentDegrees);
    }

    /**
     * Point the turret to an angle from 0 to 270 degrees.
     * Values outside that range are clamped.
     */
    public void setAngleDegrees(double degrees) {
        currentDegrees = clampDegrees(degrees);
        // Convert degrees → servo position 0.0 to 1.0
        double position = currentDegrees / MAX_DEGREES;
        servo.SetPosition(position);
    }

    /**
     * Turn a little more or less from where we are now.
     * Positive delta = one way, negative = the other way.
     */
    public void nudgeByDegrees(double deltaDegrees) {
        setAngleDegrees(currentDegrees + deltaDegrees);
    }

    /** Last angle we asked for (degrees). */
    public double getAngleDegrees() {
        return currentDegrees;
    }

    public boolean isAtMin() {
        return currentDegrees <= MIN_DEGREES + 0.5;
    }

    public boolean isAtMax() {
        return currentDegrees >= MAX_DEGREES - 0.5;
    }

    private double clampDegrees(double degrees) {
        if (degrees < MIN_DEGREES) {
            return MIN_DEGREES;
        }
        if (degrees > MAX_DEGREES) {
            return MAX_DEGREES;
        }
        return degrees;
    }
}