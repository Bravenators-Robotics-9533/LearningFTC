package com.nickfanelli.biobuzz;

import com.nickfanelli.biobuzz.gamepad.FtcGamePad;
import com.nickfanelli.biobuzz.gamepad.IGamePadEventHandler;
import com.qualcomm.hardware.lynx.LynxModule;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import java.util.List;

public abstract class AbstractTeleop extends LinearOpMode {

    protected FtcGamePad driverGamepad;
    protected FtcGamePad operatorGamepad;

    @Override
    public void runOpMode() throws InterruptedException {

        // Set Lynx module to manual mode
        List<LynxModule> lynxModules = super.hardwareMap.getAll(LynxModule.class);
        lynxModules.forEach(hub -> hub.setBulkCachingMode(LynxModule.BulkCachingMode.MANUAL));

        // Initialize GamePads
        this.driverGamepad = new FtcGamePad("Driver", super.gamepad1, this::driverGamePadEvent);
        this.operatorGamepad = new FtcGamePad("Operator", super.gamepad2, this::operatorGamePadEvent);

        this.onInitialize();

        while(!isStarted()) {

            lynxModules.forEach(LynxModule::clearBulkCache); // FIRST RESET LYNX-MOD CACHE
            this.onWaitingForStart();

        }

        while(super.opModeIsActive()) {

            lynxModules.forEach(LynxModule::clearBulkCache); // FIRST RESET LYNX-MOD CACHE

            this.onUpdate();

            // Update GamePads
            this.driverGamepad.update();
            this.operatorGamepad.update();

        }

        this.onTerminate();

    }

    private void driverGamePadEvent(FtcGamePad gamepad, int button, boolean pressed) {
        this.onDriverGamePadEvent(button, pressed);
    }

    private void operatorGamePadEvent(FtcGamePad gamepad, int button, boolean pressed) {
        this.onOperatorGamePadEvent(button, pressed);
    }

    public abstract void onInitialize();
    public abstract void onWaitingForStart();
    public abstract void onUpdate();
    public abstract void onTerminate();

    public abstract void onDriverGamePadEvent(int button, boolean isPressed);
    public abstract void onOperatorGamePadEvent(int button, boolean isPressed);

}
