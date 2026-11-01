package com.nickfanelli.biobuzz;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

public abstract class AbstractAutonomous extends LinearOpMode {

    private final ElapsedTime timer = new ElapsedTime();

    public void waitMillis(int millis) {

        timer.reset();

        while(opModeIsActive()) {
            if(timer.milliseconds() >= millis)
                break;
        }

    }

    @Override
    public void runOpMode() throws InterruptedException {

        this.onInitialize();

        while(!isStarted()) {

            this.onWaitingForStart();

        }

        if(this.opModeIsActive())
            this.onStart();

        this.onTerminate();
        
    }

    public abstract void onInitialize();
    public abstract void onWaitingForStart();
    public abstract void onStart();
    public abstract void onTerminate();

}
