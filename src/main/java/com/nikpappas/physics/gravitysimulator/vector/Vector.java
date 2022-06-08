package com.nikpappas.physics.gravitysimulator.vector;

import com.nikpappas.physics.util.MathUtils;

import static com.nikpappas.physics.util.MathUtils.calculateDistance;
import static com.nikpappas.physics.util.MathUtils.rad2Deg;
import static java.lang.Math.cos;
import static java.lang.Math.sin;
import static java.lang.String.format;

public class Vector {
    public final double magnitude;
    public final double angle;

    public Vector(double magnitude, double angle) {
        this.magnitude = magnitude;
        this.angle = angle;
    }

    public double getMagnitudeX() {
        return magnitude * cos(angle);
    }

    public double getMagnitudeY() {
        return magnitude * sin(angle);
    }

    protected static Vector fromConstituents(double magX, double magY) {
        return Vector.of(calculateDistance(magX, magY), MathUtils.calculateAngle(magX, magY));
    }


    public static <V extends Vector> Vector add(V v1, V v2) {
        if (v1 == null && v2 == null) {
            return Vector.of(0, 0);
        }
        if (v1 == null) {
            return v2;
        }
        if (v2 == null) {
            return v1;
        }
        return Vector.fromConstituents(v1.getMagnitudeX() + v2.getMagnitudeX(), v1.getMagnitudeY() + v2.getMagnitudeY());
    }

    private static Vector of(double magnitude, double angle) {
        return new Vector(magnitude, angle);
    }

    @Override
    public String toString() {
        return format("<%f, %.8f -- [%.15f, %.15f]>", magnitude, rad2Deg(angle), getMagnitudeX(), getMagnitudeY());
    }
}
