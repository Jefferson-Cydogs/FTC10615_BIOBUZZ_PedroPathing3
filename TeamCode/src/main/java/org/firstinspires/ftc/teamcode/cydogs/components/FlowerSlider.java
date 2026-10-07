package org.firstinspires.ftc.teamcode.cydogs.components;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.teamcode.cydogs.basedevices.BasePositionalMotor;

/**
 * FlowerSlider - the motor that lifts the U-channel up to the height of the flower, so we can
 * dump scoring elements into it, and then lowers it back down to its home position.
 *
 * WHAT THIS CLASS IS
 *   A FlowerSlider is a special kind of BasePositionalMotor. BasePositionalMotor already knows
 *   how to drive a motor to an exact spot, counted in "ticks" (the small steps the motor's
 *   built-in sensor, called an encoder, counts as the motor turns). Our class INHERITS all of
 *   that, and we only add the things that are specific to the slider.
 *
 * WHAT WE ADD
 *   The robot's settings for THIS motor (its name, which way it spins, how many ticks high the
 *   flower is, how fast it moves) and TWO methods: RaiseToFlower and LowerToHome.
 *
 * WHY THE SETTINGS LIVE HERE
 *   The OpMode (the program the drivers run) should not have to know any of the settings. It
 *   will only say "make a slider", "raise it" and "lower it". If we ever change the motor, the
 *   gearing or the height, we fix it in this ONE file and every OpMode keeps working.
 *
 * HOW AN OPMODE WILL USE YOUR CLASS (you do not write this part - just understand it)
 *   1. It makes a FlowerSlider and hands it the OpMode (that is the only thing it passes in).
 *   2. It calls Initialize once. You do NOT write Initialize - you inherit it. Initialize
 *      sets "zero ticks" to wherever the slider is sitting at that moment. That is why the
 *      slider MUST be all the way down (at home) when the robot is turned on and Init is
 *      pressed. If it starts half-way up, the robot will think that spot is home!
 *   3. When the driver presses a button, it calls RaiseToFlower or LowerToHome.
 *
 * ABOUT WAITING
 *   Both of your methods will start the motor moving and then return right away. The motor
 *   controller does the rest by itself, stopping at the right spot, so the robot can keep
 *   driving while the slider moves. You do not write any waiting or any stopping code.
 *
 * YOUR JOB: finish Steps 1, 2, 3 and 4 below. Everything you write goes INSIDE the curly
 * braces of the class, in the spaces marked for each step. Do the steps in order and save after
 * each one.
 */
public class FlowerSlider extends BasePositionalMotor {

    // =========================================================================================
    // STEP 1: THE SETTINGS (constants)
    // =========================================================================================
    // Make seven constants. A constant is a variable that never changes after it is created.
    // Putting a number or name in ONE place with a good label is much better than typing the
    // same mysterious value in several places (programmers call those "magic numbers").
    //
    // Every constant must be:
    //   - private  (only this class needs to see it)
    //   - static   (it belongs to the class itself, not to one particular FlowerSlider; this is
    //               also what lets you use it in Step 2)
    //   - final    (this is the word that makes it impossible to change later)
    // Name them in ALL_CAPITAL_LETTERS with underscores between words. That is the Java habit
    // for constants. Look at the top of DumpArm.java for examples of how they look.
    //
    // Make these seven, giving each a short comment explaining what it is:
    //
    //   a) HARDWARE_NAME - a String (text). The text must be exactly the name of the slider
    //      motor in the Robot Configuration on the Driver Hub, letter for letter and capital
    //      for capital. If it is misspelled the robot will crash at init and tell you it
    //      "unable to find a hardware device with name ...".
    //      Hardware name is FlowerSlider
    //
    //   b) DIRECTION - the type is DcMotorSimple.Direction (that is why we imported
    //      DcMotorSimple above). It is either FORWARD or REVERSE. Pick the one that makes the
    //      slider go UP when the tick count goes UP. If the slider goes down while the ticks
    //      count up, you picked the wrong one.
    //      >>> MENTOR: write FORWARD or REVERSE here before handing this out: Forward
    //
    //   c) ZERO_POWER_BEHAVIOR - the type is DcMotor.ZeroPowerBehavior (that is why we
    //      imported DcMotor above). Set it to BRAKE. BRAKE makes the motor hold its place when
    //      it is not being told to move, so the slider does not slowly sink back down under
    //      the weight of the U-channel.
    //
    //   d) HOME_TICKS - an int (a whole number). Set it to 0. This is the lowest the slider
    //      ever goes. It is zero because Initialize counts from wherever the slider starts.
    //
    //   e) FLOWER_TICKS - an int. This is how many ticks up the slider travels to reach the
    //      flower height. It is a positive number and it has to be measured on the real robot
    //      (we use the Motor Config program to do that).
    //      >>> MENTOR: write the measured number of ticks here: 300
    //
    //   f) RAISE_POWER - a double (a number with a decimal point). Power goes from 0 (stopped)
    //      to 1 (full speed). Start with 0.7. Going up fights gravity, so this one is bigger.
    //
    //   g) LOWER_POWER - a double. Start with 0.4. Going down gets help from gravity, so a
    //      smaller number keeps the U-channel from slamming down. We will tune both numbers
    //      later by testing on the robot.
    //
    // (Write your seven constants below this line.)


