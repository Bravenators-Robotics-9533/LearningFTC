package bravenatorsrobotics.teamcode;

import com.nickfanelli.biobuzz.AbstractTeleop;
import com.nickfanelli.biobuzz.drive.MecanumDrive;
import com.nickfanelli.biobuzz.teleopControls.MecanumDriveTeleopControl;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name="BiobuzzTeleop", group="nickfanelli.teaching")
public class BiobuzzTeleop extends AbstractTeleop {

    private static final double LAUNCHER_VELOCITY = 1200; // rpm =

    private final MecanumDrive drive;

    private MecanumDriveTeleopControl mecanumDriveControl;

    private DcMotorEx intakeMotor;
    private DcMotorEx launcherMotor;

    private Servo leftControlServo;
    private Servo rightControlServo;
    private Servo finalLeftControlServo;
    private Servo finalRightControlServo;

    public BiobuzzTeleop() {
        this.drive = new MecanumDrive();
    }

    @Override
    public void onInitialize() {

        this.intakeMotor = super.hardwareMap.get(DcMotorEx.class, "intake");
        this.launcherMotor = super.hardwareMap.get(DcMotorEx.class, "launcher");

        this.drive.initializeDrive(super.hardwareMap, false, true, true, true);
        this.mecanumDriveControl = new MecanumDriveTeleopControl(this, this.drive);

        this.leftControlServo = super.hardwareMap.get(Servo.class, "lcServo");
        this.rightControlServo = super.hardwareMap.get(Servo.class, "rcServo");

        this.finalLeftControlServo = super.hardwareMap.get(Servo.class, "flServo");
        this.finalLeftControlServo.setDirection(Servo.Direction.REVERSE);

        this.finalRightControlServo = super.hardwareMap.get(Servo.class, "frServo");

    }

    @Override
    public void onWaitingForStart() {

    }

    @Override
    public void onUpdate() {

        this.mecanumDriveControl.update();

        this.intakeMotor.setPower(this.gamepad2.a ? -1.0 : 0.0);

        if(gamepad2.b) {
            this.launcherMotor.setVelocity(LAUNCHER_VELOCITY);

            this.leftControlServo.setPosition(0.0);
            this.rightControlServo.setPosition(1.0);
            this.finalLeftControlServo.setPosition(0.0);
            this.finalRightControlServo.setPosition(0.0);
        } else {
            this.launcherMotor.setPower(0.0);

            this.leftControlServo.setPosition(0.5);
            this.rightControlServo.setPosition(0.5);
            this.finalLeftControlServo.setPosition(0.5);
            this.finalRightControlServo.setPosition(0.5);
        }

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
