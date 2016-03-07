package com.alex.rxpractice.rxcat;

/**
 * Created by alex on 16-3-7.
 */
public interface Func<T, R> {
    R call(T t);
}
