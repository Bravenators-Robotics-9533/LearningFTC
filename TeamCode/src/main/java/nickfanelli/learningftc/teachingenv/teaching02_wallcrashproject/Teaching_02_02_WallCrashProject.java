package nickfanelli.learningftc.teachingenv.teaching02_wallcrashproject;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DistanceSensor;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

/**
 * The purpose of this example is to show the kids how to make the robot drive forward
 * throughout the entire lifetime of the program and stop conditionally.
 *
 * I copied & pasted the previous code from Teaching_01 at the end that includes the motor
 * setup code.
 */
public class Teaching_02_02_WallCrashProject extends LinearOpMode {

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

        // Set the new zero power behavior to brake
        leftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

    }

    @Override
    public void runOpMode() throws InterruptedException {

        DistanceSensor distanceSensor = hardwareMap.get(DistanceSensor.class, "dist");

        setupMotors();
        waitForStart();

        // Make the motors drive forward
        leftMotor.setPower(1.0);
        rightMotor.setPower(1.0);

        while(opModeIsActive()) {

            double distanceMM = distanceSensor.getDistance(DistanceUnit.MM);

            if(distanceMM < 5.0) {
                leftMotor.setPower(0.0);
                rightMotor.setPower(0.0);
            }

        }

        // Stop the motors
        leftMotor.setPower(0.0);
        rightMotor.setPower(0.0);

    }

}
