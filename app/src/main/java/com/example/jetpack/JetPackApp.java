package com.example.jetpack;

import android.app.Application;

import com.example.jetpack._model.shared.PreferencesManager;

public class JetPackApp extends Application {


    @Override
    public void onCreate() {
        super.onCreate();
        //Aqui deben ir todas las inicializaciones o clases que requieran contexto de la aplicacion.
        PreferencesManager.inititilize(this);
    }
}
