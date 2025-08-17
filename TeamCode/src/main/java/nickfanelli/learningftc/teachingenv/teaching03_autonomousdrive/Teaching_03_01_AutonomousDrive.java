package nickfanelli.learningftc.teachingenv.teaching03_autonomousdrive;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * The goal of this example is to learn how to make the robot drive autonomously with time
 *
 * The ultimate gaol of this project is to teach the kids how to make the robot drive using
 * encoder values instead of time to control the distance & make methods that tell the robot
 * how to turn and drive a specific target value of inches or degrees
 *
 * I copied the motor code from Teaching_01_08_TankDrive
 */
public class Teaching_03_01_AutonomousDrive extends LinearOpMode {

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

    // ADD THIS
    private void driveForward() {

        leftMotor.setPower(1.0);
        rightMotor.setPower(1.0);

    }

    // MAKE SURE THIS EXISTS
    private void stopAllMotors() {

        leftMotor.setPower(0.0);
        rightMotor.setPower(0.0);

    }

    @Override
    public void runOpMode() throws InterruptedException {

        ElapsedTime timer = new ElapsedTime();

        setupMotors();

        waitForStart();

        // Drive Forward
        driveForward();

        // Wait 2 Seconds
        timer.reset();
        while(opModeIsActive() && timer.seconds() < 2.0);

        // Stop the motors
        stopAllMotors();

        // Program ends


    }

}
