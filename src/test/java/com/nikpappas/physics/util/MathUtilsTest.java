package com.nikpappas.physics.util;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static com.nikpappas.physics.util.MathUtils.*;
import static com.nikpappas.physics.util.TestUtils.assertApproximate;

class MathUtilsTest {

    @ParameterizedTest(name = "{index} -- angle from  {0},{1} == {2}")
    @CsvSource({
            "1.0,1.0,45",
            "1.0,-1.0,315",
            "0.0,0.0,0.0",
            "-1.0,-1.0,225.0",
            "0.0,1.0,90.0",
            "1.0,0.0,0.0",
            "-0.000005899109039, -0.000005899109722,225.00000331685808",
            "4.0,3.0, 36.86989764"})
    public void testCalculateAngle(double a, double b, double res) {

        assertApproximate(res, rad2Deg(calculateAngle(a, b)), 0.0000001);
    }


    @ParameterizedTest(name = "{index} -- distance from  {0},{1}")
    @CsvSource({"1,1,1.4142135623730951", "1,-1,1.4142135623730951", "0,0,0", "-1,-1,1.4142135623730951"})
    public void testCalculateDistance(double a, double b, double res) {

        assertApproximate(res, calculateDistance(a, b), 0.000000001);
    }


    @ParameterizedTest(name = "FloatMod{index} -- {0}%{1}")
    @CsvSource({"1.001,1.0,0.001", "0.45,1,0.45"})
    public void testFloatMod(double a, double b, double res) {
        assertApproximate(res, a % b, 0.000000001);
    }

    @ParameterizedTest(name = "rad2deg{index} -- {0} -> {1}")
    @CsvSource({
            "7.85398163,450",
            "6.28318529,360",
            "1.57079633,90",
            "0.78539816339,45",
            "-0.78539816339,-45",
            "3.14159265,180"
            })
    public void testRad2Deg(double rad, double deg) {
        assertApproximate(deg, rad2Deg(rad), 0.000001);
    }

    @ParameterizedTest(name = "deg2rad{index} -- {1} -> {0}")
    @CsvSource({
            "7.85398163,450",
            "6.28318529,360",
            "1.57079633,90",
            "0.78539816339,45",
            "-0.78539816339,-45",
            "3.14159265,180"
    })
    public void testDeg2Rad(double rad, double deg) {
        assertApproximate(rad, deg2DRad(deg), 0.000001);
    }

}