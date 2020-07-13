package com.example.newsapiapp;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.fragment.NavHostFragment;

import com.bumptech.glide.Glide;
import com.example.newsapiapp.model.Article;
import com.example.newsapiapp.model.ArticleUrl;
import com.example.newsapiapp.viewmodel.ArticlesListViewmodel;
import com.example.newsapiapp.viewmodel.SharedViewModel;

public class SecondFragment extends Fragment {

    ArticlesListViewmodel articlesListViewmodel;
    SharedViewModel sharedViewModel;
    ImageView articleImage;
    TextView tvTitle,description, author, publishedAt;
    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        //int movieID=getArguments().getInt("ID");
        return inflater.inflate(R.layout.activity_details, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

//        image= getActivity().findViewById(R.id.image_item_article);
        articleImage= getActivity().findViewById(R.id.articleImage);
        tvTitle= getActivity().findViewById(R.id.tvTitle);
        description= getActivity().findViewById(R.id.description);
        author= getActivity().findViewById(R.id.author);
        publishedAt= getActivity().findViewById(R.id.publishedAt);

        sharedViewModel= ViewModelProviders.of(this).get(SharedViewModel.class);
        SharedViewModel model = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);

        model.getSelected().observe(getViewLifecycleOwner(), new Observer<ArticleUrl>() {
            @Override
            public void onChanged(ArticleUrl articleUrl) {
                Log.i("Values",""+articleUrl.getArticleUrl());
             //   observablesDetails(articleUrl.getArticleUrl());
            }
        });
    }
    public void observablesDetails(final String url){
        articlesListViewmodel= ViewModelProviders.of(this).get(ArticlesListViewmodel.class);
//        articlesListViewmodel.getArticle(url).observe(getViewLifecycleOwner(), new Observer<Article>() {
//            @Override
//            public void onChanged(Article article) {
//
//
//                Toast.makeText(getActivity(), ""+article.getAuthor(), Toast.LENGTH_SHORT).show();
//
//
//                Glide.with(getActivity()).load(article.getUrlToImage());
//                Glide.with(getActivity()).load(article.getUrlToImage());
//                tvTitle.setText(article.getTitle());
//                description.setText(article.getDescription());
//                author.setText(article.getAuthor());
//                publishedAt.setText(article.getPublishedAt());
//
//            }
//        });
    }
}