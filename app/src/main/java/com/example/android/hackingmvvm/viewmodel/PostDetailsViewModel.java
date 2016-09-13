package com.example.android.hackingmvvm.viewmodel;

import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.util.Log;

import com.example.android.hackingmvvm.model.JsonPlaceHolderService;
import com.example.android.hackingmvvm.model.Post;

import rx.Subscriber;
import rx.Subscription;
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

    public ObservableField<String> title;
    public ObservableField<String> body;
    public ObservableBoolean isDataDisplayed;

    private Subscription subscription;

    private Post post;

    public PostDetailsViewModel(String postId){
        title = new ObservableField<>();
        body = new ObservableField<>();
        isDataDisplayed = new ObservableBoolean();
        getPost(postId);
    }

    public void onDestroy(){
        if (subscription != null && !subscription.isUnsubscribed()) subscription.unsubscribe();
    }

    private void getPost(String postId){
        subscription = JsonPlaceHolderService.Factory.create().postFromId(postId).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<Post>() {
                    @Override
                    public void onCompleted() {
                        Log.i(TAG, "onCompleted: ");
                        isDataDisplayed.set(true);
                        if(post != null){
                            title.set(post.getTitle());
                            body.set(post.getBody());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "onError: ", e);
                        isDataDisplayed.set(true);
                    }

                    @Override
                    public void onNext(Post post) {
                        Log.i(TAG, "onNext: ");
                        PostDetailsViewModel.this.post = post;
                    }
                });
    }
}
