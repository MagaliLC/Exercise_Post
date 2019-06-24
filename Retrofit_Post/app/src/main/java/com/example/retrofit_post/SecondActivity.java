package com.example.retrofit_post;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.retrofit_post.Model.Post;
import com.example.retrofit_post.Retrofit.MyService;
import com.example.retrofit_post.Retrofit.RetrofitClientInstance;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SecondActivity extends AppCompatActivity {
    int postId;
    TextView tv_name, tv_email, tv_address;
    Activity activity = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        tv_name = findViewById(R.id.tv_name);
        tv_email = findViewById(R.id.tv_email);
        tv_address = findViewById(R.id.tv_address);

        postId = getIntent().getIntExtra("postId", 0);

        getPostFromRetrofit();
    }

    private void getPostFromRetrofit() {
        MyService service = RetrofitClientInstance.getRetrofitInstance().create(MyService.class);

        Call<Post> call = service.getPost(postId);

        call.enqueue(new Callback<Post>() {

            @Override

            public void onResponse(Call<Post> call, Response<Post> response) {

                loadPost(response.body());
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                Toast.makeText(activity, "Error", Toast.LENGTH_LONG).show();
            }

        });
    }

    private void loadPost(Post p) {
       //DUDA

    }
}
