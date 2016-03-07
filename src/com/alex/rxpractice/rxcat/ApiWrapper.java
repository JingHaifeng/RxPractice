package com.alex.rxpractice.rxcat;

import android.net.Uri;

import java.util.List;

/**
 * Created by alex on 16-3-7.
 */
public class ApiWrapper {
    Api api;

    public void queryCats(String query, final Callback<List<Cat>> catsCallback) {
        api.queryCats(query, new Api.CatsQueryCallback() {
            @Override
            public void onCatListReceived(List<Cat> cats) {
                catsCallback.onResult(cats);
            }

            @Override
            public void onError(Exception e) {
                catsCallback.onError(e);
            }
        });
    }

    public void store(Cat cat, final Callback<Uri> uriCallback) {
        api.store(cat, new Api.StoreCallback() {
            @Override
            public void onCateStored(Uri savedUri) {
                uriCallback.onResult(savedUri);
            }

            @Override
            public void onStoreFailed(Exception e) {
                uriCallback.onError(e);
            }
        });
    }
}
