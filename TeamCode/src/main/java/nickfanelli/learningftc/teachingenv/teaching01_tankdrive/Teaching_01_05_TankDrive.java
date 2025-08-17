package nickfanelli.learningftc.teachingenv.teaching01_tankdrive;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * The goal of this example is to let the students work on their own to recreate the same logic
 * for the right motor have them figure out how to create the motor r and set the power for it.
 */
public class Teaching_01_05_TankDrive extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {

        DcMotor leftMotor = hardwareMap.dcMotor.get("l");
        DcMotor rightMotor = hardwareMap.dcMotor.get("r");

        waitForStart();

        while(opModeIsActive()) {

            if (gamepad1.a) {
                leftMotor.setPower(1.0);
            } else {
                leftMotor.setPower(0.0);
            }

            if(gamepad1.b) {
                rightMotor.setPower(1.0);
            } else {
                rightMotor.setPower(0.0);
            }

        }

        // Safety
        leftMotor.setPower(0.0);
        rightMotor.setPower(0.0);


    }

}
