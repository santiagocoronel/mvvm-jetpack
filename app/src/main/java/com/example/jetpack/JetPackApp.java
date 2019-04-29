package com.example.jetpack;

import android.app.Application;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.jetpack._model.shared.PreferencesManager;
import com.example.jetpack.util.cloudinary.CloudinaryUtils;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;

public class JetPackApp extends Application implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    private String TAG = getClass().getSimpleName();

    public GoogleApiClient mGoogleApiClient;

    @Override
    public void onCreate() {
        super.onCreate();
        //Aqui deben ir todas las inicializaciones o clases que requieran contexto de la aplicacion.
        PreferencesManager.inititilize(this);
        buildGoogleApiClient();
        //CloudinaryUtils.initCloudinary(this);

    }

    void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();

        mGoogleApiClient.connect();
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        Log.d(TAG, "onConnected GoogleApiClient");
    }

    @Override
    public void onConnectionSuspended(int i) {
        Log.d(TAG, "onConnectionSuspended GoogleApiClient");
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.d(TAG, "onConnectionFailed GoogleApiClient");
    }
}
