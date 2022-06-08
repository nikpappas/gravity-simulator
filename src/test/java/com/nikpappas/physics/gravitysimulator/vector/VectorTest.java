package com.nikpappas.physics.gravitysimulator.vector;

import com.nikpappas.physics.gravitysimulator.vector.Vector;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static com.nikpappas.physics.util.MathUtils.deg2DRad;
import static com.nikpappas.physics.util.TestUtils.assertApproximate;
import static org.junit.jupiter.api.Assertions.assertEquals;

class VectorTest {
    public final double PRECISION = 0.000000000000001;

    @ParameterizedTest(name = "[{index}] vector from constituents angle={3}")
    @CsvSource({
            "1,1,1.4142135623730951,45",
            "1,0,1,0",
            "0,1,1,90",
    })
    public void testVectorFromConstituents(double magX, double magY, double magnitude, double angle) {
        Vector v = Vector.fromConstituents(magX, magY);

        assertEquals(magnitude, v.magnitude);
        assertEquals(deg2DRad(angle), v.angle);
    }

    @ParameterizedTest(name = "[{index}] vector round trip angle={3}")
    @CsvSource({
            "1,1,1.4142135623730951,45",
            "1,0,1,0",
            "0,1,1,90",
    })
    public void testVectorRoundTripFromConstituents(double magX, double magY, double magnitude, double angle) {
        Vector v = Vector.fromConstituents(magX, magY);

        assertEquals(magnitude, v.magnitude);
        assertEquals(deg2DRad(angle), v.angle);
        assertApproximate(magX, v.getMagnitudeX(), PRECISION);
        assertApproximate(magY, v.getMagnitudeY(), PRECISION);
    }

}