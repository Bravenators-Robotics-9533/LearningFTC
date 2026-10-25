package com.nickfanelli.biobuzz.drive;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class MecanumDrive extends AbstractDrive {

    private DcMotorEx fl;
    private DcMotorEx fr;
    private DcMotorEx bl;
    private DcMotorEx br;

    @Override
    public void initializeDrive(HardwareMap hardwareMap) {
        this.fl = hardwareMap.get(DcMotorEx.class, "fl");
        this.fr = hardwareMap.get(DcMotorEx.class, "fr");
        this.bl = hardwareMap.get(DcMotorEx.class, "bl");
        this.br = hardwareMap.get(DcMotorEx.class, "br");
    }

    public void initializeDrive(HardwareMap hardwareMap, boolean isFlReversed, boolean isFrReversed, boolean isBlReversed, boolean isBrReversed) {

        this.fl = hardwareMap.get(DcMotorEx.class, "fl");
        this.fr = hardwareMap.get(DcMotorEx.class, "fr");
        this.bl = hardwareMap.get(DcMotorEx.class, "bl");
        this.br = hardwareMap.get(DcMotorEx.class, "br");

        this.fl.setDirection(isFlReversed ? DcMotorSimple.Direction.REVERSE : DcMotorSimple.Direction.FORWARD);
        this.bl.setDirection(isBlReversed ? DcMotorSimple.Direction.REVERSE : DcMotorSimple.Direction.FORWARD);
        this.br.setDirection(isBrReversed ? DcMotorSimple.Direction.REVERSE : DcMotorSimple.Direction.FORWARD);
        this.fr.setDirection(isFrReversed ? DcMotorSimple.Direction.REVERSE : DcMotorSimple.Direction.FORWARD);

    }

    @Override public void drive(double y, double r) { this.drive(0, y, r); }

    /**
     * Drives from r strafe, y forward and backwards, and rotate clockwise and counter-clockwise
     * @param r rotate clockwise (+) / counterclockwise (-)
     * @param y forward (+) / backward (-)
     * @param x strafe right (+) / left (-)
     */
    public void drive(double x, double y, double r) {
        double frontLeftPower  = -y + r + x;
        double frontRightPower = -y - r - x;
        double backLeftPower   = y - r + x;
        double backRightPower  = y + r - x;

        // Normalize motor powers
        double max = Math.max(
                1.0,
                Math.max(
                        Math.max(Math.abs(frontLeftPower), Math.abs(frontRightPower)),
                        Math.max(Math.abs(backLeftPower), Math.abs(backRightPower))
                )
        );

        fl.setPower(frontLeftPower / max);
        fr.setPower(frontRightPower / max);
        bl.setPower(backLeftPower / max);
        br.setPower(backRightPower / max);
    }

}
