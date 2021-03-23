package com.nikpappas.physics.gravitysimulator;

public class Couple<T> {
    public final T _1;
    public final T _2;

    private Couple(T _1, T _2) {
        this._1 = _1;
        this._2 = _2;
    }

    public static <V> Couple<V> of(V _1, V _2) {
        return new Couple<>(_1, _2);
    }
}
