package com.example.jetpack._view.weather;

import android.Manifest;
import android.annotation.SuppressLint;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jetpack.JetPackApp;
import com.example.jetpack.R;
import com.example.jetpack._model.database.openweather.clima.ClimaEntity;
import com.example.jetpack._model.pojo.openweather.WeatherLocation;
import com.example.jetpack._view._base.BaseActivity;
import com.example.jetpack._view._base.BasicMethods;
import com.example.jetpack._viewmodel.openweather.WeatherViewModel;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.model.LatLng;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WeatherActivity extends BaseActivity implements BasicMethods {

    private static final int REQUEST_PERMISSION_LOCATION = 2356;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.textViewCityName)
    TextView textViewCityName;

    @BindView(R.id.textViewMaxTemp)
    TextView textViewMaxTemp;

    @BindView(R.id.textViewMinTemp)
    TextView textViewMinTemp;

    @BindView(R.id.fab)
    FloatingActionButton fab;

    private FusedLocationProviderClient fusedLocationClient;
    private LocationRequest locationRequest;
    private LocationCallback locationCallback;
    private WeatherViewModel weatherViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        init();
        initListeners();


    }

    @Override
    protected void onResume() {
        super.onResume();
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        //super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {

            case REQUEST_PERMISSION_LOCATION:
                boolean resultAccessFineLocation = checkPermission(Manifest.permission.ACCESS_FINE_LOCATION, permissions, grantResults);
                boolean resultAccessCoarseLocation = checkPermission(Manifest.permission.ACCESS_COARSE_LOCATION, permissions, grantResults);
                if (resultAccessFineLocation && resultAccessCoarseLocation) {
                    getLastKnowLocation();
                    //startLocationUpdates();
                } else {
                    requestPermission();
                }
                break;
        }

    }

    @Override
    public void init() {
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        initLocation();
        weatherViewModel = ViewModelProviders.of(this).get(WeatherViewModel.class);
        /*weatherViewModel.getCurrentWeatherLocation().observe(this, weatherLocation -> {
            if (weatherLocation != null) {
                updateUI(weatherLocation);
            }
        });*/

        weatherViewModel.getClima().observe(this, climaEntity -> {
                    if (climaEntity != null) {
                        updateUI(climaEntity);
                    }
                }
        );

        weatherViewModel.getCurrentMessageError().observe(this, s -> {
            if (s != null) {
                showError(s);
            }
        });
    }

    @Override
    public void initListeners() {
        fab.setOnClickListener(view -> Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show());
    }

    private void initLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            //showDialogError(001, "Error de permisos de localizacion");
            requestPermission();
            return;
        }
        getLastKnowLocation();
        //startLocationUpdates();
    }

    private void requestPermission() {
        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION},
                REQUEST_PERMISSION_LOCATION);
    }

    @SuppressLint("MissingPermission")
    private void getLastKnowLocation() {
        if (((JetPackApp) getApplication()).mGoogleApiClient.isConnected()) {


            fusedLocationClient.getLastLocation()
                    .addOnSuccessListener(this, location -> {
                        double lat = 0d;
                        double lng = 0d;
                        // Got last known location. In some rare situations this can be null.
                        if (location != null) {
                            Toast.makeText(this, "lat: " + location.getLatitude() + " lng: " + location.getLongitude(), Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(this, "Se tomo localizacion default", Toast.LENGTH_SHORT).show();
                            lat = -31.4195404d;
                            lng = -64.2084953d;

                        }
                        //showProgressBar("Aguarde", true);
                        //weatherViewModel.getCurrentWeatherLocation(lat, lng);
                        //weatherViewModel.getClima();
                        weatherViewModel.setLatLng(new LatLng(lat, lng));
                    });
        }
    }

    @SuppressLint("MissingPermission")
    private void startLocationUpdates() {
        createLocationRequest();

        locationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult locationResult) {
                if (locationResult == null) {
                    return;
                }
                for (Location location : locationResult.getLocations()) {
                    // Update UI with location data
                    // ...
                    Toast.makeText(WeatherActivity.this, "lat: " + location.getLatitude() + " lng: " + location.getLongitude(), Toast.LENGTH_SHORT).show();
                }
            }


        };
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        fusedLocationClient.requestLocationUpdates(locationRequest,
                locationCallback,
                null /* Looper */);

    }

    protected void createLocationRequest() {
        locationRequest = LocationRequest.create();
        locationRequest.setInterval(10000);
        locationRequest.setFastestInterval(5000);
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

    }

    @Deprecated
    private void updateUI(WeatherLocation weatherLocation) {
        dismissProgressBar();
        textViewCityName.setText(weatherLocation.getName());
        textViewMaxTemp.setText(weatherLocation.getWeather().get(0).getDescription());
        textViewMinTemp.setText(weatherLocation.getWind().getSpeed() + "");
    }

    private void updateUI(ClimaEntity climaEntity) {
        dismissProgressBar();
        textViewCityName.setText(climaEntity.getCiudad());
        textViewMaxTemp.setText(climaEntity.getTemperatura() + "");
        textViewMinTemp.setText(climaEntity.getVelocidad() + "");
    }

    private void showError(String s) {
        dismissProgressBar();
        showDialogMessage("Error", s);
        weatherViewModel.setCurrentMessageError(null);
    }

}
