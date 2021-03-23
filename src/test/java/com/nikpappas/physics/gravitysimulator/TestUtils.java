package com.nikpappas.physics.gravitysimulator;

import org.opentest4j.AssertionFailedError;

import static java.lang.Math.abs;

public class TestUtils {
    public static void assertApproximate(double expected, double actual, double precision) {
        if (abs(expected - actual) > precision) {
            throw new AssertionFailedError(String.format("expected: %s but was: %s", expected, actual));
        }
    }

}
