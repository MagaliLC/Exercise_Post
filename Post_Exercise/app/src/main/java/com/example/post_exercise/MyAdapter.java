package com.example.post_exercise;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.post_exercise.Model.Post;

import java.util.ArrayList;

public class MyAdapter extends ArrayAdapter<Post> {

    int layoutResourceId;
    Context context;
    ArrayList<Post> data;

    public MyAdapter(Context context, int layoutResourceId, ArrayList<Post> data) {
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
    }


    @Override

    public View getView(int position, View convertView, ViewGroup parent) {

        View row;
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        row = inflater.inflate(layoutResourceId, parent, false);
        TextView tv_post = (TextView) row.findViewById(R.id.tv_post);

        tv_post.setText(data.get(position).getTitle());
        return row;
    }

}