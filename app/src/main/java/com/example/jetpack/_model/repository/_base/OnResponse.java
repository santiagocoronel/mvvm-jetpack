package com.example.jetpack._model.repository._base;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.List;

public interface OnResponse<T> {

    void onResponse(@NonNull ResponseType responseType, @Nullable T entity, @Nullable List<T> listEntity);

    void onError(int code, String error);

}
