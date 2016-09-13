package com.example.android.hackingmvvm.viewmodel;

import android.util.Log;

import com.example.android.hackingmvvm.model.JsonPlaceHolderService;
import com.example.android.hackingmvvm.model.Post;

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

public class PostDetailsViewModel {
    private static final String TAG = PostDetailsViewModel.class.getSimpleName();
    void getPost(String postId){
        JsonPlaceHolderService.Factory.create().postFromId(postId).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<Post>() {
                    @Override
                    public void onCompleted() {
                        Log.i(TAG, "onCompleted: ");
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Post post) {
                        Log.i(TAG, "onNext: ");
                    }
                });
    }
}
