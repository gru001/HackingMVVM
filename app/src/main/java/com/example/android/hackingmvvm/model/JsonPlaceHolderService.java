package com.example.android.hackingmvvm.model;

import java.util.List;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Description Please
 *
 * @author pranit
 * @version 1.0
 * @since 11/9/16
 */

public interface JsonPlaceHolderService {
    @GET("posts/{postid}")
    Observable<Post> postFromId(@Path("postid") String postId);

    @GET("posts")
    Observable<List<Post>> getAllPosts();

    class Factory {
        public static JsonPlaceHolderService create() {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://jsonplaceholder.typicode.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();
            return retrofit.create(JsonPlaceHolderService.class);
        }
    }
}
