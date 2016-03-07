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
        final AsyncJob<List<Cat>> catsListAsyncJob = apiWrapper.queryCats(query);
        final AsyncJob<Cat> cutestCatAsyncJob = catsListAsyncJob.map(new Func<List<Cat>, Cat>() {
            @Override
            public Cat call(List<Cat> cats) {
                return findCutestCat(cats);
            }
        });

        AsyncJob<Uri> storedUriAsyncJob = cutestCatAsyncJob.flatMap(new Func<Cat, AsyncJob<Uri>>() {
            @Override
            public AsyncJob<Uri> call(final Cat cat) {
                return apiWrapper.store(cat);
            }
        });
        return storedUriAsyncJob;
    }

    private Cat findCutestCat(List<Cat> cats) {
        return Collections.max(cats);
    }
}
