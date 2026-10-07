package org.firstinspires.ftc.teamcode.cydogs.components;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.teamcode.cydogs.basedevices.BaseLED;
import org.firstinspires.ftc.teamcode.cydogs.basedevices.BaseVelocityMotor;

/**
 * Launcher - the spinning motor that throws scoring elements, plus a light that tells the
 * drivers when it is ready.
 *
 * WHAT THE LAUNCHER IS MADE OF
 *   1) A launcher MOTOR that we control by SPEED, not by plain power. We say "spin at this
 *      speed" and the motor controller keeps adjusting the power by itself so it holds that
 *      speed, even as the battery drains. Speed here is counted in "ticks per second" (ticks
 *      are the small steps the motor's built-in sensor, called an encoder, counts as the motor
 *      turns).
 *   2) An LED light. The drivers should not have to guess whether the launcher has finished
 *      speeding up. RED means "not up to speed yet", GREEN means "ready to launch".
 *
 * "IS-A" AND "HAS-A" TOGETHER (this class uses both ideas you have already seen)
 *   - A Launcher IS a velocity motor, so it EXTENDS BaseVelocityMotor, just like LoaderServo
 *     extends BaseContinuousServo. It inherits starting, stopping and checking the speed.
 *   - A Launcher also HAS an LED, so it keeps a BaseLED in a variable (a "property"), just
 *     like Intake keeps its three parts.
 *   Why isn't the LED inherited too? Because in Java a class can only extend ONE parent. The
 *   motor is the parent, and the LED is a part we hold on to.
 *
 * WHAT WE ADD
 *   The settings, and THREE methods: StartLauncher, StopLauncher and IsLauncherAtSpeed.
 *
 * WHY THE SETTINGS LIVE HERE
 *   The OpMode (the program the drivers run) should not have to know any of the settings, or
 *   even that there is an LED. It will only say "make a launcher", "start it", "stop it" and
 *   "is it at speed yet?". If a name or a speed ever changes, we fix it in this ONE file.
 *
 * HOW AN OPMODE WILL USE YOUR CLASS (you do not write this part - just understand it)
 *   1. It makes a Launcher and hands it the OpMode (the only thing it passes in).
 *   2. It calls Initialize once. You do NOT write Initialize - you inherit it.
 *   3. When the driver presses a button, it calls StartLauncher, and later StopLauncher.
 *   4. Over and over, every time through its main loop, it calls IsLauncherAtSpeed. The LED only
 *      changes color at the moment that method runs, so it has to be asked regularly. That is
 *      how the light follows the launcher as it speeds up.
 *
 * YOUR JOB: finish Steps 1 through 6 below. Everything you write goes INSIDE the curly braces
 * of the class, in the spaces marked for each step. Do the steps in order and save after each
 * one.
 */
public class Launcher extends BaseVelocityMotor {

    // =========================================================================================
    // STEP 1: THE SETTINGS (constants)
    // =========================================================================================
    // Make seven constants. A constant is a variable that never changes after it is created.
    // Putting a number or name in ONE place with a good label is much better than typing the
    // same mysterious value in several places (programmers call those "magic numbers").
    //
    // Every constant must be:
    //   - private  (only this class needs to see it)
    //   - static   (it belongs to the class itself, not to one particular Launcher; this is also
    //               what lets you use it in Step 3)
    //   - final    (this is the word that makes it impossible to change later)
    // Name them in ALL_CAPITAL_LETTERS with underscores between words. That is the Java habit
    // for constants. Look at the top of DumpArm.java for examples of how they look.
    //
    // Make these seven, giving each a short comment explaining what it is:
    //
    //   a) HARDWARE_NAME - a String (text). The text must be exactly:  Launcher
    //      It has to match, letter for letter and capital for capital, the name of the motor
    //      in the Robot Configuration on the Driver Hub. If it is misspelled the robot will
    //      crash at init and tell you it "unable to find a hardware device with name ...".
    //
    //   b) LED_NAME - a String. The text must be exactly:  LauncherSpeedLED
    //      Same rule: it must match the Robot Configuration.
    //
    //   c) DIRECTION - the type is DcMotorSimple.Direction (that is why we imported
    //      DcMotorSimple above). It is either FORWARD or REVERSE. Use FORWARD for now. If the
    //      launcher spins the wrong way when we test, this is the one to change.
    //
    //   d) ZERO_POWER_BEHAVIOR - the type is DcMotor.ZeroPowerBehavior (that is why we
    //      imported DcMotor above). Set it to FLOAT, which lets the launcher wheel coast down
    //      when we stop it instead of slamming to a halt.
    //
    //   e) MAX_TICKS_PER_SECOND - a double (a number with a decimal point). The fastest this
    //      motor can possibly spin, counted in ticks per second. This comes from the motor's
    //      spec sheet: its top speed in revolutions per minute, times the number of ticks in
    //      one revolution, divided by 60.
    //      >>> MENTOR: write the number for our launcher motor here: ___________
    //
    //   f) LAUNCH_PERCENT - a double. How fast we want to launch, as a fraction of the top
    //      speed. 1.0 means the top speed and 0.5 means half of it. Start with 0.5. We will
    //      tune this number by testing on the robot.
    //
    //   g) LAUNCH_TICKS_PER_SECOND - a double. The actual speed we ask the motor for. You do
    //      NOT type a number for this one. Make it equal to MAX_TICKS_PER_SECOND multiplied by
    //      LAUNCH_PERCENT. (The multiply sign in Java is the asterisk.) A constant can be
    //      calculated from other constants, as long as those two were written ABOVE it.
    //
    // (Write your seven constants below this line.)


