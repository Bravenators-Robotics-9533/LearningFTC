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

    private static final double LAUNCHER_VELOCITY = 1275; // rpm =

    private final MecanumDrive drive;

    private MecanumDriveTeleopControl mecanumDriveControl;

    private DcMotorEx intakeMotor;
    private DcMotorEx launcherMotor;
    private DcMotorEx secondaryIntakeMotor;

    private Servo leftControlServo;
    private Servo rightControlServo;
    private Servo mid1Servo;
    private Servo mid2Servo;

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

        this.leftControlServo = super.hardwareMap.get(Servo.class, "lcServo");
        this.rightControlServo = super.hardwareMap.get(Servo.class, "rcServo");

        this.mid1Servo = super.hardwareMap.get(Servo.class, "mid1");
        this.mid1Servo.setDirection(Servo.Direction.REVERSE);

        this.mid2Servo = super.hardwareMap.get(Servo.class, "mid2");

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

        if(gamepad2.b) {
            this.launcherMotor.setVelocity(LAUNCHER_VELOCITY);

            this.mid1Servo.setPosition(1.0);
            this.mid2Servo.setPosition(1.0);
        } else {
            this.launcherMotor.setPower(0.0);

            this.mid1Servo.setPosition(0.5);
            this.mid2Servo.setPosition(0.5);
        }

        if(this.gamepad2.b || this.gamepad2.a) {
            this.secondaryIntakeMotor.setPower(1.0);
            this.leftControlServo.setPosition(0.0);
            this.rightControlServo.setPosition(1.0);
        } else {
            this.secondaryIntakeMotor.setPower(0.0);
            this.leftControlServo.setPosition(0.5);
            this.rightControlServo.setPosition(0.5);
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
