package com.nikpappas.physics.gravitysimulator;

import com.nikpappas.physics.util.Couple;

import java.util.*;

import static java.util.stream.Collectors.toSet;

public class World {
    private final List<Particle> particles;
    private final Map<Particle, List<Particle>> interactions;

    public World() {
        this(new ArrayList<>());
    }

    public World(List<Particle> particles) {
        this.particles = new ArrayList<>();
        this.interactions = new HashMap<>();
        particles.forEach(this::addParticle);
    }

    public void addParticle(Particle p) {
        interactions.put(p, new ArrayList<>(particles));
        particles.add(p);
    }


    public Set<Couple<Particle>> getCouples() {
        return interactions.entrySet().stream().flatMap((e ->
                e.getValue().stream().map(p -> Couple.of(e.getKey(), p))
        )).collect(toSet());
    }

    public List<Particle> getParticles() {
        return particles;
    }
}
