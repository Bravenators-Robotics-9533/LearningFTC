package com.nickfanelli.biobuzz.teleopControls;

import com.nickfanelli.biobuzz.AbstractTeleop;
import com.nickfanelli.biobuzz.gamepad.FtcGamePad;
import com.nickfanelli.biobuzz.gamepad.IGamePadEventHandler;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotorEx;

public class SingleMotorToggleTeleopControl extends AbstractTeleopControl implements IGamePadEventHandler {

    private final DcMotorEx motor;
    private final int button;

    private boolean isRunning = false;
    private double toggleHigh;
    private double toggleLow;

    public SingleMotorToggleTeleopControl(AbstractTeleop opMode, DcMotorEx motor, FtcGamePad gamePad, int button, double toggleLow, double toggleHigh) {
        super(opMode);

        this.motor = motor;
        this.button = button;

        super.opMode.subscribeToGamePad(gamePad, this);

    }

    public SingleMotorToggleTeleopControl(AbstractTeleop opMode, DcMotorEx motor, FtcGamePad gamePad, int button) {
        this(opMode, motor, gamePad, button, 0.0, 1.0);
    }

    @Override
    public void update() {

        this.motor.setPower(isRunning ? toggleHigh : toggleLow);

    }

    @Override
    public void onGamepadButtonEvent(int button, boolean isPressed) {

        // If the current button changed & not pressed (meaning if the button was released)
        if(button == this.button && !isPressed) {
            this.isRunning = !this.isRunning; // Toggle
        }

    }

    public void manuallyToggle() { this.isRunning = !this.isRunning; }
    public void setIsRunning(boolean isRunning) { this.isRunning = isRunning; }

    public void setToggleHigh(double toggleHigh) { this.toggleHigh = toggleHigh; }
    public double getToggleHigh() { return this.toggleHigh; }

    public void setToggleLow(double toggleLow) { this.toggleLow = toggleLow; }
    public double getToggleLow() { return this.toggleLow; }


}
