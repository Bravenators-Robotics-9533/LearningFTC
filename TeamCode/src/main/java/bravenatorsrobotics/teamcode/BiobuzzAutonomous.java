package bravenatorsrobotics.teamcode;

import com.nickfanelli.biobuzz.AbstractAutonomous;
import com.nickfanelli.biobuzz.drive.AutonomousMecanumDrive;
import com.nickfanelli.biobuzz.drive.TurnDirection;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

@Autonomous(name="BiobuzzAutonomous", group="nickfanelli.teaching")
public class BiobuzzAutonomous extends AbstractAutonomous {

    private static final double LAUNCHER_VELOCITY = 1100; // rpm

    private AutonomousMecanumDrive drive;

    private DcMotorEx intakeMotor;
    private DcMotorEx launcherMotor;

    private Servo leftControlServo;
    private Servo rightControlServo;
    private Servo finalLeftControlServo;
    private Servo finalRightControlServo;

    @Override
    public void onInitialize() {
        this.drive = new AutonomousMecanumDrive(this);
        this.drive.initializeDrive(super.hardwareMap, false, true, false, false);

        this.intakeMotor = super.hardwareMap.get(DcMotorEx.class, "intake");
        this.intakeMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        this.launcherMotor = super.hardwareMap.get(DcMotorEx.class, "launcher");

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
    public void onStart() {

        this.drive.driveByInches(1.0, 8); // Move off wall

        this.drive.turnByDegrees(1.0, TurnDirection.CLOCKWISE, 90);

        this.drive.driveByInches(1.0, 26); // Move to shoot location

        this.drive.turnByDegrees(1.0, TurnDirection.COUNTER_CLOCKWISE, 90); // Turn to shoot

        this.launcherMotor.setVelocity(LAUNCHER_VELOCITY);

        this.drive.driveByInches(1.0, -5.0); // Back Up

        // Turn on all intake components
        this.leftControlServo.setPosition(0.0);
        this.rightControlServo.setPosition(1.0);
        this.finalLeftControlServo.setPosition(0.0);
        this.finalRightControlServo.setPosition(0.0);
        this.intakeMotor.setPower(1.0);

        waitMillis(10000);

        // Turn off all intake components
        this.leftControlServo.setPosition(0.5);
        this.rightControlServo.setPosition(0.5);
        this.finalLeftControlServo.setPosition(0.5);
        this.finalRightControlServo.setPosition(0.5);
        this.intakeMotor.setPower(0.0);
        this.launcherMotor.setPower(0.0);

        this.drive.turnByDegrees(1.0, TurnDirection.COUNTER_CLOCKWISE, 90);

        this.drive.driveByInches(1.0, 24.0); // Drive to Park Location

        this.drive.turnByDegrees(1.0, TurnDirection.CLOCKWISE, 90);

        this.drive.driveByInches(1.0, 74.0); // Drive to Park Location

        this.drive.turnByDegrees(1.0, TurnDirection.COUNTER_CLOCKWISE, 90);

        this.drive.driveByInches(1.0, 16.0); // Drive to Park Location



    }

    @Override
    public void onTerminate() {

    }
}
