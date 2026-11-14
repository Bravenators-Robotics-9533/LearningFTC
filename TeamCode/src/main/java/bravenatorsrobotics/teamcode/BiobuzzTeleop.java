package bravenatorsrobotics.teamcode;

import com.nickfanelli.biobuzz.AbstractTeleop;
import com.nickfanelli.biobuzz.drive.MecanumDrive;
import com.nickfanelli.biobuzz.teleopControls.MecanumDriveTeleopControl;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.nickfanelli.biobuzz.gamepad.FtcGamePad;

@TeleOp(name="BiobuzzTeleop", group="nickfanelli.teaching")
public class BiobuzzTeleop extends AbstractTeleop {

    private static final double LAUNCHER_VELOCITY = 965; // rpm =

    private final MecanumDrive drive;

    private MecanumDriveTeleopControl mecanumDriveControl;

    private DcMotorEx intakeMotor;
    private DcMotorEx launcherMotor;
    private DcMotorEx secondaryIntakeMotor;

    private Servo tailServo;

    public BiobuzzTeleop() {
        this.drive = new MecanumDrive();
    }

    @Override
    public void onInitialize() {

        this.intakeMotor = super.hardwareMap.get(DcMotorEx.class, "intake");
        this.intakeMotor.setDirection(DcMotorSimple.Direction.REVERSE);

        this.secondaryIntakeMotor = super.hardwareMap.get(DcMotorEx.class, "intake2");

        this.launcherMotor = super.hardwareMap.get(DcMotorEx.class, "launcher");

        this.drive.initializeDrive(super.hardwareMap, false, true, true, true);
        this.mecanumDriveControl = new MecanumDriveTeleopControl(this, this.drive);

        this.tailServo = super.hardwareMap.get(Servo.class, "tail");

    }

    @Override
    public void onWaitingForStart() {

    }

    @Override
    public void onUpdate() {

        this.mecanumDriveControl.update();

        if(this.gamepad2.a) {
            this.intakeMotor.setPower(1.0);
        } else {
            this.intakeMotor.setPower(0.0);
        }

        if(gamepad2.right_bumper) {
            this.launcherMotor.setVelocity(LAUNCHER_VELOCITY);
        } else {
            this.launcherMotor.setPower(0.0);
        }

        if(gamepad2.dpad_left) {
            this.secondaryIntakeMotor.setPower(1.0);
        } else {
            this.secondaryIntakeMotor.setPower(0.0);
        }

    }

    @Override
    public void onDriverGamePadEvent(int button, boolean isPressed) {

    }

    private boolean isTailToggled = false;

    @Override
    public void onOperatorGamePadEvent(int button, boolean isPressed) {

        switch(button) {

            case FtcGamePad.GAMEPAD_Y:

                if (isPressed) {
                    this.tailServo.setPosition(isTailToggled ? 0.25 : 0.9);
                    isTailToggled = !isTailToggled;
                }

                break;

        }

    }

    @Override
    public void onTerminate() {

    }

}
