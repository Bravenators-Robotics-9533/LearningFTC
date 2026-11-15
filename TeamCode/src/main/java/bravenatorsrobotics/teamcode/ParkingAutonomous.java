package bravenatorsrobotics.teamcode;

import com.nickfanelli.biobuzz.AbstractAutonomous;
import com.nickfanelli.biobuzz.drive.AutonomousMecanumDrive;
import com.nickfanelli.biobuzz.drive.TurnDirection;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

@Autonomous(name="Park Only Autonomous", group="nickfanelli.teaching")
public class ParkingAutonomous extends AbstractAutonomous {

    private static final double LAUNCHER_VELOCITY = 985; // rpm

    private AutonomousMecanumDrive drive;

    @Override
    public void onInitialize() {
        this.drive = new AutonomousMecanumDrive(this);
        this.drive.initializeDrive(super.hardwareMap, false, true, false, false);
    }

    @Override
    public void onWaitingForStart() {

    }

    @Override
    public void onStart() {

        this.drive.driveByInches(0.5, 18); // Drive forward to park

    }

    @Override
    public void onTerminate() {

    }
}
