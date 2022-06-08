package com.nikpappas.physics.gravitysimulator;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class WorldTest {
    @ParameterizedTest(name = "{index} expecting {1} interactions from {0}, particles")
    @CsvSource({
            "1,0",
            "2,1",
            "3,3",
            "4,6",
            "5,10",
            "6,15",
            "7,21",
    })
    void testCouples(int particles, int interactions) {
        World w = new World();
        for (int i = 0; i < particles; i++) {
            w.addParticle(new Particle(0, 0, 0));
        }
        assertEquals(interactions, w.getCouples().size());
    }
    @ParameterizedTest(name = "{index} nondefault constructor expecting {1} interactions from {0}, particles")
    @CsvSource({
            "1,0",
            "2,1",
            "3,3",
            "4,6",
            "5,10",
            "6,15",
            "7,21",
    })
    void testCouplesNonDefaultConstructor(int particles, int interactions) {
        List<Particle> particlesTodAdd = new ArrayList<>();
        for (int i = 0; i < particles; i++) {
            particlesTodAdd.add(new Particle(0, 0, 0));
        }
        World w = new World(particlesTodAdd);
        assertEquals(interactions, w.getCouples().size());
    }

}