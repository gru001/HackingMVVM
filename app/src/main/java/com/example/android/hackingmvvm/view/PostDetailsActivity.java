package com.example.android.hackingmvvm.view;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

import com.example.android.hackingmvvm.R;
import com.example.android.hackingmvvm.databinding.PostdetailsActivityBinding;
import com.example.android.hackingmvvm.viewmodel.PostDetailsViewModel;

public class PostDetailsActivity extends AppCompatActivity {
    public static final String EXTRA_POST_ID = "EXTRA_POST_ID";
    private PostdetailsActivityBinding binding;
    private PostDetailsViewModel mDetailsViewModel;

    public static Intent newIntent(Context context, String postId){
        Intent intent = new Intent(context, PostDetailsActivity.class);
        intent.putExtra(EXTRA_POST_ID, postId);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.postdetails_activity);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        String postId = String.valueOf(getIntent().getIntExtra(EXTRA_POST_ID,0));
        mDetailsViewModel = new PostDetailsViewModel(postId);
        binding.setViewModel(mDetailsViewModel);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mDetailsViewModel.onDestroy();
    }
}
