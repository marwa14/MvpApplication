package com.example.hp.mvpapplication.View;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.example.hp.mvpapplication.Adapter.OnItemClick;
import com.example.hp.mvpapplication.Adapter.PostAdapter;
import com.example.hp.mvpapplication.Model.Comment;
import com.example.hp.mvpapplication.Model.Post;
import com.example.hp.mvpapplication.Presenter.PostPresenter;
import com.example.hp.mvpapplication.R;
import com.example.hp.mvpapplication.Tools.Constants;
import com.example.hp.mvpapplication.Tools.PostContract;
import com.example.hp.mvpapplication.Tools.Utils;

import java.io.Serializable;
import java.util.List;

public class PostActivity extends AppCompatActivity implements PostContract.PostView, OnItemClick {

    private static final String TAG = PostActivity.class.getName();

    private AlertDialog mDialog;

    private PostPresenter mPostPresenter;

    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar actionBar = this.getSupportActionBar();
        actionBar.setTitle(R.string.post);
        mRecyclerView = findViewById(R.id.list);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(new PostAdapter(this));
        initializeDialog();
        mPostPresenter = new PostPresenter(this);
        checkConnection();
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    public void showProgress() {
        mDialog.show();
    }

    @Override
    public void hideProgress() {
        mDialog.dismiss();
    }


    public void checkConnection() {
        if (Utils.CheckNetworkConnection(this)) {
            mPostPresenter.showData();
        } else {
            Utils.showAlertDialog(this);
        }
    }

    @Override
    public void setItem(List<Post> list) {
        PostAdapter adapter = (PostAdapter) mRecyclerView.getAdapter();
        adapter.setResult(list);
    }

    @Override
    public void showErrorMsg(String msg) {
        Log.i(TAG, msg);

    }

    @Override
    public void showComment(List<Comment> list) {
        Intent i = new Intent(this, CommentActivity.class);
        i.putExtra(Constants.LIST_COMMENT, (Serializable) list);
        startActivity(i);
    }

    public void initializeDialog() {
        View view = getLayoutInflater().inflate(R.layout.progress, null);
        mDialog = new AlertDialog.Builder(this).setView(view).create();
        mDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
    }

    @Override
    protected void onDestroy() {
        mPostPresenter.onDestroy();
        super.onDestroy();
    }

    @Override
    public void onClick(int id) {
        if (Utils.CheckNetworkConnection(this))
            mPostPresenter.fetchComment(id);
        else Utils.showAlertDialog(this);
    }
}
