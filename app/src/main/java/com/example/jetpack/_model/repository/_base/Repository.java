package com.example.jetpack._model.repository._base;

import android.support.annotation.Nullable;

import com.example.jetpack.util.OnVoidListener;

public class Repository {

    protected final int totalRetries = 3; //total de reintentos para regenerar un authtoken
    protected final int graceTime = 15; //tiempo de gracia para el proximo reintentos de generar un nuevo auth token.

    protected void regenerateAuthToken(@Nullable OnVoidListener onVoidListener){

        //codigo para recuperar una nueva auth token.

    }

    public boolean checkLifeTime(long lastTime, long lifeTimeMins){
        return (System.currentTimeMillis() - lastTime) < (lifeTimeMins * 60000);
    }
}
