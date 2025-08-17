package nickfanelli.learningftc.teachingenv.teaching01_tankdrive;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * The goal of this example is to learn how to use a method and clean up the code
 * Also they will learn how to reset motors & learn about motor modes
 */
public class Teaching_01_08_TankDrive extends LinearOpMode {

    private DcMotor leftMotor;
    private DcMotor rightMotor;

    /**
     * Zero the drive motors
     */
    private void setupMotors() {

        // Get the left & right motors
        leftMotor = hardwareMap.dcMotor.get("l");
        rightMotor = hardwareMap.dcMotor.get("r");

        // Reverse a motor (if necessary)
        leftMotor.setDirection(DcMotor.Direction.REVERSE);

        leftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        leftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

    }

    @Override
    public void runOpMode() throws InterruptedException {

        setupMotors();

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
