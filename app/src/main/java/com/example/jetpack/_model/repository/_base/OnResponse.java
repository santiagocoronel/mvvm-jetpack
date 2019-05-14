package com.example.jetpack._model.repository._base;

import android.support.annotation.Nullable;

import java.util.List;

public interface OnResponse<T> {

    void onResponse(@Nullable T entity,@Nullable List<T> listEntity); //cuando la respuesta aplica para un unico objeto o lista

    void onError(int code, String error); //cuando la respuesta es un error.

}
