package com.example.newsapiapp.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.newsapiapp.model.ArticleUrl;
import com.example.newsapiapp.model.Source;

import java.security.PrivateKey;

public class SharedViewModel extends ViewModel {
    private final MutableLiveData<ArticleUrl> selected = new MutableLiveData<ArticleUrl>();
    private final MutableLiveData<Source> selectedSource = new MutableLiveData<Source>();

    public void select(ArticleUrl articleUrl) {
        selected.setValue(articleUrl);
    }

    public void toSource(Source source) {
        selectedSource.setValue(source);
    }

    public LiveData<ArticleUrl> getSelected() {

        return selected;
    }

    public LiveData<Source> getSelectedSource() {
        return selectedSource;
    }
}
