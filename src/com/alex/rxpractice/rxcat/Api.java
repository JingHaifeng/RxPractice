package com.alex.rxpractice.rxcat;

import android.net.Uri;

import java.util.List;

/**
 * Created by alex on 16-3-7.
 */
public interface Api {

    interface CatsQueryCallback {
        void onCatListReceived(List<Cat> cats);

        void onError(Exception e);
    }

    interface StoreCallback {
        void onCateStored(Uri savedUri);

        void onStoreFailed(Exception e);
    }

    List<Cat> queryCats(String query, CatsQueryCallback catsQueryCallback);

    Uri store(Cat cat, StoreCallback storeCallback);
}
