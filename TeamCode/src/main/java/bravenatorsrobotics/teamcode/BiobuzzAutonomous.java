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

    private static final double LAUNCHER_VELOCITY = 650; // rpm

    private AutonomousMecanumDrive drive;

    private DcMotorEx intakeMotor;
    private DcMotorEx launcherMotor;

    @Override
    public void onInitialize() {
        this.drive = new AutonomousMecanumDrive(this);
        this.drive.initializeDrive(super.hardwareMap, false, false, false, false);

        this.intakeMotor = super.hardwareMap.get(DcMotorEx.class, "intake1");
        this.intakeMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        this.launcherMotor = super.hardwareMap.get(DcMotorEx.class, "launcher");
    }

    @Override
    public void onWaitingForStart() {



    }

    @Override
    public void onStart() {

        this.drive.driveByInches(0.5, 8); // Move off wall

        this.drive.turnByDegrees(0.5, TurnDirection.CLOCKWISE, 90);

        this.drive.driveByInches(0.5, 24); // Move to shoot location

        this.drive.turnByDegrees(0.5, TurnDirection.COUNTER_CLOCKWISE, 90); // Turn to shoot

        this.launcherMotor.setVelocity(LAUNCHER_VELOCITY);

        this.drive.driveByInches(0.5, -5); // Back Up

        waitMillis(750);

        // Turn on all intake components
        this.intakeMotor.setPower(1.0);

        waitMillis(10000);

        // Turn off all intake components
        this.intakeMotor.setPower(0.0);
        this.launcherMotor.setPower(0.0);

        this.drive.turnByDegrees(0.75, TurnDirection.COUNTER_CLOCKWISE, 90);

        this.drive.driveByInches(1.0, 28.5); // Drive to Park Location

        this.drive.turnByDegrees(1.0, TurnDirection.CLOCKWISE, 90);

        this.drive.driveByInches(1.0, 74.0); // Drive to Park Location

        this.drive.turnByDegrees(1.0, TurnDirection.COUNTER_CLOCKWISE, 90);

        this.drive.driveByInches(1.0, 16.0); // Drive to Park Location


    }

    @Override
    public void onTerminate() {

    }
}
