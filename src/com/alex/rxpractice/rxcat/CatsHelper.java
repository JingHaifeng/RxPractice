package com.alex.rxpractice.rxcat;

import android.net.Uri;

import java.util.Collections;
import java.util.List;

/**
 * Created by alex on 16-3-7.
 */
public class CatsHelper {
    Api api;

    public Uri saveTheCutestCat(String query) {
        List<Cat> cats = api.queryCats(query);
        Cat cat = findCutestCat(cats);
        Uri uri = api.store(cat);
        return uri;
    }

    private Cat findCutestCat(List<Cat> cats) {
        return Collections.max(cats);
    }
}
