package com.example.jetpack._viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import com.example.jetpack._model.database.Word;
import com.example.jetpack._model.repository.WordRepository;
import com.example.jetpack._viewmodel._base.BaseViewModel;

import java.util.List;

import retrofit2.Response;

public class WordViewModel extends BaseViewModel {

    private WordRepository mRepository;

    private LiveData<List<Word>> mAllWords;

    public WordViewModel (Application application) {
        super(application);
        mRepository = new WordRepository(application);
        mAllWords = mRepository.getAllWords();
    }

    public LiveData<List<Word>> getAllWords() { return mAllWords; }

    public void insert(Word word) { mRepository.insert(word); }

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