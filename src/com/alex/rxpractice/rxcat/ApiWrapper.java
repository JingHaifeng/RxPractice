package com.alex.rxpractice.rxcat;

import android.net.Uri;

import java.util.List;

/**
 * Created by alex on 16-3-7.
 */
public class ApiWrapper {
    Api api;

    public AsyncJob<List<Cat>> queryCats(final String query) {
        return new AsyncJob<List<Cat>>() {
            @Override
            public void start(final Callback<List<Cat>> callback) {
                api.queryCats(query, new Api.CatsQueryCallback() {
                    @Override
                    public void onCatListReceived(List<Cat> cats) {
                        callback.onResult(cats);
                    }

                    @Override
                    public void onError(Exception e) {
                        callback.onError(e);
                    }
                });
            }
        };
    }

    public AsyncJob<Uri> store(final Cat cat) {
        return new AsyncJob<Uri>() {
            @Override
            public void start(final Callback<Uri> callback) {
                api.store(cat, new Api.StoreCallback() {
                    @Override
                    public void onCateStored(Uri savedUri) {
                        callback.onResult(savedUri);
                    }

                    @Override
                    public void onStoreFailed(Exception e) {
                        callback.onError(e);
                    }
                });
            }
        };
    }
}
