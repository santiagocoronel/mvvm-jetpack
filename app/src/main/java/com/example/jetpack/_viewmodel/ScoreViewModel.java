package com.example.jetpack._viewmodel;

import android.app.Application;
import android.support.annotation.NonNull;

import com.example.jetpack._viewmodel._base.BaseViewModel;

public class ScoreViewModel extends BaseViewModel {
    // Tracks the score for Team A
    public int scoreTeamA = 0;

    // Tracks the score for Team B
    public int scoreTeamB = 0;

    public ScoreViewModel(@NonNull Application application) {
        super(application);
    }

}
