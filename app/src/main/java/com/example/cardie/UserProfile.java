package com.example.cardie;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

public class UserProfile extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        Intent intent = this.getIntent();
        TextView username= (TextView)findViewById(R.id.username_profile);
        SharedPreferences prefs = getSharedPreferences("username", MODE_PRIVATE);
        String name = prefs.getString("user", "No name defined");
        username.setText(name);

    }
}
