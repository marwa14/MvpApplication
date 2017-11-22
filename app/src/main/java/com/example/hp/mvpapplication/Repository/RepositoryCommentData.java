package com.example.hp.mvpapplication.Repository;

import android.Manifest;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresPermission;

import com.example.hp.mvpapplication.Model.Comment;
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

public class RepositoryCommentData implements Callback<List<Comment>> {

    private Retrofit mRetrofit;
    private RepositoryListener mListener;
    private Call<List<Comment>> mCall;


    public RepositoryCommentData(@NonNull RepositoryListener listener) {
        mListener = listener;
    }

    @RequiresPermission(Manifest.permission.INTERNET)
    public void getCommentsFromServer(int id) {
        mRetrofit = RetrofitClient.getClient(Constants.BASE_URL);
        PostApi api = mRetrofit.create(PostApi.class);
        mCall = api.getComments(id);
        mCall.enqueue(this);
    }

    @Override
    public void onResponse(Call<List<Comment>> call, Response<List<Comment>> response) {
        mListener.onCommentResponse(response.body());
    }

    @Override
    public void onFailure(Call<List<Comment>> call, Throwable t) {
        mListener.onCommentResponse(t);
    }

}
