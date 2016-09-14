package com.example.android.hackingmvvm;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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
    private ItemClickListener itemClickListener;

    public PostAdapter(List<Post> postList,ItemClickListener itemClickListener){
        mPostList=new ArrayList<>();
        mPostList=postList;
        this.itemClickListener=itemClickListener;
    }

    public PostAdapter(ItemClickListener itemClickListener) {
        this.mPostList = Collections.emptyList();
        this.itemClickListener=itemClickListener;
    }

    public void setPosts(List<Post> postList) {
        this.mPostList = postList;
    }

    public List<Post> getmPostList() {
        return mPostList;
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

    public class BindingHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private ViewDataBinding viewDataBinding;

        public BindingHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            viewDataBinding= DataBindingUtil.bind(itemView);
        }

        public ViewDataBinding getViewDataBinding() {
            return viewDataBinding;
        }

        @Override
        public void onClick(View view) {
            itemClickListener.onItemClick(getAdapterPosition());
        }
    }

    public interface ItemClickListener{
        public void onItemClick(int pos);

    }
}
