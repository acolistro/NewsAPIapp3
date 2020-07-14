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
import com.example.newsapiapp.model.Source;
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
    static IApiHelper iApiHelper;
    static CompositeDisposable compositeDisposable;


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

            return headlineLiveData;
        }


        public MutableLiveData<Source> getSource(String sources) {
            final MutableLiveData<Source> sourceMutableLiveData = new MutableLiveData<>();
            compositeDisposable.add(
            iApiHelper.getSource(sources, Constants.API_KEY)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Consumer<Source>() {
                        @Override
                        public void accept(Source source) throws Exception {
                            isLoading.setValue(true);
                            sourceMutableLiveData.setValue(source);

//                            Log.i("Articles2",""+source.getId().getArticles());
                        }
                    }, new Consumer<Throwable>() {
                        @Override
                        public void accept(Throwable e) throws Exception {
                            //   ToastUtils.showError(NetworkChecker.getErrorMessage(e), MyApplication.getAppContext());
                            isLoading.setValue(false);
                            Log.i("Articles2",""+e.getLocalizedMessage());

                        }
                    }));
            return sourceMutableLiveData;
        }

    }

