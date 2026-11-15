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

    private AutonomousMecanumDrive drive;

    private DcMotorEx intakeMotor;
    private DcMotorEx launcherMotor;

    @Override
    public void onInitialize() {
        this.drive = new AutonomousMecanumDrive(this);
        this.drive.initializeDrive(super.hardwareMap, true, true, true, true);

        this.intakeMotor = super.hardwareMap.get(DcMotorEx.class, "intake1");
        this.intakeMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        this.launcherMotor = super.hardwareMap.get(DcMotorEx.class, "launcher");
        this.launcherMotor.setDirection(DcMotorSimple.Direction.REVERSE);

    }

    @Override
    public void onWaitingForStart() {



    }

    @Override
    public void onStart() {

        this.drive.driveByInches(0.5, -8.0); // Move off wall

        this.drive.turnByDegrees(0.5, TurnDirection.CLOCKWISE, 90);

        this.drive.driveByInches(0.5, -26); // Move to shoot location

        this.drive.turnByDegrees(0.5, TurnDirection.COUNTER_CLOCKWISE, 90); // Turn to shoot

        this.launcherMotor.setPower(1.0);

        this.drive.driveByInches(0.5, 5.5); // Back Up

        waitMillis(750);

        // Turn on all intake components
        this.intakeMotor.setPower(1.0);

        waitMillis(5000);

        // Turn off all intake components
        this.intakeMotor.setPower(0.0);
        this.launcherMotor.setPower(0.0);

        this.drive.driveByInches(0.5, -5.5); // Back Up

        this.drive.turnByDegrees(0.75, TurnDirection.CLOCKWISE, 90);

        this.drive.driveByInches(1.0, 28); // Drive to Park Location

        this.drive.turnByDegrees(1.0, TurnDirection.CLOCKWISE, 90);

        this.drive.driveByInches(1.0, 74.0); // Drive to Park Location

        this.drive.turnByDegrees(1.0, TurnDirection.COUNTER_CLOCKWISE, 90);

    }

    @Override
    public void onTerminate() {

    }
}
