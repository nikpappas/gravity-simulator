package com.nikpappas.physics.gravitysimulator;

import com.nikpappas.physics.gravitysimulator.vector.Acceleration;
import com.nikpappas.physics.gravitysimulator.vector.Force;
import com.nikpappas.physics.gravitysimulator.vector.Velocity;
import com.nikpappas.physics.util.Couple;
import com.nikpappas.physics.util.MathUtils;

import java.util.List;

import static com.nikpappas.physics.util.MathUtils.sqr;
import static java.lang.Math.PI;

public class GravitySimulator {
    // m^3 kg^-1 s^-2 || N m^2 kg^-2
    public final double GRAVITATIONAL_CONSTANT = 6.67408 * Math.pow(10, -11);
    // Jigged up x1000
//    public final double GRAVITATIONAL_CONSTANT = 6.67408 * Math.pow(10, -8);
    private final World world = new World();
    private boolean debug;

    public GravitySimulator(boolean debug) {
        this.debug = debug;
    }

    public GravitySimulator() {
        this(false);
    }


    public static void main(String[] args) {
        GravitySimulator sim = new GravitySimulator(false);
        sim.run();

    }

    /**
     * Calculates acceleration, velocity and position of the particles after `time` seconds of the previous status
     *
     * @param time The time increment for the calculation of position and vectors of the particles
     */
    public void tick(double time) {
        world.getParticles().forEach(p -> p.resultantForce = null);
        world.getCouples()
                .forEach(c -> {
                    Couple<Force> gravity = calculateGravity(c._1, c._2);
                    c._1.resultantForce = Force.add(c._1.resultantForce, gravity._1);
                    c._2.resultantForce = Force.add(c._2.resultantForce, gravity._2);
                });


        world.getParticles().stream()
                .parallel()
                .forEach(p -> {
                    p.a = calculateAcceleration(p, p.resultantForce);
                    double vx = p.v.getMagnitudeX() + p.a.getMagnitudeX() * time;
                    double vy = p.v.getMagnitudeY() + p.a.getMagnitudeY() * time;
                    p.v = Velocity.fromConstituents(vx, vy);
                    p.x = p.x + p.v.getMagnitudeX() * time;
                    p.y = p.y + p.v.getMagnitudeY() * time;
                });

        for (int pi = 0; pi < world.getParticles().size(); pi++) {

            Particle p = world.getParticles().get(pi);
            if (debug) {
                System.out.printf("%d: F -> %s \n\ta -> %s \n\t\tv -> \n\t\t\t%s \n\t\t\t\tx -> %f y -> %f%n", pi, p.resultantForce, p.a, p.v, p.x, p.y);
            }
        }
    }

    /**
     * Calculates acceleration, velocity and position of the particles after 1 second of the previous status
     */
    public void tick() {
        tick(1);
    }

    public void run() {
        world.addParticle(new Particle(10000, -100, 10));
        world.addParticle(new Particle(10000, 100, 10));
        world.addParticle(new Particle(100000, 50, 40));


        for (int i = 0; i < 1000; i++) {
            tick();
        }
    }

    Acceleration calculateAcceleration(Particle a, Force force) {
        return Acceleration.of(force.magnitude / a.mass, force.angle);
    }

    double calculateDistance(Particle a, Particle b) {

        return Math.sqrt(sqr(a.x - b.x) + sqr(a.y - b.y));
    }

    Couple<Force> calculateGravity(Particle a, Particle b) {
        double magnitude = GRAVITATIONAL_CONSTANT * a.mass * b.mass / sqr(calculateDistance(a, b));
        double angle = calculateAngle(a, b);
        return Couple.of(Force.of(magnitude, angle), Force.of(magnitude, (angle + PI) % (2.0 * PI)));
    }

    private double calculateAngle(Particle a, Particle b) {
        return MathUtils.calculateAngle(b.x - a.x, b.y - a.y);
    }


    public List<Particle> getParticles() {
        return world.getParticles();
    }

    public void addParticle(Particle p) {
        world.addParticle(p);
    }
}
