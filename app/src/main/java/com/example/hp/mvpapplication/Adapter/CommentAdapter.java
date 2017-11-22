package com.example.hp.mvpapplication.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.hp.mvpapplication.Model.Comment;
import com.example.hp.mvpapplication.R;

import java.util.List;

/**
 * Created by HP on 16/11/2017.
 */

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.MyViewHolder> {

    private List<Comment> mListComment;


    public CommentAdapter(List<Comment> list) {
        mListComment = list;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_comment, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.mBody.setText(mListComment.get(position).getBody());
        holder.mName.setText(mListComment.get(position).getName());
        holder.mEmail.setText(mListComment.get(position).getEmail());

    }

    @Override
    public int getItemCount() {
        return mListComment.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView mBody, mName, mEmail;

        public MyViewHolder(View view) {
            super(view);
            mBody = view.findViewById(R.id.body);
            mName = view.findViewById(R.id.name);
            mEmail = view.findViewById(R.id.email);
        }

    }


}
