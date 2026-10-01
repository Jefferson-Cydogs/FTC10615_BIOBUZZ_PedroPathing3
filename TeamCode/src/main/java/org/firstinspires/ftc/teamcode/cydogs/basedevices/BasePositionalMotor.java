package org.firstinspires.ftc.teamcode.cydogs.basedevices;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.robotcore.external.navigation.CurrentUnit;

public class BasePositionalMotor {
/*

privite static final int DEFAULT_POSITION_TOLERANCE = 10; // SDK default, in ticks
    privite static final int BIG_INCREMENT = 100;       // ticks
    private static final int SMALL_INCREMENT = 10;      // ticks
    private static final double DEFAULT_INCREMENT_POWER = 0.5;

    private final OpMode opMode;
    private final  String hardwareName;
    private final DcMotorSimple.Direction direction;
    private final DcMotorSimple.ZeroPowerBehavior zeroPowerBehavior;
    private final int minPosition;
    private final int maxPosition;

    private  DcMotorEx motor;
    private int position Tolerance = DEFAULT_POSITION_TOLERANCE;
    private double currentLimitAmps = 0; // 0 or less means stall protection is off
    private double incrementPower = DEAFAULT_INCREMENT_POWER;

    /**Positions are in encoder ticks.*/

    /*
    public BasePositionalMotor(OpMode opMode,
                               String hardwareName,
                               DcMotorSimple.Direction direction,
                               DcMotor.ZeroPowerBehavior zeroPowerBehavior,
                               int minPosition,
                               int maxPosition) {

        if (minPosition>maxPosition){
            throw new IllegalArgumentException(
                    "BasePositionMotor""+ hardwareName +"": minPosition (" + minPosition
                    +") cannot be greater than maxPosition (" + maxPosition + ")");
        }

        this.opMode = opMode;
        this.hardwareName = hardwareName;
        this.direction = direction;
        this.zeroPowerBehavior = ZeroPowerBehavior;
        this.minPosition = minPosition;
        this.maxPosition = maxPosition;
    }

    /*
     * Gets the motor from the hardware map, applies direction, Zero power behavior, and
     position
     *tolerance, and zeroes the encoder. The mechanism should be at its home (min) position
     * when this runs
     * /
     public void initialize(){
     motor=opMode.hardwareMap.get(DcMotorEx.class, hardwareName);
     motor.setDirection(direction);

     */

}