package org.firstinspires.ftc.teamcode.cydogs.trix;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import org.firstinspires.ftc.teamcode.cydogs.basedevices.BaseContinuousServo;
import org.firstinspires.ftc.teamcode.cydogs.basedevices.BasePowerMotor;
import org.firstinspires.ftc.teamcode.cydogs.chassis.BioBuzzRobotChassis;
import org.firstinspires.ftc.teamcode.cydogs.components.DumpArm;


@TeleOp
public class TrixTeleop extends LinearOpMode {

    /*
        Motors
            launcher
            intake
        Servos
            windmillServo
            right_intake_servo
            left_intake_servo
            medium_flower_servo
            lower_flower_servo
            upper_flower_servo
            LedLight


Drive	Gamepad 1 sticks	Yes
Launcher on	Gamepad 2 Triangle	Yes
Launcher off	Gamepad 2 Cross (X)	Yes
Intake on	Gamepad 2 Right Trigger	Yes
Reverse intake	Gamepad 2 Left Trigger	Yes
Run feeder	Gamepad 2 Bumper (held)	Yes
     */


    private BioBuzzRobotChassis TrixWheels;


    private BaseContinuousServo leftloader;

    private BaseContinuousServo rightloader;

    private BasePowerMotor launcher;

    private BasePowerMotor intake;

    private BaseContinuousServo feeder;

    private DumpArm flowerArm;

    @Override
    public void runOpMode() {

        // Execute initialization actions here

        TrixWheels = new BioBuzzRobotChassis(this);
        TrixWheels.InitializeChassisTeleop(.6,.3,.5);

        initializeWheels();
        initializeDevices();
        initializePositions();

        waitForStart();
        while (opModeIsActive()) {
            // Execute OpMode actions here
            TrixWheels.OptimizedTeleopDrive();
            manageDriverControls();
            manageManipulatorControls();

        }
    }

    private void manageDriverControls()
    {

    }

    private void manageManipulatorControls()
    {  /*
        Launcher on	Gamepad 2 Triangle	Yes
        Launcher off	Gamepad 2 Cross (X)	Yes
        Intake on	Gamepad 2 Right Trigger	Yes
        Reverse intake	Gamepad 2 Left Trigger	Yes
        Run feeder	Gamepad 2 Bumper (held)	Yes
            */

        if(gamepad2.triangle)
        {
            launcher.SetPower(.4);
        }
        else if (gamepad2.cross)
        {
            launcher.Stop();
        }
        if (gamepad2.right_trigger > .4)
        {
            intake.SetPower(.6);
            rightloader.RunForward();
            leftloader.RunForward();
        }
        else if (gamepad2.left_trigger > .4)
        {
            intake.SetPower(-.4);
            rightloader.Stop();
            leftloader.Stop();
        }
        else {
            intake.Stop();
            rightloader.Stop();
            leftloader.Stop();
        }
        if(gamepad2.right_bumper)
        {
            feeder.RunForward();
        }
        else
        {
            feeder.Stop();
        }
        if(gamepad2.dpad_up)
        {
            flowerArm.MoveToDump();
        }
        if(gamepad2.dpad_down)
        {
            flowerArm.MoveToRest();
        }

        flowerArm.Update();

    }



    private void initializeDevices()
    {
        leftloader = new BaseContinuousServo(this,"left_intake_servo", DcMotorSimple.Direction.FORWARD,0.7);
        leftloader.Initialize();

        rightloader = new BaseContinuousServo(this,"right_intake_servo", DcMotorSimple.Direction.FORWARD, 0.7);
        rightloader.Initialize();

        feeder = new BaseContinuousServo(this,"windmillServo", DcMotorSimple.Direction.FORWARD, 0.5);
        feeder.Initialize();

        launcher = new BasePowerMotor(this, "launcher", DcMotorSimple.Direction.FORWARD, DcMotor.ZeroPowerBehavior.FLOAT);
        launcher.Initialize();

        intake = new BasePowerMotor(this, "intake", DcMotorSimple.Direction.FORWARD, DcMotor.ZeroPowerBehavior.FLOAT);
        intake.Initialize();

        flowerArm = new DumpArm(this);
        flowerArm.Initialize();
    }

    private void initializePositions()
    {

    }


}
