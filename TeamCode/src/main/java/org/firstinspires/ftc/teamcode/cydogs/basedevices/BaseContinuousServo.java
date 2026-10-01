package org.firstinspires.ftc.teamcode.cydogs.basedevices;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

public class BaseContinuousServo {

    private final OpMode opMode;
    private final String hardwareName;
    private final DcMotorSimple.Direction direction;
    private final double runPower;
    private CRServo servo;
    private double currentRunningPower = 0;


    /** runPower is the speed used by RunForward/RunBackward, from 0 to 1 (sign is ignored). */
    public BaseContinuousServo(OpMode opMode,
                               String hardwareName,
                               DcMotorSimple.Direction direction,
                               double runPower) {
        this.opMode = opMode;
        this.hardwareName = hardwareName;
        this.direction = direction;
        this.runPower = Math.min(1.0, Math.abs(runPower));
    }

    /** Gets the servo from the hardware map and applies direction. Call once before using the servo. */
    public void Initialize() {
        servo = opMode.hardwareMap.get(CRServo.class, hardwareName);
        servo.setDirection(direction);
        applyPower(0);
    }

    public void RunForward() {
        applyPower(runPower);
    }

    public void RunBackward() {
        applyPower(-runPower);
    }

    public void RunForward(double power) {
        applyPower(Math.abs(power));
    }

    public void RunBackward(double power) {
        applyPower(-Math.abs(power));
    }

    public void Stop() {
        applyPower(0);
    }

    /** Returns the last commanded power, from -1 to 1 (not a sensed value). */
    public double GetRunningPower() {
        return currentRunningPower;
    }

    /** Single place where power is sent to the servo, so the tracked value always matches. */
    private void applyPower(double power) {
        checkInitialized();
        currentRunningPower = Math.max(-1.0, Math.min(1.0, power));
        servo.setPower(currentRunningPower);
    }

    private void checkInitialized() {
        if (servo == null) {
            throw new IllegalStateException(
                    "BaseContinuousServo '" + hardwareName + "' used before Initialize() was called");
        }
    }
}