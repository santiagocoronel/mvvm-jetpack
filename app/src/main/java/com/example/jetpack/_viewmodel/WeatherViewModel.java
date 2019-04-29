package com.example.jetpack._viewmodel;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.example.jetpack._model.pojo.openweatherapi.WeatherLocation;
import com.example.jetpack._model.repository.WeatherRepository;
import com.example.jetpack._viewmodel._base.BaseViewModel;

import retrofit2.Response;

public class WeatherViewModel extends BaseViewModel {

    private WeatherRepository weatherRepository;

    private final MutableLiveData<WeatherLocation> currentWeatherLocation = new MutableLiveData<>();
    private final MutableLiveData<String> currentMessageError = new MutableLiveData<>();


    public static final int REQUEST_REFRESH_CURRENT_WEATHER_LOCATION = 1542;

    public WeatherViewModel(@NonNull Application application) {
        super(application);
        weatherRepository = new WeatherRepository();
    }


    public void refreshCurrentWeatherLocation(double lat, double lng) {
        weatherRepository.refreshCurrentWeatherLocation(lat, lng, REQUEST_REFRESH_CURRENT_WEATHER_LOCATION, this);
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

    @Override
    public void onSuccess(int requestCode, Response successResponse) {
        switch (requestCode) {
            //region REQUEST_REFRESH_CURRENT_WEATHER_LOCATION
            case REQUEST_REFRESH_CURRENT_WEATHER_LOCATION:
                currentWeatherLocation.postValue((WeatherLocation) successResponse.body());
                break;
            //endregion

        }
    }

    @Override
    public void onError(int requestCode, Response errorResponse) {
        switch (requestCode) {
            //region REQUEST_REFRESH_CURRENT_WEATHER_LOCATION
            case REQUEST_REFRESH_CURRENT_WEATHER_LOCATION:
                //Toast.makeText(this.getApplication().getApplicationContext(), "Ocurrio un error", Toast.LENGTH_SHORT).show();
                currentMessageError.postValue("Ocurrio un error al intentar recuperar datos del servidor");
                break;
            //endregion

        }
    }

    @Override
    public void onAuthorizationError(int requestCode, Response authorizationResponse) {

    }

    @Override
    public void onFailure(int requestCode, Throwable t) {
        switch (requestCode) {
            //region REQUEST_REFRESH_CURRENT_WEATHER_LOCATION
            case REQUEST_REFRESH_CURRENT_WEATHER_LOCATION:
                //Toast.makeText(this.getApplication().getApplicationContext(), "Algo salio mal", Toast.LENGTH_SHORT).show();
                currentMessageError.postValue("Ups algo salió mal");
                break;
            //endregion

        }
    }
}
