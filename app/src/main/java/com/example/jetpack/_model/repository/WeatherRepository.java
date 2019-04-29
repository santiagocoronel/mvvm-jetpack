package com.example.jetpack._model.repository;

import com.example.jetpack._model.networking._base.OnResponse;
import com.example.jetpack._model.networking._base.ServiceGenerator;
import com.example.jetpack._model.networking.openweatherapi.OpenWeatherMapApi;
import com.example.jetpack._model.pojo.openweatherapi.WeatherLocation;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WeatherRepository {


    private final String BASE_URL = "https://openweathermap.org/";
    private final String API_KEY = "b6907d289e10d714a6e88b30761fae22";

    private final OpenWeatherMapApi apiService;


    public WeatherRepository() {
        apiService = ServiceGenerator.createService(BASE_URL, null, null, OpenWeatherMapApi.class);
    }

    public void refreshCurrentWeatherLocation(double lat, double lng, int requestCode, OnResponse onResponse) {
        apiService.getDataWeather(lat, lng, API_KEY).enqueue(new Callback<WeatherLocation>() {
            @Override
            public void onResponse(Call<WeatherLocation> call, Response<WeatherLocation> response) {
                if (response.isSuccessful()) {
                    onResponse.onSuccess(requestCode, response);
                } else if (response.code() == 403) {
                    onResponse.onAuthorizationError(requestCode, response);
                } else {
                    onResponse.onError(requestCode, response);
                }
            }

            @Override
            public void onFailure(Call<WeatherLocation> call, Throwable t) {
                onResponse.onFailure(requestCode, t);
            }
        });
    }

}
