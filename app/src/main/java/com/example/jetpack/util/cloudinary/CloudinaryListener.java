package com.example.jetpack.util.cloudinary;

public interface CloudinaryListener {
    void progress(int value);

    void onSuccess(String url);

    void onError(String description);

    void onReschedule(String description);
}
