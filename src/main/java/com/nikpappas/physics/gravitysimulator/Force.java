package com.nikpappas.physics.gravitysimulator;

public class Force extends Vector {
    public Force(double magnitude, double angle) {
        super(magnitude, angle);
    }

    public static Force of(double magnitude, double angle) {
        return new Force(magnitude, angle);
    }
    public static Force add(Force f1,Force f2){
        Vector v = Vector.add(f1, f2);
        return of(v.magnitude, v.angle);
    }
}
