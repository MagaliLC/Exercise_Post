package com.example.retrofit_post;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.retrofit_post.Model.Post;
import com.example.retrofit_post.Retrofit.MyService;
import com.example.retrofit_post.Retrofit.RetrofitClientInstance;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FormActivity extends AppCompatActivity {
    EditText et_user, et_title, et_body;
    Post post;
    Activity activity = this;
    MyAdapter adapter;
    ArrayList<Post> posts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        et_user = findViewById(R.id.et_user);
        et_title = findViewById(R.id.et_title);
        et_body = findViewById(R.id.et_body);

        adapter = new MyAdapter(activity, R.layout.row, posts);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Formulario");
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;

    }


        public void createPostInRetrofit(View view) {
            int userId= Integer.parseInt(et_user.getText().toString());
            String title = et_title.getText().toString();
            String body = et_body.getText().toString();

            if (checkField(userId,title,body)) {
                Post post = new Post (userId, title, body);
                saveUserWithRetrofit(post);
            }
        }

    private void saveUserWithRetrofit(Post post) {
        MyService service = RetrofitClientInstance.getRetrofitInstance().create(MyService.class);
        Call<Post> call = service.savePost(post);
        call.enqueue(new Callback<Post>() {

            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                int id = response.body().getId();
                Toast.makeText(activity, id, Toast.LENGTH_LONG);
                finish();
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                Toast.makeText(activity, R.string.error, Toast.LENGTH_LONG).show();
            }
        });

    }
    private boolean checkField(int userId, String title, String body) {
        boolean result = true;
        if ("".equals(et_user.getText().toString())) {
            result = false;
            et_user.setError("El campo no puede estar vacío");
        }
        if ("".equals(et_title.getText().toString())) {
            result = false;
            et_title.setError("El campo puede estar vacío");
        }
        if ("".equals(et_body.getText().toString())) {
            result = false;
            et_body.setError("El campo puede estar vacío");
        }
        return result;
    }


}
