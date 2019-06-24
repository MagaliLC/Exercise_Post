package com.example.retrofit_post;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.retrofit_post.Model.Post;
import com.example.retrofit_post.Retrofit.MyService;
import com.example.retrofit_post.Retrofit.RetrofitClientInstance;

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
    int postId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        posts = new ArrayList<Post>();
        listView = findViewById(R.id.lv_post);
        adapter = new MyAdapter(activity, R.layout.row, posts);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override

            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                int postId = posts.get(position).getId();
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                intent.putExtra("postId", postId);
                startActivity(intent);
            }

        });
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                openDialog(position);
                return true;
            }
        });
    }


    @Override
    protected void onResume() {
        super.onResume();
        getPostFromRetrofit();
    }

    public void openDialog(final int position) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                this);
        alertDialogBuilder.setTitle(getString(R.string.app_name));
        alertDialogBuilder.setMessage(R.string.delete)
                .setCancelable(false)
                .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int id) {
                        deleteUser(position);
                        adapter.notifyDataSetChanged();
                    }
                })
                .setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    private void deleteUser(final int position) {
        MyService service = RetrofitClientInstance.getRetrofitInstance().create(MyService.class);

        Call<Void> call = service.deletePost(posts.get(position).getId());

        call.enqueue(new Callback<Void>() {

            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                posts.remove(position);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }

        });
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
