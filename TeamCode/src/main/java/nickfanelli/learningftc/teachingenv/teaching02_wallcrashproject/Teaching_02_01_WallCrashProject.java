package nickfanelli.learningftc.teachingenv.teaching02_wallcrashproject;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DistanceSensor;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

/**
 * The purpose of this example is to show the kids how read sensor input
 */
public class Teaching_02_01_WallCrashProject extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {

        DistanceSensor distanceSensor = hardwareMap.get(DistanceSensor.class, "dist");

        waitForStart();

        while(opModeIsActive()) {

            double distanceMM = distanceSensor.getDistance(DistanceUnit.MM);

            telemetry.addData("Distance", distanceMM);
            telemetry.update();

        }

    }

}
