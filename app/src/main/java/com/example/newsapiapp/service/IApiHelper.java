package com.example.newsapiapp.service;

import com.example.newsapiapp.model.Article;
import com.example.newsapiapp.model.Headline;
import com.example.newsapiapp.model.News;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface IApiHelper {
        //https://newsapi.org/v2/top-headlines?country=us&apiKey=98b0e7a42d3f4579a952c81ad267a17a
        @GET("top-headlines")
        Observable<News> getTopHeadlines(@Query("country") String country, @Query("apiKey") String apiKey);


//        @GET("top_headlines")
//        Observable<Article> getArticle(@Path("url") String url);
}