    // =========================================================================================
    // STEP 2: THE PROPERTY (the LED)
    // =========================================================================================
    // Make the one variable that holds the light. This is NOT a constant and NOT static.
    //
    // It must be:
    //   - private  (only this class needs to touch it)
    //   - final    (once it is created, it is never swapped for a different one)
    //
    // Make one, called speedLed, whose type is BaseLED. Add a short comment on it.
    //
    // Variables like this start out EMPTY. Because it is marked final, Java will insist that
    // you fill it in the constructor (Step 3) and will show a red underline until you do.
    //
    // (Write your property below this line.)


    // =========================================================================================
    // STEP 3: THE CONSTRUCTOR
    // =========================================================================================
    // A constructor is the special block of code that runs when someone creates a Launcher.
    // Look at the constructors inside BaseVelocityMotor.java and BaseLED.java, because your
    // constructor builds on both.
    //
    // Your constructor must:
    //   - be public
    //   - have EXACTLY the same name as the class (including the capital letters), and NO
    //     return type, not even void
    //   - take ONE parameter: an OpMode (that is why we imported OpMode above). Name the
    //     parameter opMode.
    //
    // Inside it there are TWO things to do, in this order:
    //
    //   FIRST LINE (it must be the very first line): call the PARENT class's constructor with
    //   the Java word  super  followed by parentheses. Pass these four things, in exactly this
    //   order, separated by commas:
    //       1) the opMode that was handed to your constructor
    //       2) your HARDWARE_NAME constant
    //       3) your DIRECTION constant
    //       4) your ZERO_POWER_BEHAVIOR constant
    //
    //   SECOND LINE: fill in your speedLed property by making a new BaseLED, using the Java
    //   word  new. Give it these two things, in this order (check the BaseLED constructor):
    //       1) the OpMode's hardware map. An OpMode has a variable named hardwareMap inside it;
    //          you reach it by writing the opMode parameter, then a dot, then hardwareMap.
    //       2) your LED_NAME constant
    //
    // Do NOT call Initialize here; the OpMode does that. (BaseLED has no Initialize method at
    // all, because it connects to the LED as soon as it is created.)
    //
    // (Write your constructor below this line.)


    // =========================================================================================
    // STEP 4: THE StartLauncher METHOD
    // =========================================================================================
    // Now write the method the driver's button will call to spin the launcher up. Look at the
    // SetVelocity method inside BaseVelocityMotor.java and at the Stop method in
    // BaseContinuousServo.java to see what methods look like.
    //
    // Your method must:
    //   - be public
    //   - return nothing (the word for that is void)
    //   - be named StartLauncher
    //   - take NO parameters
    //
    // Inside it there is just ONE line of code. Call the method you INHERITED from
    // BaseVelocityMotor that sets the motor's target speed. It is called SetVelocity. It needs
    // one thing passed to it: the speed in ticks per second. Give it your
    // LAUNCH_TICKS_PER_SECOND constant.
    //
    // SetVelocity starts the motor and returns right away. The motor does not jump to full
    // speed instantly, because it takes a moment to spin up. That is exactly why we have the
    // LED and the IsLauncherAtSpeed method.
    //
    // Add a comment above the method in your own words saying what it does.
    //
    // (Write your StartLauncher method below this line.)


