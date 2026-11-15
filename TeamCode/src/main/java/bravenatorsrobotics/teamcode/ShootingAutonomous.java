package bravenatorsrobotics.teamcode;

import com.nickfanelli.biobuzz.AbstractAutonomous;
import com.nickfanelli.biobuzz.drive.AutonomousMecanumDrive;
import com.nickfanelli.biobuzz.drive.TurnDirection;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

@Autonomous(name="Shooting Autonomous", group="nickfanelli.teaching")
public class ShootingAutonomous extends AbstractAutonomous {

    private static final double LAUNCHER_VELOCITY = 985; // rpm

    private AutonomousMecanumDrive drive;

    private DcMotorEx intakeMotor;
    private DcMotorEx secondaryIntakeMotor;

    private DcMotorEx launcherMotor;

    private Servo lcServo;

    @Override
    public void onInitialize() {
        this.drive = new AutonomousMecanumDrive(this);
        this.drive.initializeDrive(super.hardwareMap, false, true, false, false);

        this.intakeMotor = super.hardwareMap.get(DcMotorEx.class, "intake");
        this.intakeMotor.setDirection(DcMotorSimple.Direction.REVERSE);

        this.secondaryIntakeMotor = super.hardwareMap.get(DcMotorEx.class, "intake2");

        this.launcherMotor = super.hardwareMap.get(DcMotorEx.class, "launcher");

        this.lcServo = super.hardwareMap.get(Servo.class, "lcServo");

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

        this.drive.driveByInches(1.0, -9.0); // Back Up

        waitMillis(500);

        // Turn on all intake components
        this.lcServo.setPosition(0.0);
        this.intakeMotor.setPower(1.0);
        this.secondaryIntakeMotor.setPower(1.0);

        waitMillis(10000);

        // Turn off all intake components
        this.lcServo.setPosition(0.5);
        this.intakeMotor.setPower(0.0);
        this.launcherMotor.setPower(0.0);
        this.secondaryIntakeMotor.setPower(0.0);

        this.drive.driveByInches(1.0, 7.0); // Move Up

        this.drive.turnByDegrees(1.0, TurnDirection.COUNTER_CLOCKWISE, 90);

        this.drive.driveByInches(1.0, 28.0); // Drive to Park Location

        this.drive.turnByDegrees(1.0, TurnDirection.CLOCKWISE, 90);

        this.drive.driveByInches(1.0, 76.0); // Drive to Park Location

        this.drive.turnByDegrees(1.0, TurnDirection.COUNTER_CLOCKWISE, 90);

        this.drive.driveByInches(1.0, 15.0); // Drive to Park Location



    }

    @Override
    public void onTerminate() {

    }
}
