package com.example.newsapiapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.newsapiapp.FirstFragment;
import com.example.newsapiapp.R;
import com.example.newsapiapp.model.Article;

import java.util.List;



public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.ArticleViewHolder> {
        private List<Article> articleList ;
        private Context mContext;
        FirstFragment firstFragment;

        public ArticleAdapter(List<Article> articleList, Context context, FirstFragment firstFragment) {
            this.articleList = articleList;
            this.mContext = context;
            this.firstFragment=firstFragment;
        }

        @Override
        public ArticleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.row_article, parent, false);

            return new ArticleViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(ArticleViewHolder holder, int position) {
            final Article article = articleList.get(position);
            //holder.count.setText(album.gt + " songs");
            final String mPos= articleList.get(position).getUrl();
            // loading album cover using Glide library
            Glide.with(mContext).load(""+article.getUrlToImage()).into(holder.imageView);
            holder.imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                   // firstFragment.gotoNext(mPos);
                }
            });

//        Intent intent = new Intent(context, DetailseActivity.class);
//        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        intent.putExtra("Movie", movie);
//        context.startActivity(intent);
        }

        @Override
        public int getItemCount() {
            return articleList.size();
        }

        public static class ArticleViewHolder extends RecyclerView.ViewHolder {
            ImageView imageView;
            public ArticleViewHolder(@NonNull View itemView) {
                super(itemView);
                imageView= itemView.findViewById(R.id.image_item_article);

            }
        }
}

