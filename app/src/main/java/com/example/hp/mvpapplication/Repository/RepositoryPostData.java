package com.example.hp.mvpapplication.Repository;


import android.Manifest;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresPermission;

import com.example.hp.mvpapplication.Model.Post;
import com.example.hp.mvpapplication.Model.PostApi;
import com.example.hp.mvpapplication.Tools.Constants;
import com.example.hp.mvpapplication.Tools.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by HP on 16/11/2017.
 */

public class RepositoryPostData implements Callback<List<Post>> {

    private Retrofit mRetrofit;
    private RepositoryListener mListener;
    private Call<List<Post>> mCall;


    public RepositoryPostData(@NonNull RepositoryListener listener) {
        mListener = listener;
    }

    @RequiresPermission(Manifest.permission.INTERNET)
    public void getPostsFromServer() {
        mRetrofit = RetrofitClient.getClient(Constants.BASE_URL);
        PostApi api = mRetrofit.create(PostApi.class);
        mCall = api.getPosts();
        mCall.enqueue(this);
    }

    @Override
    public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
        mListener.onPostResponse(response.body());
    }

    @Override
    public void onFailure(Call<List<Post>> call, Throwable t) {
        mListener.onPostResponse(t);
    }

    public void cancel() {
        mListener = null;
        if (mCall != null && !mCall.isCanceled())
            mCall.cancel();
    }

}
