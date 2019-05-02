package com.example.jetpack._model.repository._base;

import java.util.List;

public interface OnResponse<T> {

    void OnResponse(T entity); //cuando la respuesta aplica para un unico objeto

    void OnResponse(List<T> listEntity); //cuando la respuesta aplica para una lista de objetos

    void onError(int code, String error); //cuando la respuesta es un error.

}
