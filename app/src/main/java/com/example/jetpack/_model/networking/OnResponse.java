package com.example.jetpack._model.networking;

import retrofit2.Response;

/**
 * Created by FerJuarez on 25/9/2017.
 */

public interface OnResponse<T> {

    void onSuccess(int requestCode, Response successResponse);

    void onError(int requestCode, Response errorResponse);

    void onAuthorizationError(int requestCode, Response authorizationResponse);

    void onFailure(int requestCode, Throwable t);

}
