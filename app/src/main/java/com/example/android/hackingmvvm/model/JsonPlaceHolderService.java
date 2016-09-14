package com.example.android.hackingmvvm.model;

import com.example.android.hackingmvvm.utils.HttpLoggingInterceptor;

import java.io.IOException;
import java.util.List;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
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
            OkHttpClient.Builder okHttpBuilder = new OkHttpClient.Builder();
            okHttpBuilder.addInterceptor(getHttpLoggingInterceptor());
            okHttpBuilder.addInterceptor(getHeaderInterceptor());
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://jsonplaceholder.typicode.com/")
                    .client(okHttpBuilder.build())
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();
            return retrofit.create(JsonPlaceHolderService.class);
        }

        private static Interceptor getHeaderInterceptor() {
            return new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Request orgRequest = chain.request();
                    final Request newRequest = orgRequest.newBuilder()
                        /*.addHeader("token", "123456")*/
                            .build();
                    return chain.proceed(newRequest);
                }
            };
        }

        private static HttpLoggingInterceptor getHttpLoggingInterceptor() {
            HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            return httpLoggingInterceptor;
        }
    }
}
