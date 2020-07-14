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
import androidx.core.app.BundleCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.fragment.NavHostFragment;

import com.bumptech.glide.Glide;
import com.example.newsapiapp.model.Article;
import com.example.newsapiapp.model.ArticleUrl;
import com.example.newsapiapp.model.Source;
import com.example.newsapiapp.viewmodel.ArticlesListViewmodel;
import com.example.newsapiapp.viewmodel.SharedViewModel;

public class SecondFragment extends Fragment {

    ArticlesListViewmodel articlesListViewmodel;

   @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
   ) {
       return inflater.inflate(R.layout.activity_details, container, false);
   }

   public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
       super.onViewCreated(view, savedInstanceState);
       articlesListViewmodel = new ViewModelProvider(requireActivity()).get(ArticlesListViewmodel.class);
//               view.findViewById(R.id.button_second).setOnClickListener((view) {
//           NavHostFragment.findNavController(SecondFragment.this)
//               .navigate(R.id.action_SecondFragment_to_FirstFragment);
//       });

   }

   public void getObservableSourceId(final String sources) {
       articlesListViewmodel.getSource(sources).observe(getViewLifecycleOwner(), new Observer<Source>() {
           @Override
           public void onChanged(Source source) {

           }
       });
   }
}