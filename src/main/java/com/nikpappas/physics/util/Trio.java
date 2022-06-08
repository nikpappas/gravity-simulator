package com.nikpappas.physics.util;

public class Trio<T> {
    public final T _1;
    public final T _2;
    public final T _3;

    private Trio(T _1, T _2, T _3) {
        this._1 = _1;
        this._2 = _2;
        this._3 = _3;
    }

    public static <V> Trio<V> of(V _1, V _2, V _3) {
        return new Trio<>(_1, _2, _3);
    }
}
