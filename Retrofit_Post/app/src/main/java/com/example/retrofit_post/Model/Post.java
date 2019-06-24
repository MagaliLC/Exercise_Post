package com.example.retrofit_post.Model;

public class Post {

    int id;
    int userId;
    String title;
    String body;
    User user;

    public Post(int userId, String title, String body) {
        this.userId = userId;
        this.title = title;
        this.body = body;
    }

    public int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }


    public String getTitle() {
        return title;
    }


    public String getBody() {
        return body;
    }

    public User getUser() {
        return user;
    }
}
