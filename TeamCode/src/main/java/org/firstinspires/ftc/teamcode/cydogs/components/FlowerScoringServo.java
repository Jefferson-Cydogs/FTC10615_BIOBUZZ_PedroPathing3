package org.firstinspires.ftc.teamcode.cydogs.components;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.cydogs.basedevices.BaseServo;

/**
 * FlowerScoringServo - the little gate that opens to let the scoring elements drop into the
 * flower, and closes again to hold them in.
 *
 * WHAT THIS CLASS IS
 *   A FlowerScoringServo is a special kind of BaseServo. BaseServo already knows how to talk to
 *   a regular (positional) servo: it can send the servo to any position from 0.0 to 1.0, and it
 *   will never let a position go outside the safe limits we give it. Our class INHERITS all of
 *   that, and we only add the things that are specific to the gate.
 *
 * WHAT WE ADD
 *   The robot's settings for THIS servo (its name, which way it turns, and the two positions
 *   we care about: gate OPEN and gate CLOSED) and TWO methods: Open and Close.
 *
 * WHY THE SETTINGS LIVE HERE
 *   The OpMode (the program the drivers run) should not have to know any of the settings. It
 *   will only say "make a gate", "open it" and "close it". If the gate is ever re-built and the
 *   positions change, we fix it in this ONE file and every OpMode keeps working.
 *
 * HOW AN OPMODE WILL USE YOUR CLASS (you do not write this part - just understand it)
 *   1. It makes a FlowerScoringServo and hands it the OpMode (the only thing it passes in).
 *   2. It calls Initialize once. You do NOT write Initialize - you inherit it. Initialize
 *      connects to the servo but does NOT move it. A servo only moves when it is told to.
 *   3. Right after that, it calls your Close method, so the gate starts the match shut.
 *   4. When the driver presses a button, it calls your Open method. Later it calls Close again.
 *
 * ABOUT WAITING
 *   A servo moves at its own speed after it is told where to go. Both of your methods send the
 *   command and return right away. The gate needs a moment to physically swing, but you do not
 *   write any waiting code. The robot can keep driving the whole time.
 *
 * YOUR JOB: finish Steps 1, 2, 3 and 4 below. Everything you write goes INSIDE the curly
 * braces of the class, in the spaces marked for each step. Do the steps in order and save after
 * each one.
 */
public class FlowerScoringServo extends BaseServo {

    // =========================================================================================
    // STEP 1: THE SETTINGS (constants)
    // =========================================================================================
    // Make six constants. A constant is a variable that never changes after it is created.
    // Putting a number or name in ONE place with a good label is much better than typing the
    // same mysterious value in several places (programmers call those "magic numbers").
    //
    // Every constant must be:
    //   - private  (only this class needs to see it)
    //   - static   (it belongs to the class itself, not to one particular gate; this is also
    //               what lets you use it in Step 2)
    //   - final    (this is the word that makes it impossible to change later)
    // Name them in ALL_CAPITAL_LETTERS with underscores between words. That is the Java habit
    // for constants. Look at the top of DumpArm.java for examples of how they look.
    //
    // Make these six, giving each a short comment explaining what it is:
    //
    //   a) HARDWARE_NAME - a String (text). The text must be exactly:  FlowerScoringServo
    //      It has to match, letter for letter and capital for capital, the name of the servo
    //      in the Robot Configuration on the Driver Hub. If it is misspelled the robot will
    //      crash at init and tell you it "unable to find a hardware device with name ...".
    //
    //   b) DIRECTION - the type is Servo.Direction (that is why we imported Servo above). It
    //      is either FORWARD or REVERSE. Use FORWARD unless your mentor tells you otherwise.
    //
    //   c) MIN_POSITION - a double (a number with a decimal point). Servo positions go from
    //      0.0 to 1.0. This is the LOWEST position this servo is ever allowed to go to. Set it
    //      to 0.0, unless your mentor gives you a different number.
    //
    //   d) MAX_POSITION - a double. The HIGHEST position this servo is ever allowed to go to.
    //      Set it to 1.0, unless your mentor gives you a different number.
    //      (MIN_POSITION and MAX_POSITION are safety limits. BaseServo will quietly pull any
    //      position outside of them back to the nearest limit, so the gate can never be sent
    //      somewhere it would hit something.)
    //
    //   e) CLOSED_POSITION - a double. The position where the gate is shut and holds the
    //      scoring elements in. It has to be a number between MIN_POSITION and MAX_POSITION.
    //      >>> MENTOR: write the measured closed position here before handing this out: _____
    //
    //   f) OPEN_POSITION - a double. The position where the gate is open and the scoring
    //      elements can drop out. It also has to be between MIN_POSITION and MAX_POSITION.
    //      >>> MENTOR: write the measured open position here before handing this out: _____
    //
    // (Write your six constants below this line.)

