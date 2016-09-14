package com.example.android.hackingmvvm.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.android.hackingmvvm.R;
import com.example.android.hackingmvvm.viewmodel.PostViewModel;

public class PostActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.post_activity);
        PostViewModel postViewModel = new PostViewModel();
        postViewModel.loadPosts();
    }
}
