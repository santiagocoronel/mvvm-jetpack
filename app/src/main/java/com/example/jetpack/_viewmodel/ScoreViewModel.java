package com.example.jetpack._viewmodel;

import android.app.Application;
import android.support.annotation.NonNull;

import com.example.jetpack._model.networking._base.OnResponse;
import com.example.jetpack._viewmodel._base.BaseViewModel;

import retrofit2.Response;

public class ScoreViewModel extends BaseViewModel implements OnResponse {
    // Tracks the score for Team A
    public int scoreTeamA = 0;

    // Tracks the score for Team B
    public int scoreTeamB = 0;

    public ScoreViewModel(@NonNull Application application) {
        super(application);
    }

    //region networking
    @Override
    public void onSuccess(int requestCode, Response successResponse) {

    }

    @Override
    public void onError(int requestCode, Response errorResponse) {

    }

    @Override
    public void onAuthorizationError(int requestCode, Response authorizationResponse) {

    }

    @Override
    public void onFailure(int requestCode, Throwable t) {

    }
    //endregion
}
