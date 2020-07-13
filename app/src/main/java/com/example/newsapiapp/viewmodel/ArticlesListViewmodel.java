package com.example.newsapiapp.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.newsapiapp.model.Article;
import com.example.newsapiapp.model.Constants;
import com.example.newsapiapp.model.Headline;
import com.example.newsapiapp.model.News;
import com.example.newsapiapp.repository.ArticlesRepository;

public class ArticlesListViewmodel extends AndroidViewModel  {

    ArticlesRepository articlesRepository;
    public ArticlesListViewmodel(@NonNull Application application) {
        super(application);
        articlesRepository= ArticlesRepository.getInstance();
    }


    public LiveData<News> getHeadline(){
        return articlesRepository.getArticlesList();
    }

//    public LiveData<Article> getArticle(String url){
////        return articlesRepository.getArticle(url);
//    }

    public LiveData<Boolean> getIsLoading(){
        LiveData<Boolean> isLoading=articlesRepository.isLoading;
        return isLoading;
    }
}
