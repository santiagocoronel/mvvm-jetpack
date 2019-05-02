package com.example.jetpack._viewmodel.weather;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.example.jetpack._model.pojo.openweatherapi.WeatherLocation;
import com.example.jetpack._model.repository._base.OnResponse;
import com.example.jetpack._model.repository.weather.WeatherRepository;
import com.example.jetpack._model.shared.PreferencesManager;
import com.example.jetpack._viewmodel._base.BaseViewModel;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import java.util.List;

public class WeatherViewModel extends BaseViewModel {

    private WeatherRepository weatherRepository;

    private final MutableLiveData<WeatherLocation> currentWeatherLocation = new MutableLiveData<>();
    private final MutableLiveData<String> currentMessageError = new MutableLiveData<>();

    public WeatherViewModel(@NonNull Application application) {
        super(application);
        weatherRepository = new WeatherRepository();
    }

    public LiveData<WeatherLocation> getCurrentWeatherLocation() {
        return currentWeatherLocation;
    }

    public void setWeatherRepository(WeatherLocation weatherLocation) {
        currentWeatherLocation.postValue(weatherLocation);
    }

    public MutableLiveData<String> getCurrentMessageError() {
        return currentMessageError;
    }

    public void setCurrentMessageError(String msgError) {
        currentMessageError.postValue(msgError);
    }

    public void getCurrentWeatherLocation(double lat, double lng) {

        //primero busca en local. donde en realidad deberia ver si la informacion es vieja primero
        String weatherJson = PreferencesManager.getInstance().getKey(PreferencesManager.KEY_WEATHER_CACHE);
        if (weatherJson != null) {
            Gson gson = new GsonBuilder().create();
            JsonObject jsonObject = gson.toJsonTree(weatherJson).getAsJsonObject();
            WeatherLocation weatherLocation = gson.fromJson(jsonObject.toString(), WeatherLocation.class);
            currentWeatherLocation.postValue(weatherLocation);
            return;
        }

        //sino lo descarga de la api
        weatherRepository.getCurrentWeatherLocation(lat, lng, new OnResponse<WeatherLocation>() {
            @Override
            public void OnResponse(WeatherLocation entity) {
                currentWeatherLocation.postValue(entity);
                //despues de descargar la informacion la guarda localmente.
                PreferencesManager.getInstance().saveKey(PreferencesManager.KEY_WEATHER_CACHE, new Gson().toJson(entity)); //save local
            }

            @Override
            public void OnResponse(List<WeatherLocation> listEntity) {

            }

            @Override
            public void onError(int code, String error) {
                //fallo la consulta a la api.
                currentMessageError.postValue(error + " code: " + code);
            }
        });
    }



}
