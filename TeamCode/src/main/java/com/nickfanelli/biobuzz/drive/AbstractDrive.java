package com.nickfanelli.biobuzz.drive;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;

public abstract class AbstractDrive {

    protected final HardwareMap hardwareMap;

    public AbstractDrive(HardwareMap hardwareMap) {
        this.hardwareMap = hardwareMap;
    }

    /**
     * Must be called before running any drive code (initializes drive)
     *
     */
    public abstract void initializeDrive();

    /**
     * Drives the robot manually based on the params
     * @param y the froward and backward power from -1 to 1
     * @param r the turn power from -1 to 1
     */
    public abstract void drive(double y, double r);


}
