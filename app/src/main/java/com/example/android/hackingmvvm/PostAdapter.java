package com.example.android.hackingmvvm;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.hackingmvvm.model.Post;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.example.android.hackingmvvm.BR.post;

/**
 * Created by Tejshree on 13-09-2016.
 */

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.BindingHolder> {

    private List<Post> mPostList;

    public PostAdapter(List<Post> postList){
        mPostList=new ArrayList<>();
        mPostList=postList;
    }

    public PostAdapter() {
        this.mPostList = Collections.emptyList();
    }

    public void setPosts(List<Post> postList) {
        this.mPostList = postList;
    }


    @Override
    public BindingHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_post, parent, false);
        BindingHolder bindingHolder = new BindingHolder(v);
        return bindingHolder;
    }

    @Override
    public void onBindViewHolder(BindingHolder holder, int position) {
        Post mPost=mPostList.get(position);
        holder.getViewDataBinding().setVariable(post,mPost);
        holder.getViewDataBinding().executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return mPostList.size();
    }

    public class BindingHolder extends RecyclerView.ViewHolder{
        private ViewDataBinding viewDataBinding;

        public BindingHolder(View itemView) {
            super(itemView);
            viewDataBinding= DataBindingUtil.bind(itemView);
        }

        public ViewDataBinding getViewDataBinding() {
            return viewDataBinding;
        }
    }
}
