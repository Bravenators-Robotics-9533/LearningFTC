package com.nickfanelli.biobuzz.teleopControls;

import com.nickfanelli.biobuzz.AbstractTeleop;

public abstract class AbstractTeleopControl {

    protected final AbstractTeleop opMode;

    public AbstractTeleopControl(AbstractTeleop opMode) {
        this.opMode = opMode;
    }

    public abstract void update();

}
