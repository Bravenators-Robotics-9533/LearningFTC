package nickfanelli.learningftc.teachingenv.teaching01_tankdrive;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * The goal of this example is to have the kids recreate a full tank drive by setting the l & r
 * motor position based on the joystick position
 */
public class Teaching_01_06_TankDrive extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {

        DcMotor leftMotor = hardwareMap.dcMotor.get("l");
        DcMotor rightMotor = hardwareMap.dcMotor.get("r");

        leftMotor.setDirection(DcMotor.Direction.REVERSE); // MAY NEED TO REVERSE SOME MOTORS

        waitForStart();

        while(opModeIsActive()) {

            // Introduce Primitive Variables for the First Time
            double leftStickPos     = gamepad1.left_stick_y;
            double rightStickPos    = gamepad1.right_stick_y;

            leftMotor.setPower(leftStickPos);
            rightMotor.setPower(rightStickPos);

        }

        // Safety
        leftMotor.setPower(0.0);
        rightMotor.setPower(0.0);


    }

}
