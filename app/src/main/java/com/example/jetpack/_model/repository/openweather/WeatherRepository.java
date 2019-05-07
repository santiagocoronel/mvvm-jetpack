package com.example.jetpack._model.repository.openweather;

import android.app.Application;
import android.arch.lifecycle.LiveData;

import com.example.jetpack._model.database.openweather.OpenWeatherDataBase;
import com.example.jetpack._model.database.openweather.clima.ClimaDao;
import com.example.jetpack._model.database.openweather.clima.ClimaEntity;
import com.example.jetpack._model.networking._base.ServiceGenerator;
import com.example.jetpack._model.networking.openweather.OpenWeatherMapApi;
import com.example.jetpack._model.pojo.openweather.WeatherForecast;
import com.example.jetpack._model.pojo.openweather.WeatherLocation;
import com.example.jetpack._model.repository._base.OnResponse;
import com.example.jetpack._model.repository._base.Repository;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WeatherRepository extends Repository {


    private final String BASE_URL = "https://openweathermap.org/";
    private final String API_KEY = "b6907d289e10d714a6e88b30761fae22";

    private final OpenWeatherMapApi apiService;

    private ClimaDao climaDao;
    private LiveData<ClimaEntity> clima;


    public WeatherRepository(Application application) {
        apiService = ServiceGenerator.createService(BASE_URL, null, null, OpenWeatherMapApi.class);

        OpenWeatherDataBase db = OpenWeatherDataBase.getDatabase(application);
        climaDao = db.climaDao();
        clima = climaDao.get();
    }

    public void getCurrentWeatherLocation(double lat, double lng, OnResponse onResponse) {
        Call<WeatherLocation> call = apiService.getDataWeather(lat, lng, API_KEY);
        Callback<WeatherLocation> callBack = new Callback<WeatherLocation>() {
            @Override
            public void onResponse(Call<WeatherLocation> call, Response<WeatherLocation> response) {
                if (response.isSuccessful()) {
                    onResponse.OnResponse(response.body());
                } else if (response.code() == 403) {
                    //requiere un tratado especial de reintento con recuperacion de auth token.
                    onResponse.onError(response.code(), "error de autorizacion");
                } else {
                    onResponse.onError(response.code(), response.errorBody().toString());
                }
            }

            @Override
            public void onFailure(Call<WeatherLocation> call, Throwable t) {
                onResponse.onError(000, t.getMessage());
            }
        };
        call.enqueue(callBack);
    }

    public void getCurrentForecastWeatherLocation(double lat, double lng, OnResponse onResponse) {
        Call<WeatherForecast> call = apiService.getDataForecast(lat, lng, API_KEY);
        Callback<WeatherForecast> callBack = new Callback<WeatherForecast>() {
            @Override
            public void onResponse(Call<WeatherForecast> call, Response<WeatherForecast> response) {
                if (response.isSuccessful()) {
                    onResponse.OnResponse(response.body());
                } else if (response.code() == 403) {
                    //requiere un tratado especial de reintento con recuperacion de auth token.
                    onResponse.onError(response.code(), "error de autorizacion");
                } else {
                    onResponse.onError(response.code(), response.errorBody().toString());
                }
            }

            @Override
            public void onFailure(Call<WeatherForecast> call, Throwable t) {
                onResponse.onError(000, t.getMessage());
            }
        };
        call.enqueue(callBack);
    }

    public LiveData<ClimaEntity> getClima() {
        return clima;
    }
}
