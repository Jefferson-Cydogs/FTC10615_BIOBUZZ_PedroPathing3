package org.firstinspires.ftc.teamcode.cydogs.teleop;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.cydogs.chassis.BioBuzzRobotChassis;
import org.firstinspires.ftc.teamcode.cydogs.core.EventTracker;



@TeleOp(name="New Robot BioBuzz TeleOp", group= "TeleOp")
public class BioBuzzTeleop extends LinearOpMode {

    /** declare variables here */
    private BioBuzzRobotChassis Wheels;

    private ElapsedTime currentTimer;
    private ElapsedTime matchTimer;
    private EventTracker eventTracker;


    public String Team = "blue";

    @Override
    public void runOpMode()
    {
        /** Execute initialization actions here */
        Wheels = new BioBuzzRobotChassis(this);
        Wheels.InitializeChassisTeleop(.6,.3,.5);
        initializeDevices();
        initializePositions();
        currentTimer = new ElapsedTime();
        matchTimer = new ElapsedTime();
        eventTracker = new EventTracker();

        waitForStart();
        //tagReader.initAprilTag();
        matchTimer.reset();

        while (opModeIsActive()) {
            /** Execute OpMode actions here */
            Wheels.OptimizedTeleopDrive();

            //tagReader.displayDetections(tagReader.GetDetections());
            manageDriverControls();
            manageManipulatorControls();



            if (eventTracker.doEvent("Telemetry",currentTimer.seconds(),0.5)) {

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
            // increase launcher velocity by adjustLauncherPowerIncrement
            // write out to telemetry the new launcher velocity
        }
        else if(gamepad1.dpadDownWasPressed())
        {
            // decrease launcher velocity by adjustLauncherPowerIncrement
            // write out to telemetry the new launcher velocity
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
            // turn launcher ff
        }

        if(gamepad2.right_bumper)
        {
            // run feeder
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

    private void initializeDevices()
    {

    }

    private void initializePositions()
    {

    }

}