    private final String HARDWARE_NAME = "FlowerScoringServo";
        private final Servo.Direction DIRECTION = Servo.Direction.FORWARD;
        private final double MIN_POSITION = 0.0;
        private final double MAX_POSITION = 1.0;
        private final double CLOSED_POSITION = 0.5;
        private final double OPEN_POSITION = 0.8;
}

    // =========================================================================================
    // STEP 2: THE CONSTRUCTOR
    // =========================================================================================
    // A constructor is the special block of code that runs when someone creates a
    // FlowerScoringServo. Open BaseServo.java and look at its constructor to see what one
    // looks like.
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
    //     4) your MIN_POSITION constant
    //     5) your MAX_POSITION constant
    //     6) your CLOSED_POSITION constant   (the parent calls this the starting position)
    // Compare with the parameter list of the BaseServo constructor: your six things must line
    // up with its six parameters, in the same order, and each one must be the same type.
    //
    // Why CLOSED_POSITION is the starting position: the parent remembers it, so the gate's
    // "home" is shut. That is where we want it every time the robot starts.
    //
    // Do NOT put anything else in the constructor. In particular, do not call Initialize here;
    // the OpMode does that.
    //
    // (Write your constructor below this line.)

public FlowerScoringServo (OpMode opMode)
{ super(opMode,
        HARDWARE_NAME,
        DIRECTION,
        MIN_POSITION,
        MAX_POSITION,
        CLOSED_POSITION);
}

    // =========================================================================================
    // STEP 3: THE Open METHOD
    // =========================================================================================
    // Now write the first method the driver's button will call. Look at the SetToMinimum method
    // inside BaseServo.java to see what a method with no parameters looks like.
    //
    // Your method must:
    //   - be public
    //   - return nothing (the word for that is void)
    //   - be named Open  (capital O, exactly like that)
    //   - take NO parameters
    //
    // Inside it there is just ONE line of code. Call the method you INHERITED from BaseServo
    // that sends the servo to a position you choose. It is called SetPosition. It needs one
    // thing passed to it: the position. Give it your OPEN_POSITION constant.
    //
    // Before you move on, add a comment above the method in your own words saying what it does.
    //
    // (Write your Open method below this line.)

        public void Open(){
                BaseServo.SetPosition = OPEN_POSITION;


        }
    // =========================================================================================
    // STEP 4: THE Close METHOD
    // =========================================================================================
    // This one is almost a copy of Step 3, with two differences. Write it the same way:
    //   - public, void, NO parameters
    //   - named Close  (capital C)
    //
    // Inside it there is ONE line. Call the same inherited SetPosition method, but give it your
    // CLOSED_POSITION constant instead.
    //
    // Add a comment above it in your own words.
    //
    // (Write your Close method below this line.)


public void Close(){
        BaseServo.SetPosition = CLOSED_POSITION;


}
    // =========================================================================================
    // CHECK YOUR WORK
    // =========================================================================================
    //   [ ] The first line of the class says it "extends BaseServo".
    //   [ ] You have six constants, each marked private static final, in ALL_CAPS.
    //   [ ] The constructor is spelled exactly like the class and takes one OpMode.
    //   [ ] The very first line inside the constructor is the call to super with 6 things in
    //       the right order.
    //   [ ] Open and Close each have no parameters and one line, passing the right position
    //       constant to SetPosition.
    //   [ ] Every line of code ends with a semicolon, and every opening { has a closing }.
    //   [ ] The program builds with no red underlines.
    //
    // Common mistakes:
    //   - A red underline on super: it is not the first line in the constructor, or the six
    //     things are in a different order than the parent's constructor expects.
    //   - "Cannot find symbol": a name is misspelled, or has the wrong capital letters. Java
    //     treats  open  and  Open  as two different words.
    //   - Open and Close are swapped: the two position numbers are in the wrong constants.
    //   - The gate never reaches the position you wanted: the number is outside the
    //     MIN_POSITION to MAX_POSITION range, so BaseServo pulled it back to the limit.
    //   - The robot crashes at init: HARDWARE_NAME does not match the Robot Configuration, or
    //     the OpMode did not call Initialize before using the gate.between MIN_POSITION and MAX_POSITION.
    //
    // OPTIONAL CHALLENGE (only after everything above works)
    //   Add a method called IsOpen that tells the OpMode whether the gate is currently open.
    //   Unlike your other methods, it has to GIVE BACK an answer, so it cannot be void. It
    //   gives back a boolean (true or false). It takes no parameters. Its one line compares
    //   the inherited GetPosition method's answer to your OPEN_POSITION constant, and gives
    //   back the result of that comparison. (Hint: two numbers are compared for "the same"
    //   with a double equals sign, not a single one.)

