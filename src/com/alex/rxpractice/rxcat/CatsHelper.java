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

    Api api;

    public void saveTheCutestCat(String query, final CutestCatCallback cutestCatCallback) {
        List<Cat> cats = api.queryCats(query, new Api.CatsQueryCallback() {
            @Override
            public void onCatListReceived(List<Cat> cats) {
                Cat cat = findCutestCat(cats);
                Uri savedUri = api.store(cat, new Api.StoreCallback() {
                    @Override
                    public void onCateStored(Uri savedUri) {
                        cutestCatCallback.onCutestCatSaved(savedUri);
                    }

                    @Override
                    public void onStoreFailed(Exception e) {
                        cutestCatCallback.onQueryFailed(e);
                    }
                });
                cutestCatCallback.onCutestCatSaved(savedUri);
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
