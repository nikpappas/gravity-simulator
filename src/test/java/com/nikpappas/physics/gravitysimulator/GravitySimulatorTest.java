package com.nikpappas.physics.gravitysimulator;

import com.nikpappas.physics.gravitysimulator.vector.Force;
import com.nikpappas.physics.util.Couple;
import org.junit.jupiter.api.Test;

import static com.nikpappas.physics.util.TestUtils.assertApproximate;
import static org.junit.jupiter.api.Assertions.assertEquals;

class GravitySimulatorTest {
    public static final double PRECISION = 0.0000000000000000001;
    public static final double EARTH_RADIUS = 6378000;
    public static final double EARTH_MASS = 5.9722E+24;


    @Test
    void test2MassesSameMassSymmetry() {
        double mass = 100.0;
        double halfDistance = 100;
        //          21
        //------12--|--11-----
        //          22

        GravitySimulator gs1 = new GravitySimulator();
        Particle particle11 = new Particle(mass, halfDistance, 0.0);
        Particle particle12 = new Particle(mass, -halfDistance, 0.0);
        GravitySimulator gs2 = new GravitySimulator();
        Particle particle21 = new Particle(mass, 0.0, halfDistance);
        Particle particle22 = new Particle(mass, 0.0, -halfDistance);
        gs1.addParticle(particle11);
        gs1.addParticle(particle12);
        gs2.addParticle(particle21);
        gs2.addParticle(particle22);

        int repetitions = 1000;
        for (int i = 0; i < repetitions; i++) {
            assertEquals(particle11.x, particle21.y);
            assertEquals(particle12.x, particle22.y);
            assertApproximateP(particle11.y, particle21.x);
            assertApproximateP(particle12.y, particle21.x);

            gs1.tick();
            gs2.tick();
        }
    }

    @Test
    void test2MassesSameMassSymmetryAngle() {
        double mass = 100.0;
        double halfDistance = 100;
        GravitySimulator gs1 = new GravitySimulator();

        //      12  |  21
        //----------|----------
        //      22  |  11


        Particle particle11 = new Particle(mass, halfDistance, -halfDistance);
        Particle particle12 = new Particle(mass, -halfDistance, halfDistance);
        GravitySimulator gs2 = new GravitySimulator();
        Particle particle21 = new Particle(mass, halfDistance, halfDistance);
        Particle particle22 = new Particle(mass, -halfDistance, -halfDistance);
        gs1.addParticle(particle11);
        gs1.addParticle(particle12);
        gs2.addParticle(particle21);
        gs2.addParticle(particle22);

        int repetitions = 1000;
        for (int i = 0; i < repetitions; i++) {
            assertEquals(particle11.x, particle21.y);
            assertEquals(particle12.x, particle22.y);
            assertEquals(particle11.y, -particle21.x);
            assertEquals(particle12.y, particle21.x);

            gs1.tick();
            gs2.tick();
        }
    }

    @Test
    void test2MassesDoubleMassAcceleration() {
        double mass1 = 100.0;
        double mass2 = 200;
        GravitySimulator gs = new GravitySimulator();


        Particle particle1 = new Particle(mass1, 0, 100);
        Particle particle2 = new Particle(mass2, 0, 0);
        gs.addParticle(particle1);
        gs.addParticle(particle2);

        int repetitions = 1000;
        for (int i = 0; i < repetitions; i++) {
            gs.tick();
            assertEquals(particle1.a.magnitude, 2.0 * particle2.a.magnitude);
            assertApproximateP(particle1.a.getMagnitudeX(), -2 * particle2.a.getMagnitudeX());
            assertEquals(particle1.a.getMagnitudeY(), -2.0 * particle2.a.getMagnitudeY());
        }
    }

    @Test
    void freeFallTest() {
        Particle earth = new Particle(EARTH_MASS, 0, 0);
        Particle human = new Particle(100, 0, 100 + EARTH_RADIUS);
        GravitySimulator gs = new GravitySimulator();
        gs.addParticle(earth);
        gs.addParticle(human);
        double time = 0.0;
        double increment = .01;
        while (time < 5) {
            System.out.printf("%.3f,  %.3f, %.9f%n", time, human.y - EARTH_RADIUS, earth.y);
            time += increment;
            gs.tick(increment);
        }
    }

    @Test
    void calculateForceTest() {
        double earthRadius = 6378000;
        GravitySimulator gs = new GravitySimulator();
        Particle earth = new Particle(EARTH_MASS, 0, 0);
        Particle human = new Particle(100, 0, 100 + earthRadius);

        Couple<Force> res = gs.calculateGravity(earth, human);
        assertEquals(979.812787317626, res._2.magnitude);
    }

    private static void assertApproximateP(double expected, double actual) {
        assertApproximate(expected, actual, PRECISION);
    }

}