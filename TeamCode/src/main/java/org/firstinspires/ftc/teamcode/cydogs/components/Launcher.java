package org.firstinspires.ftc.teamcode.cydogs.components;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.teamcode.cydogs.basedevices.BaseLED;
import org.firstinspires.ftc.teamcode.cydogs.basedevices.BaseVelocityMotor;

/**
 * Launcher - the spinning motor that throws scoring elements, plus an LED that shows whether
 * it has reached speed (RED = not yet, GREEN = ready).
 *
 * A Launcher IS a BaseVelocityMotor (so it inherits Initialize, SetVelocity, Stop and
 * IsAtVelocity) and HAS a BaseLED. The motor holds a target speed counted in encoder ticks per
 * second, so its encoder cable must be plugged in.
 *
 * How an OpMode uses it:
 *   1. Make one:                 new Launcher(this)   (inside runOpMode/init, not as a field
 *                                initializer, because the LED connects to the hardware map as
 *                                soon as it is created)
 *   2. Call Initialize()         once (inherited).
 *   3. StartLauncher() / StopLauncher() from button presses.
 *   4. IncreaseLaunchPercent() / DecreaseLaunchPercent() to tune the speed in 1% steps. If the
 *      launcher is running, the new speed takes effect immediately.
 *   5. IsLauncherAtSpeed()       every loop. The LED only changes color when this runs.
 */
public class Launcher extends BaseVelocityMotor {

    // Must match the names in the Robot Configuration on the Driver Hub.
    private static final String HARDWARE_NAME = "Launcher";
    private static final String LED_NAME = "LauncherSpeedLED";

    // Use REVERSE if the launcher spins the wrong way.
    private static final DcMotorSimple.Direction DIRECTION = DcMotorSimple.Direction.FORWARD;

    // FLOAT lets the wheel coast down when stopped instead of braking hard.
    private static final DcMotor.ZeroPowerBehavior ZERO_POWER_BEHAVIOR =
            DcMotor.ZeroPowerBehavior.FLOAT;

    // The motor's top speed in ticks per second (free-speed RPM x ticks per revolution / 60).
    // NOTE: 300 is a placeholder. Check it against the motor's spec sheet.
    private static final double MAX_TICKS_PER_SECOND = 300;

    // Starting launch speed as a fraction of top speed (0.5 = 50%). Tune on the robot.
    private static final double LAUNCH_PERCENT = 0.5;

    // How much one adjustment changes the launch speed (0.01 = 1% of top speed).
    private static final double PERCENT_STEP = 0.01;

    // The speed indicator light.
    private final BaseLED speedLed;

    // Current launch speed as a fraction of top speed. Starts at LAUNCH_PERCENT and can be adjusted.
    private double launchVelocityPercent = LAUNCH_PERCENT;

    public Launcher(OpMode opMode) {
        super(opMode, HARDWARE_NAME, DIRECTION, ZERO_POWER_BEHAVIOR);
        speedLed = new BaseLED(opMode.hardwareMap, LED_NAME);
    }

    /** Spins the launcher up to the current launch speed. Returns immediately. */
    public void StartLauncher() {
        SetVelocity(MAX_TICKS_PER_SECOND * launchVelocityPercent);
    }

    /** Stops the launcher and turns the LED off. */
    public void StopLauncher() {
        Stop();
        speedLed.setOff();
    }

    /**
     * True when the launcher is within 5% of its target speed. Also sets the LED: green when
     * at speed, red when not (including when the launcher is stopped). Call this every loop.
     */
    public boolean IsLauncherAtSpeed() {
        boolean atSpeed = IsAtVelocity();
        if (atSpeed) {
            speedLed.setGreen();
        } else {
            speedLed.setRed();
        }
        return atSpeed;
    }

    /** Raises the launch speed by one step. If the launcher is running, applies it right away. */
    public void IncreaseLaunchPercent() {
        adjustLaunchPercent(PERCENT_STEP);
    }

    /** Lowers the launch speed by one step. If the launcher is running, applies it right away. */
    public void DecreaseLaunchPercent() {
        adjustLaunchPercent(-PERCENT_STEP);
    }

    /** The current launch speed as a fraction of top speed (0.5 = 50%), for telemetry. */
    public double GetLaunchVelocityPercent() {
        return launchVelocityPercent;
    }

    private void adjustLaunchPercent(double change) {
        launchVelocityPercent = Math.max(0.0, Math.min(1.0, launchVelocityPercent + change));
        if (GetTargetVelocity() != 0) {   // launcher is running, so apply the new speed now
            StartLauncher();
        }
    }
}