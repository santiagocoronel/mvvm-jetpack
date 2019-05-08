package com.example.jetpack.util;

public interface OnVoidListener {

    void onStartProcess();

    void onFinishProcess();

    void onSuccess();

    void onError(String error);


}
