package com.nikpappas.physics.gravitysimulator.examples;

import com.nikpappas.physics.gravitysimulator.GravitySimulator;
import com.nikpappas.physics.gravitysimulator.Particle;

public class SimpleTwoObjectsSameMass {
    public static final int DURATION_S = 1000;

    public static void main(String[] args) {
        GravitySimulator gs = new GravitySimulator(false);
        gs.addParticle(new Particle(1,-1,0));
        gs.addParticle(new Particle(1,1,0));
        for (int i = 0; i < DURATION_S; i++) {
            gs.tick();
        }
    }
}
