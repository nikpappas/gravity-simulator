package com.nikpappas.physics.gravitysimulator;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static com.nikpappas.physics.gravitysimulator.MathUtils.rad2Deg;
import static com.nikpappas.physics.gravitysimulator.TestUtils.assertApproximate;

class VectorTest {
    public final double PRECISION = 0.000001;

    @ParameterizedTest
    @CsvSource("1,1,1.4142135,45")
    public void testVectorFromConstituents(double magX, double magY, double magnitude, double angle) {
        Vector v = Vector.fromConstituents(magX, magY);

        assertApproximate(magnitude, v.magnitude, PRECISION);
        assertApproximate(rad2Deg(angle), v.angle, PRECISION);
    }

}