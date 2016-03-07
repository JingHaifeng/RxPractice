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

    public void saveTheCutestCat(String query, final CutestCatCallback cutestCatCallback) {
        apiWrapper.queryCats(query, new Callback<List<Cat>>() {
            @Override
            public void onResult(List<Cat> result) {
                Cat cat = findCutestCat(result);
                apiWrapper.store(cat, new Callback<Uri>() {
                    @Override
                    public void onResult(Uri result) {
                        cutestCatCallback.onCutestCatSaved(result);
                    }

                    @Override
                    public void onError(Exception e) {
                        cutestCatCallback.onQueryFailed(e);
                    }
                });
            }

            @Override
            public void onError(Exception e) {
                cutestCatCallback.onQueryFailed(e);
            }
        });
    }

    private Cat findCutestCat(List<Cat> cats) {
        return Collections.max(cats);
    }
}
