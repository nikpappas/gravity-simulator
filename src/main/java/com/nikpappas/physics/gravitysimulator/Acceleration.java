package com.nikpappas.physics.gravitysimulator;

public class Acceleration extends Vector {
    public Acceleration(double magnitude, double angle) {
        super(magnitude, angle);
    }

    public static Acceleration of(double magnitude, double angle) {
        return new Acceleration(magnitude, angle);
    }
}
