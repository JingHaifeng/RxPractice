package com.alex.rxpractice.rxcat;

import android.net.Uri;

import java.util.Collections;
import java.util.List;

/**
 * Created by alex on 16-3-7.
 */
public class CatsHelper {

    public interface CutestCatCallback {
        void onCutestCatSaved(Uri savedUri);

        void onQueryFailed(Exception e);
    }

    ApiWrapper apiWrapper;

    public AsyncJob<Uri> saveTheCutestCat(final String query) {
        return new AsyncJob<Uri>() {
            @Override
            public void start(final Callback<Uri> callback) {
                apiWrapper.queryCats(query)
                        .start(new Callback<List<Cat>>() {
                            @Override
                            public void onResult(List<Cat> result) {
                                Cat cat = findCutestCat(result);
                                apiWrapper.store(cat)
                                        .start(new Callback<Uri>() {
                                            @Override
                                            public void onResult(Uri result) {
                                                callback.onResult(result);
                                            }

                                            @Override
                                            public void onError(Exception e) {
                                                callback.onError(e);
                                            }
                                        });
                            }
                            @Override
                            public void onError(Exception e) {
                                callback.onError(e);
                            }
                        });
            }
        };
    }

    private Cat findCutestCat(List<Cat> cats) {
        return Collections.max(cats);
    }
}
