package com.example.post_exercise;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.post_exercise.Model.Post;
import com.example.post_exercise.Retrofit.MyService;
import com.example.post_exercise.Retrofit.RetrofitClientInstance;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    Activity activity = this;
    ArrayList<Post> posts;
    MyAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        posts = new ArrayList<Post>();
        listView = findViewById(R.id.lv_post);
        adapter = new MyAdapter(activity, R.layout.row, posts);
        listView.setAdapter(adapter);

    }

    @Override
    protected void onResume() {
        super.onResume();
        getPostFromRetrofit();
    }

    public void getPostFromRetrofit() {

        MyService service = RetrofitClientInstance.getRetrofitInstance().create(MyService.class);

        Call<List<Post>> call = service.postList();
        call.enqueue(new Callback<List<Post>>() {

            @Override

            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {

                posts.addAll(response.body());
                adapter.notifyDataSetChanged();

            }

            @Override

            public void onFailure(Call<List<Post>> call, Throwable t) {
                Toast.makeText(activity, R.string.error, Toast.LENGTH_LONG).show();

            }


        });
    }

}


