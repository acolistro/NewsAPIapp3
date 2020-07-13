package com.example.newsapiapp;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newsapiapp.adapter.ArticleAdapter;
import com.example.newsapiapp.model.ArticleUrl;
import com.example.newsapiapp.model.Headline;
import com.example.newsapiapp.model.News;
import com.example.newsapiapp.utils.NetworkChecker;
import com.example.newsapiapp.viewmodel.ArticlesListViewmodel;
import com.example.newsapiapp.viewmodel.SharedViewModel;
import com.google.android.material.snackbar.Snackbar;

public class FirstFragment extends Fragment  {
    ArticlesListViewmodel articlesListViewmodel;
    SharedViewModel model;
    public static ProgressDialog progressDialog;
    RecyclerView recyclerView;
    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setTitle("Loading");
        progressDialog.setTitle("Please wait...");
        Toast.makeText(getActivity(), "Message error", Toast.LENGTH_LONG).show();
        progressDialog.setCancelable(false);
//        progressDialog.show();
        recyclerView= getActivity().findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),2));
        //sharedViewModel= ViewModelProviders.of(this).get(SharedViewModel.class);

        model = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
        articlesListViewmodel = new ViewModelProvider(requireActivity()).get(ArticlesListViewmodel.class);

        //articlesListViewmodel= ViewModelProviders.of(this).get(ArticlesListViewmodel.class);
        articleListObservables();

//        if(NetworkChecker.isConnected(getActivity())) {
//            articleListObservables();
//        }else{
//            // Get Data from
//            Snackbar snackBar = Snackbar .make(view, "An Error Occurred!", Snackbar.LENGTH_LONG) .setAction("RETRY", new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    articleListObservables();
//                }
//            });
//            snackBar.setActionTextColor(Color.BLUE);
//            View snackBarView = snackBar.getView();
//            TextView textView = snackBarView.findViewById(com.google.android.material.R.id.snackbar_text);
//            textView.setTextColor(Color.RED);
//            snackBar.show();
//        }

        view.findViewById(R.id.button_first).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(FirstFragment.this)
                        .navigate(R.id.action_FirstFragment_to_SecondFragment);
//                 observablesDetails();
            }
        });


    }
    public void articleListObservables(){
        getProgressDialog();

        articlesListViewmodel.getHeadline().observe(getViewLifecycleOwner(),     new Observer<News>() {
            @Override
            public void onChanged(News headline) {

                Log.i("Articles3","Value is "+headline.getArticles().get(0).getDescription());
                Toast.makeText(getActivity(), "" + headline.getArticles().get(1).getUrl(), Toast.LENGTH_SHORT).show();
                recyclerView.setAdapter(new ArticleAdapter(headline.getArticles(), getActivity(), FirstFragment.this));
                if(progressDialog.isShowing())
                    progressDialog.dismiss();

            }
        });

    }

    public void getProgressDialog(){
        articlesListViewmodel.getIsLoading().observe(getActivity(), new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable Boolean aBoolean) {
                if (aBoolean) {
                    progressDialog.show();
                    Log.i("Progree",""+aBoolean);
                } else {
                    progressDialog.dismiss();
                    Log.i("Progredsde",""+aBoolean);
                }
            }
        });
    }
    public void gotoArticle(final String mPos){
        ArticleUrl articleUrl= new ArticleUrl();
        articleUrl.setArticleUrl(mPos);
        model.select(articleUrl);
        NavHostFragment.findNavController(FirstFragment.this)
                .navigate(R.id.action_FirstFragment_to_SecondFragment);
    }




}
