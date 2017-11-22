package com.example.hp.mvpapplication.Presenter;


import com.example.hp.mvpapplication.Model.Comment;
import com.example.hp.mvpapplication.Model.Post;
import com.example.hp.mvpapplication.Repository.RepositoryCommentData;
import com.example.hp.mvpapplication.Repository.RepositoryListener;
import com.example.hp.mvpapplication.Repository.RepositoryPostData;
import com.example.hp.mvpapplication.Tools.PostContract;

import java.util.ArrayList;

/**
 * Created by HP on 16/11/2017.
 */

public class PostPresenter implements PostContract.PostPresenter, RepositoryListener {

    private PostContract.PostView mPostView;
    private RepositoryPostData mRepositoryPostData;
    private RepositoryCommentData mRepositoryCommentData;


    public PostPresenter(PostContract.PostView view) {
        mPostView = view;
        mRepositoryPostData = new RepositoryPostData(this);
        mRepositoryCommentData = new RepositoryCommentData(this);
    }


    @Override
    public void showData() {
        if (mRepositoryPostData != null) {
            mPostView.showProgress();
            mRepositoryPostData.getPostsFromServer();
        }
    }

    @Override
    public void onDestroy() {
        if (mRepositoryPostData != null) {
            mRepositoryPostData.cancel();
            mRepositoryPostData = null;
        }
        mPostView = null;
    }

    @Override
    public void onPostResponse(Object o) {
        mRepositoryPostData = null;
        mPostView.hideProgress();
        if (o instanceof Throwable) {
            mPostView.showErrorMsg(o.toString());
        } else {
            mPostView.setItem((ArrayList<Post>) o);
        }
    }

    @Override
    public void onCommentResponse(Object o) {
        if (o instanceof Throwable) {
            mPostView.showErrorMsg(o.toString());
        } else {
            mPostView.showComment((ArrayList<Comment>) o);
        }
    }

    @Override
    public void fetchComment(int id) {
        mRepositoryCommentData.getCommentsFromServer(id);
    }
}
