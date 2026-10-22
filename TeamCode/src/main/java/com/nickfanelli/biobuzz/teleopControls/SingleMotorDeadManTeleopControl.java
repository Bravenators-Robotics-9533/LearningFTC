package com.nickfanelli.biobuzz.teleopControls;

import com.nickfanelli.biobuzz.AbstractTeleop;
import com.nickfanelli.biobuzz.gamepad.FtcGamePad;
import com.nickfanelli.biobuzz.gamepad.IGamePadEventHandler;
import com.qualcomm.robotcore.hardware.DcMotorEx;

public class SingleMotorDeadManTeleopControl extends AbstractTeleopControl implements IGamePadEventHandler {

    private final DcMotorEx motor;
    private final int button;

    private boolean isRunning = false;
    private double runHigh;
    private double runLow;

    public SingleMotorDeadManTeleopControl(AbstractTeleop opMode, DcMotorEx motor, FtcGamePad gamePad, int button, double runLow, double runHigh) {
        super(opMode);

        this.motor = motor;
        this.button = button;

        super.opMode.subscribeToGamePad(gamePad, this);

    }

    public SingleMotorDeadManTeleopControl(AbstractTeleop opMode, DcMotorEx motor, FtcGamePad gamePad, int button) {
        this(opMode, motor, gamePad, button, 0.0, 1.0);
    }

    @Override
    public void update() {

        this.motor.setPower(isRunning ? runHigh : runLow);

    }

    @Override
    public void onGamepadButtonEvent(int button, boolean isPressed) {

        if(button == this.button) {
            this.isRunning = isPressed; // Set Direct (dead-man)
        }

    }

    public void setRunHigh(double runHigh) { this.runHigh = runHigh; }
    public double getRunHigh() { return this.runHigh; }

    public void setRunLow(double runLow) { this.runLow = runLow; }
    public double getRunLow() { return this.runLow; }


}
