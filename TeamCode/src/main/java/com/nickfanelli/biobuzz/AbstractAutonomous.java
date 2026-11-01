package com.nickfanelli.biobuzz;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

public abstract class AbstractAutonomous extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {

        this.onInitialize();

        while(!isStarted()) {

            this.onWaitingForStart();

        }

        while(opModeIsActive()) {

            this.onUpdate();

        }

        this.onTerminate();
        
    }

    public abstract void onInitialize();
    public abstract void onWaitingForStart();
    public abstract void onUpdate();
    public abstract void onTerminate();

}
