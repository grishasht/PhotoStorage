package com.github.grishasht.photostorage.client.factory;

import com.github.grishasht.photostorage.client.ImageClient;
import com.github.grishasht.photostorage.client.impl.ImageClientImpl;

public class ImageClientFactory {

    // This should be moved to another save place, such us environment
    // variables or remote credential storage. But, because of a leak of time,
    // this key will be here.
    private final static String KEY_API = "23567b218376f79d9415";

    public ImageClient createDefaultInstance(){

        return new ImageClientImpl(KEY_API);
    }
}
