package com.alex.rxpractice.rxcat;

import android.graphics.Bitmap;

/**
 * Created by alex on 16-3-7.
 */
public class Cat implements Comparable<Cat> {
    Bitmap image;
    int cuteness;

    @Override
    public int compareTo(Cat cat) {
        return Integer.compare(cuteness, cat.cuteness);
    }
}
