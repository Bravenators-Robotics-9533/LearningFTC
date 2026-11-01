package com.nickfanelli.biobuzz.drive;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;

public class AutonomousMecanumDrive extends MecanumDrive {

    private static final int TICKS_PER_MOTOR_REV = 28;
    private static final double GEAR_REDUCTION = 20;

    private static final double WHEEL_DIAM = 3.78;
    private static final double WHEEL_CIRCUMFERENCE = WHEEL_DIAM * Math.PI;

    private static final double TICKS_PER_IN = ((TICKS_PER_MOTOR_REV * GEAR_REDUCTION) / WHEEL_CIRCUMFERENCE) - 1.976175;

    private static final double TRACK_DISTANCE = 12; // in
    private static final double TURN_CIRCUMFERENCE = TRACK_DISTANCE * Math.PI;

    protected final LinearOpMode opMode;

    public AutonomousMecanumDrive(LinearOpMode opMode) {
        this.opMode = opMode;
    }

    private void waitMillis(int millis) {
        ElapsedTime timer = new ElapsedTime();
        timer.reset();

        while(opMode.opModeIsActive() && timer.milliseconds() < millis) {

        }

    }

    public void driveByInches(double power, double inches) {

        int ticks = (int) (inches * TICKS_PER_IN);

        this.driveByEncoder(power, ticks, ticks, ticks, ticks);

    }

    public void turnByDegrees(double power, TurnDirection turnDirection, int degrees) {

        int ticks = (int) ((TURN_CIRCUMFERENCE * TICKS_PER_IN) / 180) * degrees * turnDirection.multiplier;

        driveByEncoder(power, -ticks, ticks, -ticks, ticks);

    }

    public void driveByEncoder(double power, int flTicks, int frTicks, int blTicks, int brTicks) {

        super.fl.setTargetPosition(super.fl.getCurrentPosition() + flTicks);
        super.fr.setTargetPosition(super.fr.getCurrentPosition() + frTicks);
        super.bl.setTargetPosition(super.bl.getCurrentPosition() + blTicks);
        super.br.setTargetPosition(super.br.getCurrentPosition() + brTicks);

        super.fl.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        super.fr.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        super.bl.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        super.br.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        super.fl.setPower(power);
        super.fr.setPower(power);
        super.bl.setPower(power);
        super.br.setPower(power);

        while(opMode.opModeIsActive()) {

            this.opMode.telemetry.addData("fl", fl.getCurrentPosition());
            this.opMode.telemetry.addData("fr", fr.getCurrentPosition());
            this.opMode.telemetry.addData("bl", bl.getCurrentPosition());
            this.opMode.telemetry.addData("br", br.getCurrentPosition());
            this.opMode.telemetry.update();

            if(!super.fl.isBusy() && !super.fr.isBusy() && !super.bl.isBusy() && !super.br.isBusy()) {
                break;
            }

        }

        super.fl.setPower(0);
        super.fr.setPower(0);
        super.bl.setPower(0);
        super.br.setPower(0);

        super.fl.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        super.fr.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        super.bl.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        super.br.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

    }

}
