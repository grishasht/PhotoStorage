package com.github.grishasht.photostorage.client.exception;

public class ImageClientException extends RuntimeException {

    private int statusCode;

    public ImageClientException(int statusCode) {
        this.statusCode = statusCode;
    }

    public ImageClientException(String message, int statusCode) {
        super(message);
        this.statusCode = statusCode;
    }

    public ImageClientException(String message, Throwable cause, int statusCode) {
        super(message, cause);
        this.statusCode = statusCode;
    }

    public ImageClientException(Throwable cause, int statusCode) {
        super(cause);
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return statusCode;
    }
}
