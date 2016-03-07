package com.alex.rxpractice.rxcat;

/**
 * Created by alex on 16-3-7.
 */
public interface Callback<T> {
    void onResult(T result);

    void onError(Exception e);
}
