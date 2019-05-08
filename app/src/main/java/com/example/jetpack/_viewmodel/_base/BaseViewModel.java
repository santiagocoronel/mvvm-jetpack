package com.example.jetpack._viewmodel._base;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

public abstract class BaseViewModel extends AndroidViewModel {

    public static String ERROR_SOCKET_CLOSED = "socket closed"; //este error tambien ocurre cuando el usuario cancela una solicitud de alguna api

    protected MutableLiveData<String> loading = new MutableLiveData<>();
    protected MutableLiveData<String> error = new MutableLiveData<>();

    public BaseViewModel(@NonNull Application application) {
        super(application);
    }

    public MutableLiveData<String> getLoading() {
        return loading;
    }

    public void setLoading(String loading) {
        this.loading.postValue(loading);
    }

    public MutableLiveData<String> getError() {
        return error;
    }

    public void setError(String msgError) {

        if (msgError.trim().toLowerCase().equals(ERROR_SOCKET_CLOSED)) return;

        error.postValue(msgError);
    }

    public void cancelRequestData() {
    }
}
