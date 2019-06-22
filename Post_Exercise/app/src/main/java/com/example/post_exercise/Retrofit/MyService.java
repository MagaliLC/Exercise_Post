package com.example.post_exercise.Retrofit;

import com.example.post_exercise.Model.Post;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MyService {
    @GET("posts/")
    Call<List<Post>> postList();
}
