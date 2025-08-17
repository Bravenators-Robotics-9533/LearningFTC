package nickfanelli.learningftc.teachingenv.teaching01_tankdrive;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * The goal of this example is to have the kids add to their tank drive by adding comments &
 * telemetry
 *
 * Mention how its odd the motors don't reset their position to zero at the start of each run
 * This is because the program lifetime stores that data & your opmode does not represent the entire
 * lifetime of the code.
 */
public class Teaching_01_07_TankDrive extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {

        // Get the left & right motors
        DcMotor leftMotor = hardwareMap.dcMotor.get("l");
        DcMotor rightMotor = hardwareMap.dcMotor.get("r");

        // Reverse a motor (if necessary)
        leftMotor.setDirection(DcMotor.Direction.REVERSE);

        // Wait for user to press start
        waitForStart();

        // Update until program ends
        while(opModeIsActive()) {

            // Get the current position of the left & right stick
            double leftStickPos     = gamepad1.left_stick_y;
            double rightStickPos    = gamepad1.right_stick_y;

            // Set the motor powers
            leftMotor.setPower(leftStickPos);
            rightMotor.setPower(rightStickPos);

            // Print the current motor positions
            telemetry.addData("Left Motor Position", leftMotor.getCurrentPosition());
            telemetry.addData("Right Motor Position", rightMotor.getCurrentPosition());
            telemetry.update(); // Push to the phone

        }

        // Safety
        leftMotor.setPower(0.0);
        rightMotor.setPower(0.0);


    }

}
