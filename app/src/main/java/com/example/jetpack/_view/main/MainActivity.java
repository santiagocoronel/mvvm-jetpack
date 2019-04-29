package com.example.jetpack._view.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.view.View;

import com.example.jetpack.R;
import com.example.jetpack._view._base.BaseActivity;
import com.example.jetpack._view._base.BasicMethods;
import com.example.jetpack._view.main.databind.DataBindFragment;
import com.example.jetpack._view.main.score.ScoreFragment;
import com.example.jetpack._view.main.word.WordsFragment;
import com.example.jetpack._view.navigation.NavigationExampleActivity;
import com.example.jetpack._view.weather.WeatherActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements BasicMethods {

    @BindView(R.id.navigation)
    BottomNavigationView navigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        init();
        initListeners();
    }

    @Override
    public void init() {

    }

    @Override
    public void initListeners() {
        navigation.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    replaceFragment(DataBindFragment.getInstance(), R.id.container, false);
                    return true;
                case R.id.navigation_dashboard:
                    replaceFragment(ScoreFragment.getInstance(), R.id.container, false);
                    return true;
                case R.id.navigation_notifications:
                    navigation.setVisibility(View.GONE);
                    replaceFragment(WordsFragment.getInstance(), R.id.container, false);
                    return true;
                case R.id.navigation_nav:
                    startActivity(new Intent(this, NavigationExampleActivity.class));
                    return true;
                case R.id.navigation_weather:
                    startActivity(new Intent(this, WeatherActivity.class));
                    return true;

            }
            return false;
        });
    }

    @Override
    public void onBackPressed() {

        if (currentFragment instanceof WordsFragment) {
            navigation.setVisibility(View.VISIBLE);
        } else {
            super.onBackPressed();
        }
    }
}
