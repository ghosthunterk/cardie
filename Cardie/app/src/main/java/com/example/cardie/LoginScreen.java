package com.example.cardie;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cardie.Models.Card;
import com.example.cardie.Models.User;
import com.example.cardie.RetrofitClient.API;
import com.example.cardie.RetrofitClient.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

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
                    final Intent intent = new Intent(LoginScreen.this,setlist_main.class);
                    final String str = (String) username.getText().toString();

                    //check if username's existed

                    Retrofit retrofit = RetrofitClient.getInstance();
                    final API api = retrofit.create(API.class);
                    Call<User> call = api.getUserProfile(str);
                    call.enqueue(new Callback<User>() {
                                     @Override
                                     public void onResponse(Call<User> call, Response<User> response) {
                                         User newUser = response.body();
                                         if (newUser.getUsername()==str)
                                         {
                                             Toast.makeText(LoginScreen.this, "Username has existed", Toast.LENGTH_LONG).show();
                                             username.setText("");
                                         }
                                         else
                                         {
                                             SharedPreferences.Editor editor = getSharedPreferences("UserProfile", MODE_PRIVATE).edit();
                                             editor.putString("username",str);
                                             editor.apply();
                                             addUserToDb(str);
                                             startActivity(intent);

                                         }
                                     }

                                     @Override
                                     public void onFailure(Call<User> call, Throwable t) {
                                         Toast.makeText(LoginScreen.this,t.getMessage(),Toast.LENGTH_LONG).show();
                                     }
                                 }
                    );
                    return true;
                }
                else{
                    return false;
                }
            }
        });
    }
    private void addUserToDb(String username)
    {
        Retrofit retrofit = RetrofitClient.getInstance();
        final API api = retrofit.create(API.class);
        Call<String> call = api.addUser(username);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Toast.makeText(LoginScreen.this, "Account created Successfully", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(LoginScreen.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}

