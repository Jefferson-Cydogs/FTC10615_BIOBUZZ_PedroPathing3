package org.firstinspires.ftc.teamcode.cydogs.basedevices;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;

public class BaseVelocityMotor {
    /*
        private static final double DEFAULT_VELOCITY_TOLERANCE_PERCENT = 0.05;

        private final OpMode opMode;
        private final String hardwareName;
        private final DcMotorSimple.Direction direction;
        private final DcMotor.ZeroPowerBehavior zeroPowerBehavior;

        private DcMotorEx motor;
        private double targetVelocity = 0;
        private boolean velocityTargetActive = false;

                 public BaseVelocityMotor(OpMode opMode,
                                          String hardwareName,
                                          DcMotorSimple.Direction direction,
                                          DcMotor.ZeroPowerBehavior zeroPowerBehavior) {
                     this.opMode = opMode;
                     this.hardwareName = hardwareName;
                     this.direction = direction;
                     this.zeroPowerBehavior = zeroPowerBehavior;
                 }
                 /** Gets the motor from the hardware map and prepares it for velocity control. */

    /*
    public void Initialize() {

        motor = opMode.hardwareMap.get(DcMotorEx.class, hardwareName);
        motor.setDirection(direction);
        motor.setZeroPowerBehavior(zeroPowerBehavior);
        motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motor.setPower(0);
        targetVelocity = 0;
        velocityTargetActive = false;
    }

    /**
     * Starts the motor toward a target velocity (encoder ticks per second) and returns
     * immediately.
     * The motor controller's built-in PID does the spin-up in the background. Use IsAtVelocity()
     * to check whether it has reached the target.
     */
    /*
    public void SetVelocity(double ticksPerSecond) {
        checkInitialized();
        targetVelocity = ticksPerSecond;
        velocityTargetActive = true;
        motor.setVelocity(ticksPerSecond);
    }

    /**
     * True if a velocity target is active and the measured velocity is within the tolerance
     * (ticks/sec) of it
     */
    public boolean IsAtVelocity() {
        checkInitialized();
        return velocityTargetActive
                && Math.abs(motor.getVelocity() - targetVelocity) <= toleranceTicksPerSecond;
    }

    /**
     * Same as above, using a default tolerance of 5% of the target velocity.
     */
    public boolean IsAtVelocity() {
        return IsAtVelocity(Math.abs(targetVelocity) *
                DEFAULT_VELOCITY_TOLERANCE_PERCENT);
    }
             */
}