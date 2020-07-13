package com.example.newsapiapp.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.newsapiapp.model.ArticleUrl;

public class SharedViewModel extends ViewModel {
    private final MutableLiveData<ArticleUrl> selected = new MutableLiveData<ArticleUrl>();

    public void select(ArticleUrl articleUrl) {
        selected.setValue(articleUrl);
    }

    public LiveData<ArticleUrl> getSelected() {
        return selected;
    }
}
