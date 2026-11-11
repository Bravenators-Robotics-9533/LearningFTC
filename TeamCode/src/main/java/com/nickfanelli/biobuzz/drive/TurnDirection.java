package com.nickfanelli.biobuzz.drive;

public enum TurnDirection {

    CLOCKWISE(1),
    COUNTER_CLOCKWISE(-1);

    public final int multiplier;

    TurnDirection(int multiplier) {
        this.multiplier = multiplier;
    }

}
