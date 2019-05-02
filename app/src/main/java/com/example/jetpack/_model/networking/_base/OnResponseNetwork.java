package com.example.jetpack._model.networking._base;

import retrofit2.Response;

public interface OnResponseNetwork<T> {

    void onSuccess(int requestCode, Response successResponse);

    void onError(int requestCode, Response errorResponse);

    void onAuthorizationError(int requestCode, Response authorizationResponse);

    void onFailure(int requestCode, Throwable t);

}
