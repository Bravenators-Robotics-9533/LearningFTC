package com.nickfanelli.biobuzz.teleopControls;

import com.nickfanelli.biobuzz.AbstractTeleop;
import com.nickfanelli.biobuzz.drive.MecanumDrive;
import com.qualcomm.robotcore.util.Range;

public class MecanumDriveTeleopControl extends AbstractTeleopControl {

    protected final MecanumDrive drive;

    public MecanumDriveTeleopControl(AbstractTeleop opMode, MecanumDrive drive) {
        super(opMode);

        this.drive = drive;
    }

    @Override
    public void update() {

        double x = Range.clip(
                super.opMode.gamepad1.left_stick_x
                + super.opMode.gamepad1.right_trigger - super.opMode.gamepad1.left_trigger,
                -1.0, 1.0
        );
        double y = super.opMode.gamepad1.left_stick_y;
        double r = super.opMode.gamepad1.right_stick_x;

        this.drive.drive(x, y, r);

    }

}
