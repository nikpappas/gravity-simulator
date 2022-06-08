package com.nikpappas.physics.gravitysimulator.vector;

public class Acceleration extends Vector {
    public Acceleration(double magnitude, double angle) {
        super(magnitude, angle);
    }

    public static Acceleration of(double magnitude, double angle) {
        return new Acceleration(magnitude, angle);
    }
}
