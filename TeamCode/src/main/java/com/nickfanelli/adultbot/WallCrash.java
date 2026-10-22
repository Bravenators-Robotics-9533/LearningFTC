package com.nickfanelli.adultbot;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DistanceSensor;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

@Autonomous(name="Wall Crash")
public class WallCrash extends LinearOpMode {

    private final double MIN_DIST_MM = 45;

    @Override
    public void runOpMode() throws InterruptedException {

        // "left" and "right" -- "l" and "r"
        DcMotor left = hardwareMap.get(DcMotor.class, "left");
        DcMotor right = hardwareMap.get(DcMotor.class, "right");

        left.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        right.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        // "dist" in the hardware map or "distance" -- whatever you called it.
        DistanceSensor distance  = hardwareMap.get(DistanceSensor.class, "dist");

        waitForStart(); // WAIT UNTIL YOU PRESS THE START BUTTON

        left.setPower(1);
        right.setPower(1);

        while(opModeIsActive()) {

            double dist = distance.getDistance(DistanceUnit.MM);

            if(dist <= MIN_DIST_MM) {
                left.setPower(0);
                right.setPower(0);
            }

            telemetry.addData("Distance", dist);
            telemetry.update();

        }

    }

}
