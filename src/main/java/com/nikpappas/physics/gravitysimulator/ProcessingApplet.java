package com.nikpappas.physics.gravitysimulator;

import processing.core.PApplet;

import java.util.ArrayList;
import java.util.List;

import static java.awt.event.KeyEvent.VK_SPACE;

public class ProcessingApplet extends PApplet {

    public static void main(String[] args) {
        PApplet.main(Thread.currentThread().getStackTrace()[1].getClassName());
    }

    boolean paused = false;
    final GravitySimulator sim = new GravitySimulator();
    final List<Trio<Float>> colours = new ArrayList<>();

    @Override
    public void settings() {
        size(900, 600);
    }

    @Override
    public void setup() {
        super.setup();
        background(20, 30, 30);

        addParticle(new Particle(100000, -100, 10));
        addParticle(new Particle(10000, 100, 10));
        addParticle(new Particle(100000, 50, 40));
        addParticle(new Particle(100000000, 5, 14));
        addParticle(new Particle(1000001, 15, 40));
        addParticle(new Particle(100000, 60, 140));
        addParticle(new Particle(10000000, -50, 40));
        addParticle(new Particle(100000, -50, -40));

    }

    @Override
    public void draw() {
        background(20, 30, 30);

        ellipseMode(CENTER);

        if (!paused) {
            sim.tick();
        }

        List<Particle> particles = sim.getParticles();
        stroke(200);
        for (int i = 0; i < particles.size(); i++) {
            Trio<Float> colour = colours.get(i);
            fill(colour._1, colour._2, colour._3);
            Particle p = particles.get(i);
            float x = width * .5f + (float) p.x;
            float y = height * .5f + (float) p.y;
            circle(x, y, max((float) p.mass * 0.000001f, 2.0f));
            line(x, y,
                    (float) (x + p.resultantForce.getMagnitudeX()), (float) (y + p.resultantForce.getMagnitudeY()));
        }
        delay(40);

    }

    @Override
    public void keyPressed() {
        if (key == VK_SPACE) {
            paused = !paused;
        }
    }

    private void circle(float x, float y, float r) {
        ellipse(x, y, r, r);
    }

    private void addParticle(Particle p) {
        sim.addParticle(p);
        colours.add(Trio.of(random(60, 200), random(60, 200), random(60, 200)));
    }
}
