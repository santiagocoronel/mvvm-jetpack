package com.example.jetpack._view.main.word;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;

import com.example.jetpack.R;
import com.example.jetpack._view._base.BaseActivity;
import com.example.jetpack._view._base.BasicMethods;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewWordActivity extends BaseActivity implements BasicMethods {

    public static final String EXTRA_WORD_CREATE = "extra_word_create";

    @BindView(R.id.editTextWord)
    EditText editTextWord;

    @BindView(R.id.buttonSave)
    Button buttonSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_word);
        ButterKnife.bind(this);

        init();
        initListeners();
    }

    @Override
    public void init() {

    }

    @Override
    public void initListeners() {
        buttonSave.setOnClickListener(view -> {
            Intent replyIntent = new Intent();
            if (TextUtils.isEmpty(editTextWord.getText())) {
                setResult(RESULT_CANCELED, replyIntent);
            } else {
                String word = editTextWord.getText().toString();
                replyIntent.putExtra(EXTRA_WORD_CREATE, word);
                setResult(RESULT_OK, replyIntent);
            }
            finish();
        });
    }
}
