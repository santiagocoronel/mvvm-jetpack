package com.example.jetpack._model.repository.openweather;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.example.jetpack._model.database.openweather.OpenWeatherDataBase;
import com.example.jetpack._model.database.openweather.clima.ClimaDao;
import com.example.jetpack._model.database.openweather.clima.ClimaEntity;
import com.example.jetpack._model.networking._base.ServiceGenerator;
import com.example.jetpack._model.networking.openweather.OpenWeatherMapApi;
import com.example.jetpack._model.pojo.openweather.WeatherForecast;
import com.example.jetpack._model.pojo.openweather.WeatherLocation;
import com.example.jetpack._model.repository._base.OnResponse;
import com.example.jetpack._model.repository._base.Repository;
import com.example.jetpack.util.OnVoidListener;
import com.google.android.gms.maps.model.LatLng;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WeatherRepository extends Repository {


    private final String BASE_URL = "https://openweathermap.org/";
    private final String API_KEY = "b6907d289e10d714a6e88b30761fae22";

    private final OpenWeatherMapApi apiService;

    private ClimaDao climaDao;
    private LiveData<ClimaEntity> clima;
    private long lastUpdateClima = 0l;
    private long lifeTimeClima = 1; //in minutes

    protected String basicAuth = "";
    protected String jwtToken = "";

    public WeatherRepository(Application application) {
        super(application);
        apiService = ServiceGenerator.createService(BASE_URL, null, null, OpenWeatherMapApi.class);

        OpenWeatherDataBase db = OpenWeatherDataBase.getDatabase(application);
        climaDao = db.climaDao();

        if (climaDao.get() != null) {
            clima = climaDao.get();
        }

    }

    public void getCurrentWeatherLocation(double lat, double lng, OnResponse onResponse) {
        Call<WeatherLocation> call = apiService.getDataWeather(lat, lng, API_KEY);
        Callback<WeatherLocation> callBack = new Callback<WeatherLocation>() {
            @Override
            public void onResponse(Call<WeatherLocation> call, Response<WeatherLocation> response) {
                if (response.isSuccessful()) {
                    onResponse.onResponse(response.body(), null);
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
                    onResponse.onResponse(response.body(), null);
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

    public void refrescarClima(LatLng latLng, OnVoidListener onVoidListener) {

        /**
         * Validamos que el clima sea distinto de null - sino lo descargamos desde la api.
         * Validamos que el tiempo de vida del clima en base de datos aun sea valido.
         * Validamos que la latitud no sea distinta.
         * Validamos que la longitud no sea distinta.
         * Si alguna de estas validaciones no se cumple se descarga desde la api
         * */

        /*if (clima.getValue() == null && latLng != null) {

            getWeatherFromNetwork(latLng.latitude, latLng.longitude);

        } else if (clima.getValue() != null &&
                checkLifeTime(lastUpdateClima, lifeTimeClima) && //tiempos de vida aun valido? requiere agregar el algoritmo
                latLng != null &&
                clima.getValue().getLatitud() == latLng.latitude &&
                clima.getValue().getLongitud() == latLng.longitude) {

            //descarga desde la api
            getWeatherFromNetwork(latLng.latitude, latLng.longitude);

        }*/

        if (latLng == null) return;

        if (lastUpdateClima == 0f) {
            getWeatherFromNetwork(latLng.latitude, latLng.longitude, onVoidListener);
        } else if (checkLifeTime(lastUpdateClima, lifeTimeClima)) {
            getWeatherFromNetwork(latLng.latitude, latLng.longitude, onVoidListener);
        }
    }


    private void getWeatherFromNetwork(double lat, double lng, OnVoidListener onVoidListener) {
        onVoidListener.onStartProcess();
        Call<WeatherLocation> call = apiService.getDataWeather(lat, lng, API_KEY);
        Callback<WeatherLocation> callBack = new Callback<WeatherLocation>() {
            @Override
            public void onResponse(Call<WeatherLocation> call, Response<WeatherLocation> response) {
                if (response.isSuccessful()) {
                    lastUpdateClima = System.currentTimeMillis();
                    new AsyncTask<String, String, String>() {
                        @Override
                        protected String doInBackground(String... strings) {
                            climaDao.insert(weatherToClima(response.body()));
                            return null;
                        }
                    }.execute();
                } else if (response.code() == 403) {
                    //requiere un tratado especial de reintento con recuperacion de auth token.
                    onVoidListener.onError("error de autorizacion");
                } else {
                    onVoidListener.onError(response.errorBody().toString());
                }

                onVoidListener.onFinishProcess();
                currentCall = null;
            }

            @Override
            public void onFailure(Call<WeatherLocation> call, Throwable t) {
                onVoidListener.onFinishProcess();
                onVoidListener.onError(t.getMessage());
                currentCall = null;
            }
        };
        call.enqueue(callBack);
        currentCall = call;
    }

    private ClimaEntity weatherToClima(WeatherLocation weatherLocation) {

        return new ClimaEntity.Builder()
                .id(1)
                .latitud(weatherLocation.getCoord().getLat())
                .longitud(weatherLocation.getCoord().getLon())
                .pais(weatherLocation.getSys().getCountry())
                .ciudad(weatherLocation.getSys().getCountry())
                .temperatura(weatherLocation.getMain().getTemp())
                .velocidad(weatherLocation.getWind().getSpeed())
                //agregar todos los atributos
                .build();
    }
}
