package com.example.newsapiapp.service;

import com.example.newsapiapp.model.Article;
import com.example.newsapiapp.model.Headline;
import com.example.newsapiapp.model.News;
import com.example.newsapiapp.model.Source;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface IApiHelper {
        //https://newsapi.org/v2/top-headlines?country=us&apiKey=98b0e7a42d3f4579a952c81ad267a17a
        @GET("top-headlines")
        Observable<News> getTopHeadlines(@Query("country") String country, @Query("apiKey") String apiKey);

//      Get Headlines from that source (bbc or nbc or nyt etc)
//      https://newsapi.org/v2/top-headlines?sources=nbc-news&apiKey=98b0e7a42d3f4579a952c81ad267a17a
        @GET("top_headlines")
        Observable<Source> getSource(@Query("sources") String sources, @Query("apiKey") String apiKey);
}
