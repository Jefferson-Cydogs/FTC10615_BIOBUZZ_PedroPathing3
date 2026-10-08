package org.firstinspires.ftc.teamcode.cydogs.teleop;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.cydogs.chassis.BioBuzzRobotChassis;
import org.firstinspires.ftc.teamcode.cydogs.components.FlowerScoringServo;
import org.firstinspires.ftc.teamcode.cydogs.components.FlowerSlider;
import org.firstinspires.ftc.teamcode.cydogs.components.Intake;
import org.firstinspires.ftc.teamcode.cydogs.components.Launcher;
import org.firstinspires.ftc.teamcode.cydogs.components.LoaderServo;
import org.firstinspires.ftc.teamcode.cydogs.core.EventTracker;



@TeleOp(name="New Robot BioBuzz TeleOp", group= "TeleOp")
public class BioBuzzTeleop extends LinearOpMode {

    /** declare variables here */
    private BioBuzzRobotChassis Wheels;

    // WE NEED TO DECLARE OUR NEW DEVICES HERE
    //   intake, launcher, loaderServo, flowerSlider, flowerScoringServo




    private ElapsedTime currentTimer;
    private ElapsedTime matchTimer;
    private EventTracker eventTracker;


    public String Team = "blue";

    @Override
    public void runOpMode()
    {
        /** Execute initialization actions here */

        constructDevices();
        initializeDevices();
        initializePositions();
        currentTimer = new ElapsedTime();
        matchTimer = new ElapsedTime();
        eventTracker = new EventTracker();

        waitForStart();

        matchTimer.reset();

        while (opModeIsActive()) {
            /** Execute OpMode actions here */
            Wheels.OptimizedTeleopDrive();
            manageDriverControls();
            manageManipulatorControls();



            if (eventTracker.doEvent("Telemetry",currentTimer.seconds(),0.5)) {
                // add code to write out current velocity percent of launcher

                telemetry.update();
            }
        }
    }
    /*

     Drive	Gamepad 1 sticks	Yes
     Launcher on	Gamepad 2 Triangle	Yes
     Launcher off	Gamepad 2 Cross (X)	Yes
     Intake on	Gamepad 2 Right Trigger	Yes
     Reverse intake	Gamepad 2 Left Trigger	Yes
     Run feeder	Gamepad 2 Bumper (held)	Yes


  */
    private void manageDriverControls()
    {
        if(gamepad1.dpadUpWasPressed())
        {
            // increase launcher velocity the built in increment

        }
        else if(gamepad1.dpadDownWasPressed())
        {
            // decrease launcher velocity by the built in decrement

        }
    }

    private void manageManipulatorControls()
    {
        //intake
        if(gamepad2.right_trigger > 0.4)
        {
            // run intake in
        }
        else if(gamepad2.left_trigger > 0.4)
        {
            // run intake reverse
        }
        else {
            // stop the intake
        }

        if(gamepad2.triangleWasPressed())
        {
            // turn launcher on
        }
        else if(gamepad2.crossWasPressed())
        {
            // turn launcher off
        }

        if(gamepad2.rightBumperWasPressed())
        {
            // move scoring element to launcher
        }

        if(gamepad2.dpadUpWasPressed())
        {
            // raise flower lift
        }
        else if(gamepad2.dpadDownWasPressed())
        {
            // lower flower lift AND close flower scoring servo
        }

        if(gamepad2.squareWasPressed())
        {
            // open flower scoring servo
        }
        else if(gamepad2.circleWasPressed())
        {
            // close flower scoring servo
        }

    }

    private void constructDevices()
    {
        Wheels = new BioBuzzRobotChassis(this);

        // WE NEED TO CONSTRUCT OUR NEW DEVICES HERE
        //   intake, launcher, loaderServo, flowerSlider, flowerScoringServo


    }
    private void initializeDevices()
    {
        Wheels.InitializeChassisTeleop(.6,.3,.5);

        // WE NEED TO INITIALIZE OUR NEW DEVICES HERE
        //   intake, launcher, loaderServo, flowerSlider, flowerScoringServo


    }

    private void initializePositions()
    {

    }

}

