package com.example.cardie.Models;

import com.google.gson.annotations.SerializedName;

public class User {
    @SerializedName("_id")
    private String UserId;
    @SerializedName("username")
    private String Username;

    public User(String userId, String username) {
        UserId = userId;
        Username = username;
    }

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }
}
