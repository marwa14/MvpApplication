package com.example.hp.mvpapplication.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.hp.mvpapplication.Model.Post;
import com.example.hp.mvpapplication.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HP on 16/11/2017.
 */

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder> {

    private List<Post> mListPost;
    private OnItemClick mListener;


    public PostAdapter(OnItemClick listener) {
        mListPost = new ArrayList<>();
        mListener = listener;
    }


    @Override
    public PostAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_post, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(PostAdapter.ViewHolder holder, final int position) {
        holder.mBody.setText(mListPost.get(position).getBody());
        holder.mTitle.setText(mListPost.get(position).getTitle());
        holder.onBind(position, mListener);


    }

    @Override
    public int getItemCount() {
        return mListPost.size();
    }

    public void setResult(List<Post> list) {
        mListPost.clear();
        mListPost.addAll(list);
        notifyDataSetChanged();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView mBody, mTitle;

        public ViewHolder(View view) {
            super(view);
            mBody = view.findViewById(R.id.body);
            mTitle = view.findViewById(R.id.title);
        }

        public void onBind(final int position, final OnItemClick lisener) {
            mBody.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    lisener.onClick(mListPost.get(position).getId());
                }
            });
        }
    }
}
