package com.example.retrofit_post.Retrofit;

import com.example.retrofit_post.Model.Post;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface MyService {
    @GET("posts/")
    Call<List<Post>> postList();

    @GET("users/{post_id}")
    Call<Post> getPost(@Path("post_id") int post_id);

    @DELETE("users/{post_id}")
    Call<Void> deletePost(@Path("post_id") int post_id);

    @POST("posts/")
    Call<Post> savePost(@Body Post post);
}
