package com.nickfanelli.adultbot;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

public class RugratDemoBot extends LinearOpMode {

    @Override
    public void runOpMode() {

        DcMotor left = super.hardwareMap.dcMotor.get("left");
        DcMotor right = super.hardwareMap.dcMotor.get("right");

        left.setDirection(DcMotorSimple.Direction.REVERSE);

        waitForStart();

        while(opModeIsActive()) {

            double leftStickPower = super.gamepad1.left_stick_y;
            double rightStickPower = super.gamepad1.right_stick_y;

            left.setPower(leftStickPower);
            right.setPower(rightStickPower);

        }

        left.setPower(0);
        right.setPower(0);

    }

}
