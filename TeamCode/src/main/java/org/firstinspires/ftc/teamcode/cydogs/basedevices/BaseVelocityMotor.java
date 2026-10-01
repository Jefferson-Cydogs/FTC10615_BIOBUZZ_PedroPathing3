package org.firstinspires.ftc.teamcode.cydogs.basedevices;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;

public class BaseVelocityMotor {

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
                                      DcMotor.ZeroPowerBehavior zeroPowerBehavior){
             this.opMode = opMode;
             this.hardwareName = hardwareName;
             }