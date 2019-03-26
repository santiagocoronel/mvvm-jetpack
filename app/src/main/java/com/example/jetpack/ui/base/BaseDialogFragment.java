package com.example.jetpack._view._base;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.Log;

public abstract class BaseDialogFragment extends DialogFragment {

    protected final String TAG = this.getClass().getSimpleName();

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Log.d(TAG, "OPEN ACTIVITY " + TAG);
        return super.onCreateDialog(savedInstanceState);
    }
}
