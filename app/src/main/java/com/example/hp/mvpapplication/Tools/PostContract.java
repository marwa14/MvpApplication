package com.example.hp.mvpapplication.Tools;

import com.example.hp.mvpapplication.Model.Comment;
import com.example.hp.mvpapplication.Model.Post;

import java.util.List;

/**
 * Created by HP on 16/11/2017.
 */

public interface PostContract {

    public interface PostView {

        public void showProgress();

        public void hideProgress();

        public void setItem(List<Post> list);

        public void showErrorMsg(String msg);

        public void showComment(List<Comment> list);
    }

    public interface PostPresenter {

        public void showData();

        public void onDestroy();

        public void fetchComment(int id);
    }
}
