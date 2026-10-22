package com.nickfanelli.adultbot;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp(name="Rugrat")
public class Rugrat extends LinearOpMode {

    private void waitMillis(double millis) {

        ElapsedTime timer = new ElapsedTime();
        while(opModeIsActive() && timer.milliseconds() < millis) {}

    }

    @Override
    public void runOpMode() throws InterruptedException {

        DcMotor left = super.hardwareMap.dcMotor.get("left");
        DcMotor right = super.hardwareMap.dcMotor.get("right");

        left.setDirection(DcMotorSimple.Direction.REVERSE);

        waitForStart();

        // Drive both motors forward
        left.setPower(1.0);
        right.setPower(1.0);

        waitMillis(2000);

        left.setPower(0.0);
        right.setPower(0.0);

    }

}
