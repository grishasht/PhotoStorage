package com.github.grishasht.photostorage.client.factory.proxy;

import com.github.grishasht.photostorage.client.exception.ImageClientException;
import com.github.grishasht.photostorage.client.impl.ImageClientImpl;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class ImageClientInvocationHandler implements InvocationHandler {

    private final ImageClientImpl targetClient;
    private final Integer retryCount;

    public ImageClientInvocationHandler(ImageClientImpl targetClient, Integer retryCount) {
        this.targetClient = targetClient;
        this.retryCount = retryCount;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        for (int i = 0; i < retryCount; i++) {

            try {

                return method.invoke(targetClient, args);

            } catch (ImageClientException ex) {

                final int statusCode = ex.getStatusCode();

                if (statusCode / 100 != 4) {
                    return null;
                }
            }
        }

        return null;
    }
}
