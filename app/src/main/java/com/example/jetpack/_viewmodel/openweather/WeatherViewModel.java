package com.example.jetpack._viewmodel.openweather;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.example.jetpack._model.database.openweather.clima.ClimaEntity;
import com.example.jetpack._model.pojo.openweather.WeatherLocation;
import com.example.jetpack._model.repository._base.OnResponse;
import com.example.jetpack._model.repository.openweather.WeatherRepository;
import com.example.jetpack._viewmodel._base.BaseViewModel;
import com.example.jetpack.util.OnVoidListener;
import com.google.android.gms.maps.model.LatLng;

import java.util.List;

public class WeatherViewModel extends BaseViewModel {

    private WeatherRepository weatherRepository;

    @Deprecated
    private final MutableLiveData<WeatherLocation> currentWeatherLocation = new MutableLiveData<>();
    private final MutableLiveData<ClimaEntity> climaActual = new MutableLiveData<>();


    private MutableLiveData<LatLng> latLng = new MutableLiveData<>();

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

    public MutableLiveData<ClimaEntity> getClimaActual() {
        return climaActual;
    }

    public MutableLiveData<LatLng> getLatLng() {
        return latLng;
    }

    public void setLatLng(LatLng latLng) {
        this.latLng.postValue(latLng);
        refrescarClima();
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
                WeatherViewModel.this.error.postValue(error + " code: " + code);
            }
        });
    }

    public LiveData<ClimaEntity> getClima() {
        return weatherRepository.getClima();
    }

    public void refrescarClima() {
        weatherRepository.refrescarClima(latLng.getValue(), new OnVoidListener() {
            @Override
            public void onStartProcess() {
                setLoading("Aguarde...");
            }

            @Override
            public void onFinishProcess() {
                setLoading(null);
            }

            @Override
            public void onSuccess() {

            }

            @Override
            public void onError(String error) {
                setError(error);
            }
        });
    }

    @Override
    public void cancelRequestData() {
        super.cancelRequestData();
        weatherRepository.cancelRequestData();
    }
}
