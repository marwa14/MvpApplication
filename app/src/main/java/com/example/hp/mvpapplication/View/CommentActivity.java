package com.example.hp.mvpapplication.View;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.hp.mvpapplication.Adapter.CommentAdapter;
import com.example.hp.mvpapplication.R;
import com.example.hp.mvpapplication.Tools.Constants;

import java.util.List;

public class CommentActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);

        ActionBar actionBar = this.getSupportActionBar();
        actionBar.setTitle(R.string.comment);
        RecyclerView recyclerView = findViewById(R.id.list);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        CommentAdapter commentAdapter = new CommentAdapter((List) getIntent().getSerializableExtra(Constants.LIST_COMMENT));
        recyclerView.setAdapter(commentAdapter);

    }


}
