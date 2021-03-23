package com.nikpappas.physics.gravitysimulator;


public class Particle {
    /**
     * Mass in Kg
     */
    final double mass;

    /**
     * X in meters
     */
    volatile double x;
    /**
     * Y in meters
     */
    volatile double y;
    /**
     * v in meters/sec
     */
    Velocity v;
    /**
     * a in meters/sec^2
     */
    Acceleration a;
    /**
     * a in meters/sec^2
     */
    Force resultantForce;


    public Particle(double mass, double x, double y) {
        this.mass = mass;
        this.x = x;
        this.y = y;
        this.v = Velocity.of(0, 0);
    }
}
