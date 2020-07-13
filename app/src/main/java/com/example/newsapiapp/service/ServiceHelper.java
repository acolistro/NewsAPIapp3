package com.example.newsapiapp.service;

import android.util.Log;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.newsapiapp.model.Constants.BASE_URL;

public class ServiceHelper {
    public static Retrofit retrofit=null;
    //okhttp


    public static HttpLoggingInterceptor loggerInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
        @Override public void log(String message) {
            Log.i("Articles",""+message );
        }
    }).setLevel(HttpLoggingInterceptor.Level.BODY);


    public static OkHttpClient client = new OkHttpClient.Builder()
            .addInterceptor(loggerInterceptor)
            .build();



    public static IApiHelper getRestAPIHelper(){
        if(retrofit==null){
            retrofit= new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(client)
                    .build();
        }
        return retrofit.create(IApiHelper.class);
    }
}
