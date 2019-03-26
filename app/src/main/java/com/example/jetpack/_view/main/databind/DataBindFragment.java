package com.example.jetpack._view.main.databind;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.jetpack.R;
import com.example.jetpack.databinding.FragmentDataBindBinding;
import com.example.jetpack._model.pojo.User;
import com.example.jetpack._view._base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class DataBindFragment extends BaseFragment {

    public static final String KEY_ARG_USER = "key_arg_user";

    private View view;
    private User user;
    private FragmentDataBindBinding binding;

    @BindView(R.id.buttonCambiarNombre)
    Button buttonCambiarNombre;


    public DataBindFragment() {
        // Required empty public constructor
    }

    private static DataBindFragment INSTANCE = null;

    public static synchronized DataBindFragment getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new DataBindFragment();
        }
        return (INSTANCE);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle args = getArguments();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_data_bind, container, false);
        view = binding.getRoot();
        ButterKnife.bind(this,view);

        init();
        initListeners();
        return view;
    }

    private void init() {
        user = new User("Hector", "Santiago");
        binding.setUser(user);
    }

    private void initListeners() {
        buttonCambiarNombre.setOnClickListener(v -> {
            user.setFirstName("Matias");
        });
    }

}
