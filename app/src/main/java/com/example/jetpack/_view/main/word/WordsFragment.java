package com.example.jetpack._view.main.word;


import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.jetpack.R;
import com.example.jetpack._view.adapters.WordListAdapter;
import com.example.jetpack._model.database.Word;
import com.example.jetpack._view._base.BasicMethods;
import com.example.jetpack._view.newword.NewWordActivity;
import com.example.jetpack._viewmodel.WordViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 */
public class WordsFragment extends Fragment implements BasicMethods {


    @BindView(R.id.fabAddWord)
    FloatingActionButton fabAddWord;

    @BindView(R.id.recyclerviewWords)
    RecyclerView recyclerviewWords;

    private View view;

    private WordListAdapter adapter = null;
    private LinearLayoutManager linearLayoutManager;

    private WordViewModel mWordViewModel;
    private static final int REQUEST_CODE_NEW_WORD = 990;

    public WordsFragment() {
        // Required empty public constructor
    }

    private static WordsFragment INSTANCE = null;

    public static synchronized WordsFragment getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new WordsFragment();
        }
        return (INSTANCE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_words, container, false);
        ButterKnife.bind(this, view);

        init();
        initListeners();
        return view;
    }

    @Override
    public void init() {
        initRecyclewView();

        mWordViewModel = ViewModelProviders.of(this).get(WordViewModel.class);
        mWordViewModel.getAllWords().observe(this, words -> {
            adapter.setWords(words);
        });

    }

    @Override
    public void initListeners() {
        fabAddWord.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), NewWordActivity.class);
            getActivity().startActivityForResult(intent, REQUEST_CODE_NEW_WORD);
        });
    }

    private void initRecyclewView() {
        adapter = new WordListAdapter(getContext());
        linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerviewWords.setAdapter(adapter);
        recyclerviewWords.setLayoutManager(linearLayoutManager);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_NEW_WORD && resultCode == RESULT_OK) {
            Word word = new Word(data.getStringExtra(NewWordActivity.EXTRA_WORD_CREATE));
            mWordViewModel.insert(word);
        } else {
            Toast.makeText(
                    getActivity().getApplicationContext(),
                    R.string.empty_not_saved,
                    Toast.LENGTH_LONG).show();
        }
    }
}
