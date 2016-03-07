package com.alex.rxpractice.rxcat;

import android.net.Uri;

import java.util.List;

/**
 * Created by alex on 16-3-7.
 */
public interface Api {
    List<Cat> queryCats(String query);

    Uri store(Cat cat);
}