    // =========================================================================================
    // STEP 2: THE CONSTRUCTOR
    // =========================================================================================
    // A constructor is the special block of code that runs when someone creates a FlowerSlider.
    // Open BasePositionalMotor.java and look at its constructor to see what one looks like.
    //
    // Your constructor must:
    //   - be public
    //   - have EXACTLY the same name as the class (including the capital letters), and NO
    //     return type, not even void
    //   - take ONE parameter: an OpMode (that is why we imported OpMode above). Name the
    //     parameter opMode.
    //
    // Inside the constructor there is only ONE thing to do, and it must be the very first
    // line: call the PARENT class's constructor using the Java word  super  followed by
    // parentheses. Inside those parentheses, pass these SIX things, in exactly this order,
    // separated by commas:
    //     1) the opMode that was handed to your constructor
    //     2) your HARDWARE_NAME constant
    //     3) your DIRECTION constant
    //     4) your ZERO_POWER_BEHAVIOR constant
    //     5) your HOME_TICKS constant      (the parent calls this the minimum position)
    //     6) your FLOWER_TICKS constant    (the parent calls this the maximum position)
    // Compare with the parameter list of the BasePositionalMotor constructor: your six things
    // must line up with its six parameters, in the same order, and each one must be the same
    // type. (Notice the two tick values are whole numbers, and that is on purpose.)
    //
    // Why this matters: the parent will never let the slider go below HOME_TICKS or above
    // FLOWER_TICKS. Those two numbers are the safety limits that protect the robot.
    //
    // Do NOT put anything else in the constructor. In particular, do not call Initialize here;
    // the OpMode does that.
    //
    // (Write your constructor below this line.)


    // =========================================================================================
    // STEP 3: THE RaiseToFlower METHOD
    // =========================================================================================
    // Now write the first method the driver's button will call. Look at the Stop method inside
    // BasePositionalMotor.java to see what a method with no parameters looks like.
    //
    // Your method must:
    //   - be public
    //   - return nothing (the word for that is void)
    //   - be named RaiseToFlower  (capital R, capital T, capital F - exactly like that)
    //   - take NO parameters
    //
    // Inside it there is just ONE line of code. Call the method you INHERITED from
    // BasePositionalMotor that drives the motor to its MAXIMUM position. It is called
    // SetToMaximum. It needs one thing passed to it: the power to use. Give it your
    // RAISE_POWER constant.
    //
    // You do not need to tell it how far to go. The parent already knows its maximum, because
    // you gave it FLOWER_TICKS in the constructor.
    //
    // Before you move on, add a comment above the method in your own words saying what it does.
    //
    // (Write your RaiseToFlower method below this line.)


    // =========================================================================================
    // STEP 4: THE LowerToHome METHOD
    // =========================================================================================
    // This one is almost a copy of Step 3, with two differences. Write it the same way:
    //   - public, void, NO parameters
    //   - named LowerToHome  (capital L, capital T, capital H)
    //
    // Inside it there is ONE line. Call the inherited method that drives the motor to its
    // MINIMUM position. It is called SetToMinimum (look at the list of methods in
    // BasePositionalMotor.java to find it). Give it your LOWER_POWER constant.
    //
    // Add a comment above it in your own words.
    //
    // (Write your LowerToHome method below this line.)


    // =========================================================================================
    // CHECK YOUR WORK
    // =========================================================================================
    //   [ ] The first line of the class says it "extends BasePositionalMotor".
    //   [ ] You have seven constants, each marked private static final, in ALL_CAPS.
    //   [ ] The constructor is spelled exactly like the class and takes one OpMode.
    //   [ ] The very first line inside the constructor is the call to super with 6 things in
    //       the right order.
    //   [ ] RaiseToFlower and LowerToHome each have no parameters and one line, passing the
    //       right power constant to the right inherited method.
    //   [ ] Every line of code ends with a semicolon, and every opening { has a closing }.
    //   [ ] The program builds with no red underlines.
    //
    // Common mistakes:
    //   - A red underline on super: it is not the first line in the constructor, or the six
    //     things are in a different order than the parent's constructor expects, or a number
    //     has a decimal point where the parent wants a whole number.
    //   - "Cannot find symbol": a name is misspelled, or has the wrong capital letters. Java
    //     treats  raiseToFlower  and  RaiseToFlower  as two different words.
    //   - The slider goes the wrong way: DIRECTION is the opposite of what it should be.
    //   - The slider does nothing, or the robot crashes at init: the OpMode did not call
    //     Initialize, or HARDWARE_NAME does not match the Robot Configuration. Those problems
    //     are not inside the two methods.
    //   - The robot thinks home is in the wrong place: the slider was not all the way down
    //     when Init was pressed.
    //
    // OPTIONAL CHALLENGE (only after everything above works)
    //   Add a method called IsMoving that tells the OpMode whether the slider is still on its
    //   way. Unlike your other methods, it has to GIVE BACK an answer, so it cannot be void.
    //   It gives back a boolean (true or false). It takes no parameters. Its one line gives
    //   back the answer from the inherited method that reports whether the motor is busy.
    //   (Hint: look for it in the "Feedback" section of BasePositionalMotor.java.)

}