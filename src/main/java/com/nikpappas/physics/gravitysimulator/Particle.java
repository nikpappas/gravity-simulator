package com.nikpappas.physics.gravitysimulator;


import com.nikpappas.physics.gravitysimulator.vector.Acceleration;
import com.nikpappas.physics.gravitysimulator.vector.Force;
import com.nikpappas.physics.gravitysimulator.vector.Velocity;

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


    /**
     *
     * @param mass kg
     * @param x m
     * @param y m
     */
    public Particle(double mass, double x, double y) {
        this.mass = mass;
        this.x = x;
        this.y = y;
        this.v = Velocity.of(0, 0);
    }
}
