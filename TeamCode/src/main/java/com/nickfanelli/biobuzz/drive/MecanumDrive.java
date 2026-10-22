package com.nickfanelli.biobuzz.drive;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class MecanumDrive extends AbstractDrive {

    private DcMotorEx fl;
    private DcMotorEx fr;
    private DcMotorEx bl;
    private DcMotorEx br;

    public MecanumDrive(HardwareMap hardwareMap) {
        super(hardwareMap);
    }

    @Override
    public void initializeDrive() {

        this.fl = super.hardwareMap.get(DcMotorEx.class, "fl");
        this.fr = super.hardwareMap.get(DcMotorEx.class, "fr");
        this.bl = super.hardwareMap.get(DcMotorEx.class, "bl");
        this.br = super.hardwareMap.get(DcMotorEx.class, "br");

    }

    @Override public void drive(double y, double r) { this.drive(0, y, r); }

    /**
     * Drives from x strafe, y forward and backwards, and rotate clockwise and counter-clockwise
     * @param x strafe right (+) / left (-)
     * @param y forward (+) / backward (-)
     * @param r rotate clockwise (+) / counterclockwise (-)
     */
    public void drive(double x, double y, double r) {
        double frontLeftPower  = y + x + r;
        double frontRightPower = y - x - r;
        double backLeftPower   = y - x + r;
        double backRightPower  = y + x - r;

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
