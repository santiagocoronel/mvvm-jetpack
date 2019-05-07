package com.example.jetpack._viewmodel.weather;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.example.jetpack._model.database.openweather.clima.ClimaEntity;
import com.example.jetpack._model.pojo.openweather.WeatherLocation;
import com.example.jetpack._model.repository._base.OnResponse;
import com.example.jetpack._model.repository.openweather.WeatherRepository;
import com.example.jetpack._viewmodel._base.BaseViewModel;

import java.util.List;

public class WeatherViewModel extends BaseViewModel {

    private WeatherRepository weatherRepository;

    @Deprecated
    private final MutableLiveData<WeatherLocation> currentWeatherLocation = new MutableLiveData<>();
    private final MutableLiveData<String> currentMessageError = new MutableLiveData<>();
    private final MutableLiveData<ClimaEntity> climaActual = new MutableLiveData<>();

    public WeatherViewModel(@NonNull Application application) {
        super(application);
        weatherRepository = new WeatherRepository(application);
    }

    //region getter and setter

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

    public MutableLiveData<ClimaEntity> getClimaActual() {
        return climaActual;
    }

    //endregion

    @Deprecated
    public void getCurrentWeatherLocation(double lat, double lng) {

        weatherRepository.getCurrentWeatherLocation(lat, lng, new OnResponse<WeatherLocation>() {
            @Override
            public void OnResponse(WeatherLocation entity) {
                //una respuesta de un solo objeto
                currentWeatherLocation.postValue(entity);
            }

            @Override
            public void OnResponse(List<WeatherLocation> listEntity) {
                //una respuesta de una lista de objectos.

            }

            @Override
            public void onError(int code, String error) {
                //error al obtener informacion
                currentMessageError.postValue(error + " code: " + code);
            }
        });
    }

    public void obtenerClimaActual(double lat, double lng) {
        weatherRepository.obtenerClimaActual(lat, lng, new OnResponse<ClimaEntity>() {
            @Override
            public void OnResponse(ClimaEntity entity) {
                climaActual.postValue(entity);
            }

            @Override
            public void OnResponse(List<ClimaEntity> listEntity) {

            }

            @Override
            public void onError(int code, String error) {
                currentMessageError.postValue(error + " code: " + code);
            }
        });
    }

}
