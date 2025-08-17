package nickfanelli.learningftc.teachingenv.teaching01_tankdrive;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * The goal of this class is to use telemetry & print out current motor position
 * Let the students turn the wheel and watch the number change
 * Explain that because of the loop it constantly updates the value
 */
public class Teaching_01_03_TankDrive extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {

        // Get the left Motor
        DcMotor leftMotor = hardwareMap.dcMotor.get("l");

        waitForStart(); // Wait for start

        while(opModeIsActive()) { // Keep looping until I press stop

            telemetry.addData("Left Motor Position", leftMotor.getCurrentPosition());
            telemetry.update();

        }

        // Finish up (stop all motors, save the state, etc.)

    }

}
