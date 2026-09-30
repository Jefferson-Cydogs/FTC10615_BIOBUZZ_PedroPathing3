package org.firstinspires.ftc.teamcode.cydogs.teleop;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.cydogs.chassis.BioBuzzRobotChassis;
import org.firstinspires.ftc.teamcode.cydogs.chassis.IndianaChassis;
import org.firstinspires.ftc.teamcode.cydogs.components.ArtifactSensors;
import org.firstinspires.ftc.teamcode.cydogs.components.ColorLED;
import org.firstinspires.ftc.teamcode.cydogs.components.ColorLED.ColorOption;
import org.firstinspires.ftc.teamcode.cydogs.components.Feeders;
import org.firstinspires.ftc.teamcode.cydogs.components.Gates;
import org.firstinspires.ftc.teamcode.cydogs.components.IntakeV2;
import org.firstinspires.ftc.teamcode.cydogs.components.LaunchersWithVelocity;
import org.firstinspires.ftc.teamcode.cydogs.core.EventTracker;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;


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
        Wheels.InitializeChassisTeleop(.8,.3,.7);
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

    private void manageDriverControls()
    {

    }

    private void manageManipulatorControls()
    {



    }

    private void initializeDevices()
    {

    }

    private void initializePositions()
    {

    }

}