    // =========================================================================================
    // STEP 5: THE StopLauncher METHOD
    // =========================================================================================
    // Written the same way as Step 4: public, void, NO parameters, named StopLauncher.
    //
    // Inside it there is ONE line. Call the inherited method that stops the motor. It is
    // called Stop, and it needs nothing passed to it.
    //
    // Add a comment above it in your own words.
    //
    // (Write your StopLauncher method below this line.)


    // =========================================================================================
    // STEP 6: THE IsLauncherAtSpeed METHOD
    // =========================================================================================
    // This is the trickiest one. It does TWO jobs: it answers a question, and it sets the
    // light to match the answer.
    //
    // Your method must:
    //   - be public
    //   - give back a boolean (true or false) instead of void. The word boolean goes where
    //     void went in the other methods.
    //   - be named IsLauncherAtSpeed
    //   - take NO parameters
    //
    // Inside it, do these things in order:
    //
    //   1) Ask the parent whether the motor has reached its target speed. The inherited method
    //      for that is called IsAtVelocity. Use the version that takes nothing in the
    //      parentheses; it counts "at speed" as being within 5 percent of the target. Save
    //      the answer in a new variable of type boolean. Call the variable atSpeed.
    //
    //   2) Make a two-way decision using if and else:
    //        - IF atSpeed is true, tell speedLed to turn green. BaseLED has a method for that.
    //        - ELSE (otherwise), tell speedLed to turn red.
    //      Careful: look in BaseLED.java at how its method names are written. Unlike most of
    //      our classes, they start with a SMALL letter, and Java will not accept a capital.
    //
    //   3) The very last line gives the answer back to whoever called the method. The Java
    //      word for that is  return  followed by your atSpeed variable.
    //
    // Good to know: when the launcher is stopped it has no target speed, so this method says
    // false and the light goes red. That is correct.
    //
    // Add a comment above the method in your own words.
    //
    // (Write your IsLauncherAtSpeed method below this line.)


    // =========================================================================================
    // CHECK YOUR WORK
    // =========================================================================================
    //   [ ] The first line of the class says it "extends BaseVelocityMotor".
    //   [ ] You have seven constants, each marked private static final, in ALL_CAPS, and
    //       LAUNCH_TICKS_PER_SECOND is calculated from the two constants above it.
    //   [ ] You have one property, speedLed, marked private final.
    //   [ ] The constructor is spelled exactly like the class and takes one OpMode. Its first
    //       line is the call to super with 4 things in the right order, and its second line
    //       fills in speedLed using  new.
    //   [ ] StartLauncher and StopLauncher each have no parameters and one line.
    //   [ ] IsLauncherAtSpeed says boolean (not void), has no parameters, sets the LED in an
    //       if / else, and ends with a return line.
    //   [ ] Every line of code ends with a semicolon, and every opening { has a closing }.
    //   [ ] The program builds with no red underlines.
    //
    // Common mistakes:
    //   - A red underline on super: it is not the first line in the constructor, or the four
    //     things are in a different order than the parent's constructor expects.
    //   - "Cannot find symbol" on the LED: BaseLED's methods start with a small letter
    //     (setGreen), not a capital (SetGreen). Java treats those as different words.
    //   - "Missing return statement": IsLauncherAtSpeed needs a return line at the very end.
    //   - The launcher barely moves: LAUNCH_PERCENT is a fraction, so it should be a number
    //     like 0.5, not 50. Also check that MAX_TICKS_PER_SECOND has been filled in.
    //   - The light never changes color: the OpMode must keep calling IsLauncherAtSpeed over
    //     and over. That is not something inside this class.
    //   - The robot crashes at init: a hardware name does not match the Robot Configuration,
    //     or the OpMode did not call Initialize.
    //
    // OPTIONAL CHALLENGE (only after everything above works)
    //   Make StopLauncher also turn the LED off, so the light does not stay stuck on its last
    //   color after the launcher stops. Add one more line to StopLauncher, using the BaseLED
    //   method that turns the light off.

}