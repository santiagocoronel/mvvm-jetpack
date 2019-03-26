package com.example.jetpack._view._base;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;


public abstract class BaseFragment extends Fragment {

    protected final String TAG = this.getClass().getSimpleName();

    protected BaseActivity activity;

    public BaseFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        Log.d(TAG, "OPEN FRAGMENT " + TAG);
        super.onCreate(savedInstanceState);
    }
}