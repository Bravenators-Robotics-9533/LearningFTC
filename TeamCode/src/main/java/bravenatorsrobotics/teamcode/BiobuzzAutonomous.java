package bravenatorsrobotics.teamcode;

import com.nickfanelli.biobuzz.AbstractAutonomous;
import com.nickfanelli.biobuzz.drive.AutonomousMecanumDrive;
import com.nickfanelli.biobuzz.drive.MecanumDrive;
import com.nickfanelli.biobuzz.drive.TurnDirection;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

@Autonomous(name="BiobuzzAutonomous", group="nickfanelli.teaching")
public class BiobuzzAutonomous extends AbstractAutonomous {

    private static final double LAUNCHER_VELOCITY = 1200; // rpm

    private AutonomousMecanumDrive drive;

    private DcMotorEx intakeMotor;
    private DcMotorEx launcherMotor;

    private Servo leftControlServo;
    private Servo rightControlServo;

    @Override
    public void onInitialize() {
        this.drive = new AutonomousMecanumDrive(this);
        this.drive.initializeDrive(super.hardwareMap, false, true, false, false);

        this.intakeMotor = super.hardwareMap.get(DcMotorEx.class, "intake");
        this.intakeMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        this.launcherMotor = super.hardwareMap.get(DcMotorEx.class, "launcher");

        this.leftControlServo = super.hardwareMap.get(Servo.class, "lcServo");
        this.rightControlServo = super.hardwareMap.get(Servo.class, "rcServo");

    }

    @Override
    public void onWaitingForStart() {



    }

    @Override
    public void onStart() {

        this.drive.driveByInches(0.5, 8); // Move off wall

        this.drive.turnByDegrees(0.5, TurnDirection.CLOCKWISE, 90);

        this.drive.driveByInches(0.5, 26); // Move to shoot location

        this.launcherMotor.setVelocity(LAUNCHER_VELOCITY);

        this.drive.turnByDegrees(0.5, TurnDirection.COUNTER_CLOCKWISE, 90); // Turn to shoot

        // Turn on all intake components
        this.leftControlServo.setPosition(0.0);
        this.rightControlServo.setPosition(1.0);
        this.intakeMotor.setPower(1.0);

        waitMillis(7500);

//        this.drive.driveByInches(0.5, 48);
//        this.drive.turnByDegrees(0.5, 180);

    }

    @Override
    public void onTerminate() {

    }
}
