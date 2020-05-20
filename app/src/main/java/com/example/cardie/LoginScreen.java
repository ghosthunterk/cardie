package com.example.cardie;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class LoginScreen extends AppCompatActivity {
    EditText username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_screen);
        username = (EditText) findViewById(R.id.username);


        username.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if ( (actionId == EditorInfo.IME_ACTION_DONE) || ((event.getKeyCode() == KeyEvent.KEYCODE_ENTER) && (event.getAction() == KeyEvent.ACTION_DOWN ))){
                    Intent intent = new Intent(LoginScreen.this,setlist_main.class);
                    final String str = (String) username.getText().toString();
                    SharedPreferences.Editor editor = getSharedPreferences("username", MODE_PRIVATE).edit();
                    editor.putString("user",str);
                    editor.apply();
                    startActivity(intent);
                    return true;
                }
                else{
                    return false;
                }
            }
        });
    }


}

