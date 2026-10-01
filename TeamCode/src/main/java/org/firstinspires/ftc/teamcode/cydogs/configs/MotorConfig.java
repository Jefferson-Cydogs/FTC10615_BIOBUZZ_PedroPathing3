package org.firstinspires.ftc.teamcode.cydogs.configs;


import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.teamcode.cydogs.basedevices.BasePositionalMotor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
/*
@TeleOp(name = "Motor Config")
public class MotorConfig extends OpMode {


    private static final int TEST_MIN=-1_000_000;
    private static final int TEST_MAX=-1_000_000;
    private static final double TEST_POWER= 0.5;
    private final List<String>motorNames= new ArrayList<>();
    private int selectedIndex=0;
    private boolean selectionLocked = false;
    private BasePositionMotor selectedMotor;
    private boolean prevDpadup, prevDpadDown, prevDpadLeft, prevDpadRight,prevA,prevB,prevX;
    @Override
    public void init() {
    for (Map.Entry<String,DcMotor>entry:hardwareMap.dcMotor.entrySet())
        {motorNames.add(entry.getKey());
        }

        if(motorNames.isEmpty()){
            telemetry.addLine("No motors found in the robot configuration!");
        }else {
            telemetry.addLine("Found"+motorNames.size()+"motor(s).");
        }
        telemetry.addLine("D-Pad Up/Down to select,A to confrim.");
        telemetry.update();
    }

    @Override
    public void init_loop() {
         if (motorNames.isEmpty()||selectionLocked){
             return;
            }
        boolean dpadUpPressed=gamepad1.dpad_up&&!prevDpadup;
         boolean dpadDownPressed=gamepad1.dpad_down&&!prevDpadDown;
         boolean aPressed=gamepad1.a&&!prevA;

         if(dpadUpPressed){
             selectedIndex=(selectedIndex-1+motorNames.size())%motorNames.size();
         }
         if (dpadDownPressed){
             selectedIndex=(selectedIndex+1)%motorNames.size();
         }
         if (aPressed){
             selectionLocked=true;
             selectedMotor=new BasePositionalMotor(
                    this,
                    motorNames.get(selectedIndex),
                    DcMotorSimple.Direction.FORWARD,
                    DcMotor.ZeroPowerBehavior.BRAKE,
                     TEST_MIN,
                     TEST_MAX);
             selectedMotor.SetIncrementPower(TEST_POWER);
             selectedMotor.Initalize();
         }
         prevDpadup=gamepad1.dpad_up;
         prevDpadDown=gamepad1.dpad_down;
         prevA=gamepad1.a;

         telemetry.addLine("Select a mptpr(D-Pad Up/Down), then press A");
         for (int i =0;i<motorNames.size();i++){
             String marker = (i==selectedIndex)?">>":"";
             telemetry.addLine(marker+motorNames.get(i));
         }
         telemetry.update();



    }

    @Override
    public void loop() {
        if(selectedMotor==null){
            telemetry.addLine("No motor was selcted during init!");
            telemetry.update();
            return;
        }
        boolean dpadUpPressed=gamepad1.dpad_up&&!prevDpadup;
        boolean dpadDownPressed=gamepad1.dpad_down&&!prevDpadDown;
        boolean dpadRightPressed=gamepad1.dpad_right&&!prevDpadRight;
        boolean dpadLeftPressed=gamepad1.dpad_left&&!prevDpadLeft;
        boolean bPressed=gamepad1.b&&!prevB;
        boolean xPressed=gamepad1.x&&!prevX;

        if (dpadUpPressed){
            selectedMotor.UpBigIncrement();
        }
        if (dpadDownPressed){
            selectedMotor.DownBigIncrement();
        }
        if (dpadRightPressed){
            selectedMotor.UpSmallIncrement();
        }
        if (dpadLeftPressed){
            selectedMotor.DownSmallIncrement();
        }
        if (bPressed){
            selectedMotor.Stop();
        }
        if (xPressed){
            selectedMotor.ResetEncoder();
        }

        prevDpadup=gamepad1.dpad_up;
        prevDpadDown=gamepad1.dpad_down;
        prevDpadRight=gamepad1.dpad_right;
        prevDpadLeft=gamepad1.dpad_left;
        prevB=gamepad1.b;
        prevX=gamepad1.x;

        telemetry.addData("Controlling motor",motorNames.get(selectedIndex));
        telemetry.addData("Position(tick)",selectedMotor.GetPosition());
        telemetry.addData("Power","%.2f",selectedMotor.GetPower());
        telemetry.addData("Current(A)","%.2f",selectedMotor.GetCurrent());
        telemetry.addData("Busy",selectedMotor.IsBusy());
        telemetry.addLine("Up/Down=+-100 ticks Left/Right = +- 10 ticks");
        telemetry.addLine("B=Stop X=Resest encoder to 0");
        telemetry.update();


    }
*/
}