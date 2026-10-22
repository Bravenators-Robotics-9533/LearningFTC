package bravenatorsrobotics.teamcode;

import com.nickfanelli.biobuzz.AbstractTeleop;
import com.nickfanelli.biobuzz.drive.MecanumDrive;
import com.nickfanelli.biobuzz.gamepad.FtcGamePad;
import com.nickfanelli.biobuzz.teleopControls.MecanumDriveTeleopControl;
import com.nickfanelli.biobuzz.teleopControls.SingleMotorToggleTeleopControl;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;

@TeleOp(name="BiobuzzTeleop", group="nickfanelli.teaching")
public class BiobuzzTeleop extends AbstractTeleop {

    private final MecanumDrive drive;

    private MecanumDriveTeleopControl mecanumDriveControl;
    private SingleMotorToggleTeleopControl intakeToggleControl;

    public BiobuzzTeleop() {
        this.drive = new MecanumDrive(super.hardwareMap);
    }

    @Override
    public void onInitialize() {
        DcMotorEx intakeMotor = super.hardwareMap.get(DcMotorEx.class, "intake");

        this.mecanumDriveControl = new MecanumDriveTeleopControl(this, this.drive);

        // Example for Toggleable Control
        this.intakeToggleControl = new SingleMotorToggleTeleopControl(
                this, // Current opMode required
                intakeMotor, // The motor you want to toggle
                super.operatorGamepad, // What gamepad you want to control this toggle
                FtcGamePad.GAMEPAD_A // The button you want to toggle
        );

    }

    @Override
    public void onWaitingForStart() {

    }

    @Override
    public void onUpdate() {

        this.mecanumDriveControl.update();
        this.intakeToggleControl.update();

    }

    @Override
    public void onDriverGamePadEvent(int button, boolean isPressed) {

    }

    @Override
    public void onOperatorGamePadEvent(int button, boolean isPressed) {

    }

    @Override
    public void onTerminate() {

    }

}
