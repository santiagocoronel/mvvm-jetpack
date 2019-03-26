package com.example.jetpack._view.navigation.nav;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.jetpack.R;
import com.example.jetpack._view._base.BasicMethods;

import androidx.navigation.Navigation;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class AFragment extends Fragment implements BasicMethods {


    private View view;

    @BindView(R.id.textViewLetter)
    TextView textViewLetter;

    public AFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_a, container, false);
        ButterKnife.bind(this, view);


        init();
        initListeners();
        return view;
    }

    @Override
    public void init() {

    }

    @Override
    public void initListeners() {
        textViewLetter.setOnClickListener(v -> Navigation.findNavController(view).navigate(R.id.action_AFragment_to_BFragment));
    }
}
