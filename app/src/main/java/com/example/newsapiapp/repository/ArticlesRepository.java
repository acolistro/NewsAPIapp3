package com.example.newsapiapp.repository;

import androidx.lifecycle.MutableLiveData;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

import android.app.Application;
import android.app.admin.DevicePolicyManager;
import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.newsapiapp.model.Constants;
import com.example.newsapiapp.model.Article;
import com.example.newsapiapp.model.Headline;
import com.example.newsapiapp.model.News;
import com.example.newsapiapp.service.IApiHelper;
import com.example.newsapiapp.service.ServiceHelper;
import com.example.newsapiapp.utils.MyApplication;
import com.example.newsapiapp.utils.NetworkChecker;
import com.example.newsapiapp.utils.ToastUtils;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class ArticlesRepository {

    public static ArticlesRepository articlesRepository;


        public synchronized static ArticlesRepository getInstance() {
            if (articlesRepository == null) {
                articlesRepository = new ArticlesRepository();
            }
            return articlesRepository;
        }


        public static MutableLiveData<Boolean> isLoading=new MutableLiveData<>();

        public MutableLiveData<News> getArticlesList() {
            IApiHelper iRestHelper = ServiceHelper.getRestAPIHelper();
            final MutableLiveData<News> headlineLiveData = new MutableLiveData<>();
            CompositeDisposable mDisposable = new CompositeDisposable();

            mDisposable.add(iRestHelper.getTopHeadlines("us", Constants.API_KEY)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Consumer<News>() {
                        @Override
                        public void accept(News headline) throws Exception {
                            isLoading.setValue(true);
                            headlineLiveData.setValue(headline);

                                Log.i("Articles2",""+headline.getArticles().get(0).getAuthor());
                        }
                    }, new Consumer<Throwable>() {
                        @Override
                        public void accept(Throwable e) throws Exception {
                         //   ToastUtils.showError(NetworkChecker.getErrorMessage(e), MyApplication.getAppContext());
                            isLoading.setValue(false);
                            Log.i("Articles2",""+e.getLocalizedMessage());

                        }
                    }));

//                .subscribe(new Observer<MoviesResponse>() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
//
//                    }
//
//                    @Override
//                    public void onNext(MoviesResponse moviesResponse) {
//                        moviesResponseLiveData.setValue(moviesResponse);
//                        Log.i("Movies","Value is "+moviesResponse.getResults().get(1).getOriginalTitle());
//
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        ToastUtils.showError(NetworkChecker.getErrorMessage(e), MyApplication.getAppContext());
//
//                    }
//
//                    @Override
//                    public void onComplete() {
//
//                    }
//                });
            return headlineLiveData;
        }

//        //TODO need getArticle method to connect with api helper method
//        public MutableLiveData<Article> getArticle(String url){
//            final MutableLiveData<Article> articleMutableLiveData = new MutableLiveData<>();
//            IApiHelper iRestHelper = ServiceHelper.getRestAPIHelper();
//            iRestHelper.getArticle(url)
//                    .subscribeOn(Schedulers.io())
//                    .observeOn(AndroidSchedulers.mainThread())
//                    .subscribe(new Observer<Article>() {
//                        @Override
//                        public void onSubscribe(Disposable d) {
//
//                        }
//
//                        @Override
//                        public void onNext(Article article) {
//                            articleMutableLiveData.setValue(article);
//                        }
//
//                        @Override
//                        public void onError(Throwable e) {
//
//                        }
//
//                        @Override
//                        public void onComplete() {
//
//                        }
//                    });
//            return articleMutableLiveData;
//        }

    }

