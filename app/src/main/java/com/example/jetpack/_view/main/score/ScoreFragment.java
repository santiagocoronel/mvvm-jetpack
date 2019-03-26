package com.example.jetpack._view.main.score;


import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.jetpack.R;
import com.example.jetpack._view._base.BasicMethods;
import com.example.jetpack._viewmodel.ScoreViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class ScoreFragment extends Fragment implements BasicMethods {

    @BindView(R.id.textViewPointsBlue)
    TextView textViewPointsBlue;

    @BindView(R.id.buttonAddBlue)
    Button buttonAddBlue;

    @BindView(R.id.buttonSubtractBlue)
    Button buttonSubtractBlue;

    @BindView(R.id.textViewPointsRed)
    TextView textViewPointsRed;

    @BindView(R.id.buttonAddRed)
    Button buttonAddRed;

    @BindView(R.id.buttonSubtractRed)
    Button buttonSubtractRed;

    private ScoreViewModel scoreViewModel;

    public ScoreFragment() {
        // Required empty public constructor
    }

    private static ScoreFragment INSTANCE = null;

    public static synchronized ScoreFragment getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ScoreFragment();
        }
        return (INSTANCE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_score, container, false);
        ButterKnife.bind(this, view);
        scoreViewModel = ViewModelProviders.of(this).get(ScoreViewModel.class);

        init();
        initListeners();
        return view;
    }


    @Override
    public void init() {
        displayBlue();
        displayRed();
    }

    @Override
    public void initListeners() {
        buttonAddBlue.setOnClickListener(v -> {
            scoreViewModel.scoreTeamA = scoreViewModel.scoreTeamA + 1;
            displayBlue();
        });
        buttonSubtractBlue.setOnClickListener(v -> {
            scoreViewModel.scoreTeamA = scoreViewModel.scoreTeamA - 1;
            displayBlue();
        });
        buttonAddRed.setOnClickListener(v -> {
            scoreViewModel.scoreTeamB = scoreViewModel.scoreTeamB + 1;
            displayRed();
        });
        buttonSubtractRed.setOnClickListener(v -> {
            scoreViewModel.scoreTeamB = scoreViewModel.scoreTeamB - 1;
            displayRed();
        });
    }

    private void displayBlue() {
        textViewPointsBlue.setText(String.valueOf(scoreViewModel.scoreTeamA));
    }

    private void displayRed() {
        textViewPointsRed.setText(String.valueOf(scoreViewModel.scoreTeamB));
    }
}
