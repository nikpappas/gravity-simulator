package com.nikpappas.physics.gravitysimulator;

public class Velocity extends Vector {
    public Velocity(double magnitude, double angle) {
        super(magnitude, angle);
    }

    public static Velocity of(double magnitude, double angle) {
        return new Velocity(magnitude, angle);
    }

    public static Velocity fromConstituents(double vx, double vy) {
        Vector vector = Vector.fromConstituents(vx, vy);
        return Velocity.of(vector.magnitude, vector.angle) ;
    }
}
