package com.example.jetpack._model.networking.openweatherapi;

import com.example.jetpack._model.pojo.openweatherapi.WeatherForecast;
import com.example.jetpack._model.pojo.openweatherapi.WeatherLocation;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface OpenWeatherMapApi {

    @GET("/data/2.5/weather")
    Call<WeatherLocation> getDataWeather(@Query("lat") Double lat, @Query("lon") Double lng, @Query("appid") String apikey);

    @GET("/data/2.5/forecast")
    Call<WeatherForecast> getDataForecast(@Query("lat") Double lat, @Query("lon") Double lng, @Query("appid") String apikey);

}