package com.nickfanelli.adultbot;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

@TeleOp(name="Teleop")
public class MecanumDrive extends LinearOpMode {

    public static final int DRIVER_CONTROLLER_EASE_POW = 1;
    public static final double ROBOT_SPEED_LIMIT = 1.0;

    private double offsetHeading = 0.0;

    @Override
    public void runOpMode() throws InterruptedException {

        IMU imu = hardwareMap.get(IMU.class, "imu");
        IMU.Parameters parameters = new IMU.Parameters(new RevHubOrientationOnRobot(
                RevHubOrientationOnRobot.LogoFacingDirection.UP, RevHubOrientationOnRobot.UsbFacingDirection.RIGHT));
        imu.initialize(parameters);

        DcMotorEx fl = super.hardwareMap.get(DcMotorEx.class, "fl");
        DcMotorEx fr = super.hardwareMap.get(DcMotorEx.class, "fr");
        DcMotorEx bl = super.hardwareMap.get(DcMotorEx.class, "bl");
        DcMotorEx br = super.hardwareMap.get(DcMotorEx.class, "br");

        fl.setDirection(DcMotorSimple.Direction.REVERSE);
        bl.setDirection(DcMotorSimple.Direction.REVERSE);

        waitForStart();

        while(opModeIsActive()) {

            double y = Range.clip(Math.pow(-gamepad1.left_stick_y, DRIVER_CONTROLLER_EASE_POW), -1.0, 1.0);
            double xt = (Math.pow(gamepad1.right_trigger, DRIVER_CONTROLLER_EASE_POW) - Math.pow(gamepad1.left_trigger, DRIVER_CONTROLLER_EASE_POW));
            double x = Range.clip(Math.pow(gamepad1.left_stick_x, DRIVER_CONTROLLER_EASE_POW) + xt, -1.0, 1.0);
            double rx = Range.clip(Math.pow(gamepad1.right_stick_x, DRIVER_CONTROLLER_EASE_POW), -1.0, 1.0);

            // Read inverse IMU heading, as the UMG heading is CW positive
            double botHeading = -imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.RADIANS) + offsetHeading;

            double rotX = x * Math.cos(botHeading) - y * Math.sin(botHeading);
            double rotY = x * Math.sin(botHeading) + y * Math.cos(botHeading);

            // Denominator is the largest motor power (absolute value) or 1
            // This ensures all the powers maintain the same ratio, but only when
            // at least one is out of the range [-1, 1]
            double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1);

            double frontLeftPower = (rotY + rotX + rx) / denominator;
            double backLeftPower = (rotY - rotX + rx) / denominator;
            double frontRightPower = (rotY - rotX - rx) / denominator;
            double backRightPower = (rotY + rotX - rx) / denominator;

            frontLeftPower  = Range.clip(frontLeftPower, -ROBOT_SPEED_LIMIT, ROBOT_SPEED_LIMIT);
            frontRightPower = Range.clip(frontRightPower, -ROBOT_SPEED_LIMIT, ROBOT_SPEED_LIMIT);
            backLeftPower   = Range.clip(backLeftPower, -ROBOT_SPEED_LIMIT, ROBOT_SPEED_LIMIT);
            backRightPower  = Range.clip(backRightPower, -ROBOT_SPEED_LIMIT, ROBOT_SPEED_LIMIT);

            fl.setPower(frontLeftPower);
            fr.setPower(frontRightPower);
            bl.setPower(backLeftPower);
            br.setPower(backRightPower);

        }

    }

}
