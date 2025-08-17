package nickfanelli.learningftc.teachingenv.teaching02_wallcrashproject;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DistanceSensor;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

/**
 * The purpose of this example is to show the kids how to optimize the code & remove magic numbers
 */
public class Teaching_02_03_WallCrashProject extends LinearOpMode {

    public static final double STOP_DIST_MM = 4.0;

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

    private void stopAllMotors() {

        leftMotor.setPower(0.0);
        rightMotor.setPower(0.0);

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

            if(distanceMM < STOP_DIST_MM) { // Remove magic number & make work (also improves readability)
                stopAllMotors();
            }

        }

        // Stop the motors
        stopAllMotors();

    }

}
