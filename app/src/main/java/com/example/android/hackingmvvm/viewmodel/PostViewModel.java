package com.example.android.hackingmvvm.viewmodel;

import android.util.Log;

import com.example.android.hackingmvvm.model.JsonPlaceHolderService;
import com.example.android.hackingmvvm.model.Post;

import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Description Please
 *
 * @author pranit
 * @version 1.0
 * @since 13/9/16
 */

public class PostViewModel {
    private static final String TAG = PostViewModel.class.getSimpleName();

    public List<Post> mPostList;
    private DataListener dataListener;

    public PostViewModel(DataListener dataListener){
        this.dataListener=dataListener;
        loadPosts();
    }

    void loadPosts(){
        JsonPlaceHolderService.Factory.create().getAllPosts().observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<List<Post>>() {
                    @Override
                    public void onCompleted() {
                        Log.i(TAG, "onCompleted: ");
                        if(null !=dataListener)
                            dataListener.onPostAdded(mPostList);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i(TAG, "onError: ");
                    }

                    @Override
                    public void onNext(List<Post> posts) {
                        Log.i(TAG, "onNext: ");
                        PostViewModel.this.mPostList=posts;
                    }
                });
    }

    public interface DataListener {
        void onPostAdded(List<Post> posts);
    }
}
