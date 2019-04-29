package com.example.jetpack._viewmodel._base;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;

import com.example.jetpack._model.networking.OnResponse;

public abstract class BaseViewModel extends AndroidViewModel implements OnResponse {

    public BaseViewModel(@NonNull Application application) {
        super(application);
    }


}
