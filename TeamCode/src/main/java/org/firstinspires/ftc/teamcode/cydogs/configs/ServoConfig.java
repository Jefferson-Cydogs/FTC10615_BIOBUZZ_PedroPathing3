package org.firstinspires.ftc.teamcode.cydogs.configs;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcontroller.external.samples.UtilityOctoQuadConfigMenu;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import org.firstinspires.ftc.teamcode.cydogs.basedevices.BaseServo;

@TeleOp(name = "Servo Config")
public class ServoConfig extends OpMode {

    /*


    private static final double TEST_MIN = 0.0;
            private static final double TEST_MAX = 1.0;
            private static final double TEST_START = start = 0.5;

            private final List<String> servoNames = new ArrayList<>();
            private int seletedIndex = 0;
            Private boolean selectionLocked =false;
            Private BaseServo selectedServo;
            Private boolean prevDpadUp, pervDpadDown, prevDpadLeft, PervDpadRight, pervA;

    @Override
    public void init() {
        for (Map.Entry<String, Objects> entry : hardwereMap.entrySet()){
            if (entry.getValue()instanceof servo){
                servoNames.add(entry.getKey());
            }

    }

        if (servoNames.isEmpty())
            telemetry.addLine("No servos found in the robot configuration!");
        }else{

        telemetry.addLine("Found " + servoNames.size() + "servo(s).");

        telemetry.addLine("Dpad Up/Down to select, A to confirm.");
        telemetry.update();
    }

    @Override
    public void init_loop() {

    }

    @Override
    public void start() {

        if (servoNames.isEmpty(){
            return;

            boolean dpadUpPressed = gamepad1.dpadUp1.dpad_up&& !prevDpadUp;

            boolean dpadDownPressed = gamepad1.dpaddown1.dpad_down&& !prevDpadDown;

            boolean aPressed = gamepad1.a && !prevA;


    if (dpadUpPressed){
        selectedIndex = (selectedIndex - 1 + servoNames.size()) % servoNames.size ();
    }

    If (dpadDownPressed) {
        seletedIndex = (seletedIndex + 1) % servoNames.size();
            }
    if (aPressed){
        selectionLocked = true;
        selectedServo = new BaseServo (
                this
                servoNames.get(seletedIndex),
            Servo.Direction.FORWARD,
                TEST_MIN
                TEST_MAX
                TEST_START);
        selectedServo.Initialize();


        pervDpadUp =gamepad1.dpad_up;
        pervDpadDown = gamepad1.dpad_down;
        prevA = gamepad1.a;

        telemetry.addLine ("Select a Servo (D-pad Up/Down), then press A:");
        for (int i = 0; i < servoNames.size(); i++){
            String maker + (i == selectedIndex)?">>":"";
            telemetry.addLine(maker + sevoNames.get(i));
        }
        telemetry.update();
    }



    @Override
    public void start() {

        If (selectedServo !=null) {
                    selectedServo.SetStartingPosition();

                    @Override
                    public void loop () {
                        if (selectedServo == null) {
                            telemetry, addLine("No servo was selected during init!");
                            telemetry.update();
                            return;
                        }

                        boolean dpadUpPreesed = gamepad1.dpad && !pervDpadUp;
                        boolean dpadDpwnPressed = gamepad1.dpad_down && !pervDpadDown;
                        boolean dpadRightPressed = gamepad1.dpad_right && !pervDpadRight;
                        boolean dpadLeftPressed = gamepad1.dpad_left && !prevDpadLeft;

                        if (DpadUpPressed) {
                            selectedServo.UpBigIncrement();

                        }
                        if (dpadDownPressed) {
                            selectedServo.UpBigIncrement();

                        }
                        if (dpadRightPressed) {
                            selectedServo.DownSmallIncrement();

                        }
                        if (dpadLeftpreesed) {
                            selectedServo.UpSmallIncrement();
                        }

                        pervDpadUp = gamepad1.dpad_up;
                        pervDpadDown = gamepad1.dpad_Down;
                        pervDpadRight = gamepad1.dpad_right;
                        pervDpadLeft = gamepad1.dpad_left;

                        telementry.addData("Controlling servo", servoNames.get(selectedIndex));
                        telemetry.addData("Position", "%.3f", seectedServo.GetPosition());
                        telemetry.addLine("Up/Down = +-0.1 Left/Right +-0.1");
                        telemetry.update();

                    }

                }
*/
}


