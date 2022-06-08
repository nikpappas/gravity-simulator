package com.nikpappas.physics.util;

import org.opentest4j.AssertionFailedError;

import static java.lang.Math.abs;

public class TestUtils {
    public static void assertApproximate(double expected, double actual, double precision) {
        double actualPrecision = abs(expected - actual);
        if (actualPrecision > precision) {
            throw new AssertionFailedError(String.format("expected: <%s> but was: <%s> %nexpected precision: <%s> but was: <%s>)",
                    expected, actual, precision, actualPrecision));
        }
    }

}
