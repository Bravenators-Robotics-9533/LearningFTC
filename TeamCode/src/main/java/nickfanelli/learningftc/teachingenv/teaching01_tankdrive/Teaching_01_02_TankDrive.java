package nickfanelli.learningftc.teachingenv.teaching01_tankdrive;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

/**
 * The goal of this class is to use telemetry and some basic logic to learn the concepts of both
 */
public class Teaching_01_02_TankDrive extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {

        waitForStart(); // Wait for start

        while(opModeIsActive()) { // Keep looping until I press stop

            telemetry.addData("Is A Pressed", gamepad1.a);
            telemetry.update();

        }

        // Finish up (stop all motors, save the state, etc.)

    }

}
