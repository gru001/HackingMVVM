package com.example.android.hackingmvvm.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;

import com.example.android.hackingmvvm.PostAdapter;
import com.example.android.hackingmvvm.R;
import com.example.android.hackingmvvm.databinding.PostActivityBinding;
import com.example.android.hackingmvvm.model.Post;
import com.example.android.hackingmvvm.viewmodel.PostViewModel;

import java.util.List;

public class PostActivity extends AppCompatActivity implements PostViewModel.DataListener{

    private PostAdapter mPostAdapter;
    private PostViewModel mPostViewModel;
    private PostActivityBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.post_activity);
        mPostViewModel=new PostViewModel(this);
        mPostAdapter=new PostAdapter();
        binding.setViewModel(mPostViewModel);
        binding.rvPost.setAdapter(mPostAdapter);
        binding.rvPost.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void onPostAdded(List<Post> posts) {
        mPostAdapter= (PostAdapter) binding.rvPost.getAdapter();
        mPostAdapter.setPosts(posts);
        mPostAdapter.notifyDataSetChanged();
    }
}
