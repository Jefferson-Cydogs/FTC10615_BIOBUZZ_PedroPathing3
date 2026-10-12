package org.firstinspires.ftc.teamcode.cydogs.components;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.teamcode.cydogs.basedevices.BaseContinuousServo;

public class MoveToLoadServo extends BaseContinuousServo {
    /**
     * runPower is the speed used by RunForward/RunBackward, from 0 to 1 (sign is ignored).
     *
     * @param opMode
     * @param hardwareName
     * @param direction
     * @param runPower
     */

    private static final String hardwareName = "MoveToLoadServo";

    private static final DcMotorSimple.Direction direction = DcMotorSimple.Direction.REVERSE;

    private static double runPower = 0.6;


    public MoveToLoadServo(OpMode opMode) {

        super(opMode, hardwareName, direction, runPower);
    }
}
