package nickfanelli.learningftc.teachingenv.teaching01_tankdrive;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * The goal of this example is to show the kids how to conditionally power a motor.
 *
 * Be sure to make a mistake on purpose and keep the motor powered on. (don't put the stop
 * if statement (else clause)).
 */
public class Teaching_01_04_TankDrive extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {

        // Get the left Motor
        DcMotor leftMotor = hardwareMap.dcMotor.get("l");

        waitForStart(); // Wait for start

        while(opModeIsActive()) { // Keep looping until I press stop

            if (gamepad1.a) {
                leftMotor.setPower(1.0);
            } else { // DON'T PUT THIS PART AT FIRST AND LET THEM TELL YOU WHAT'S WRONG
                leftMotor.setPower(0.0);
            }

        }

        leftMotor.setPower(0.0); // Safety

        // Finish up (stop all motors, save the state, etc.)

    }

}
