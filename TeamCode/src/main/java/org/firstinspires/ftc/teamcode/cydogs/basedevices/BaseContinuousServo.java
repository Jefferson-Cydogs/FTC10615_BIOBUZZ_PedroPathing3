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

    // left_intake_servo


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
        servo.setPower(0);
    }

    public void RunForward() {
        checkInitialized();
        servo.setPower(runPower);
    }

    public void RunBackward() {
        checkInitialized();
        servo.setPower(-runPower);
    }

    public void Stop() {
        checkInitialized();
        servo.setPower(0);
    }

    private void checkInitialized() {
        if (servo == null) {
            throw new IllegalStateException(
                    "BaseContinuousServo '" + hardwareName + "' used before Initialize() was called");
        }
    }
}
