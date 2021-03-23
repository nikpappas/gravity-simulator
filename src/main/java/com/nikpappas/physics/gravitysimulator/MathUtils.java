package com.nikpappas.physics.gravitysimulator;

import static java.lang.Math.*;

public class MathUtils {

    private MathUtils(){}

    public static double calculateAngle(double x, double y) {

        double angle = atan(y / x);
        if(x<0){
            return PI+angle;
        }
        return angle < 0 ? 2*PI + angle : angle;
    }

    public static double calculateDistance(double x, double y) {
        return sqrt(sqr(x) + sqr(y));
    }

    public static double sqr(double a) {
        return a * a;
    }

    public static double rad2Deg(double a) {
        return 180.0 * a / PI;
    }

    public static double deg2DRad(double a) {
        return PI * a / 180.0;
    }


}
