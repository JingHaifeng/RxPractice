package com.alex.rxpractice.rxcat;

/**
 * Created by alex on 16-3-7.
 */
public abstract class AsyncJob<T> {
    public abstract void start(Callback<T> callback);
}
