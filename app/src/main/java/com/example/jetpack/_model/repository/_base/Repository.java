package com.example.jetpack._model.repository._base;

import android.support.annotation.Nullable;

import com.example.jetpack.util.OnVoidListener;

import retrofit2.Call;

public abstract class Repository {

    protected final String BASE_URL = "";
    protected final String API_KEY = "";

    protected Call currentCall = null;

    protected final int totalRetries = 3; //total de reintentos para regenerar un authtoken
    protected final int graceTime = 15; //tiempo de gracia para el proximo reintentos de generar un nuevo auth token.

    protected void regenerateAuthToken(@Nullable OnVoidListener onVoidListener) {

        //codigo para recuperar una nueva auth token.

    }

    public boolean checkLifeTime(long lastTime, long lifeTimeMins) {

        long tiempoRestante = (System.currentTimeMillis() - lastTime);
        long tiempoAceptable = (lifeTimeMins * 60000);

        return !(tiempoRestante <= tiempoAceptable);
    }

    public void cancelRequestData() {
        if (currentCall != null && currentCall.isExecuted()) {
            currentCall.cancel();
        }
    }
}
