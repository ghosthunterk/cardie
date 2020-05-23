package com.example.cardie;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.example.cardie.Models.User;
import com.example.cardie.RetrofitClient.API;
import com.example.cardie.RetrofitClient.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class Welcome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        final TextView touchhere = findViewById(R.id.welcome_touchhere);
        final TextView description = findViewById(R.id.welcome_text);
        final LottieAnimationView splash = findViewById(R.id.welcome_splash);
        final LottieAnimationView tired = findViewById(R.id.welcome_writedown);
        final LottieAnimationView gotyou = findViewById(R.id.welcome_gotyou);
        final LottieAnimationView card = findViewById(R.id.welcome_cardie);
        final LottieAnimationView start = findViewById(R.id.welcome_start);


        tired.setVisibility(View.GONE);
        gotyou.setVisibility(View.GONE);
        card.setVisibility(View.GONE);
        start.setVisibility(View.GONE);
        final MediaPlayer mp = MediaPlayer.create(this, R.raw.welcome);
        splash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                splash.setSpeed(2);
                splash.playAnimation();
                splash.setEnabled(false);
                splash.setClickable(false);
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        touchhere.setText("");
                        tired.setVisibility(View.VISIBLE);
                        tired.setSpeed(2);
                        tired.playAnimation();
                        tired.setRepeatCount(9999);
                        description.setText("Tired of having to write down whenever you wanna remember something?");
                        tired.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        tired.setVisibility(View.GONE);
                                        gotyou.setVisibility(View.VISIBLE);
                                        gotyou.setSpeed(2);
                                        gotyou.playAnimation();

                                        gotyou.setRepeatCount(9999);
                                        description.setText("Don't worry, we've got you covered");
                                        gotyou.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                handler.postDelayed(new Runnable() {
                                                    @Override
                                                    public void run() {
                                                        gotyou.setVisibility(View.GONE);
                                                        gotyou.setEnabled(false);
                                                        gotyou.setClickable(false);
                                                        card.setVisibility(View.VISIBLE);
                                                        card.setSpeed(1);
                                                        card.playAnimation();

                                                        card.setRepeatCount(9999);
                                                        description.setText("Our Cardie app provides you with intensive flash cards system");
                                                        card.setOnClickListener(new View.OnClickListener() {
                                                            @Override
                                                            public void onClick(View v) {
                                                                card.setVisibility(View.GONE);
                                                                card.setEnabled(false);
                                                                card.setClickable(false);
                                                                start.setVisibility(View.VISIBLE);
                                                                description.setText("Let's get started");
                                                                start.setOnClickListener(new View.OnClickListener() {
                                                                    @Override
                                                                    public void onClick(View v) {
                                                                        mp.start();
                                                                        start.setSpeed(1);
                                                                        start.playAnimation();
                                                                        start.setEnabled(false);
                                                                        start.setClickable(false);
                                                                        handler.postDelayed(new Runnable() {
                                                                            @Override
                                                                            public void run() {
                                                                                SharedPreferences sharedPref
                                                                                        = getSharedPreferences("UserProfile",
                                                                                        MODE_PRIVATE);
                                                                                String username = sharedPref.getString("username","");
                                                                                if (username.isEmpty() || !checkDatabaseUser(username)) {
                                                                                    Intent intent = new Intent(Welcome.this, LoginScreen.class);
                                                                                    startActivity(intent);
                                                                                }
                                                                                else
                                                                                {
                                                                                    if (checkDatabaseUser(username)) {
                                                                                        String welcome = "Welcome " + username;
                                                                                        Toast.makeText(Welcome.this, welcome, Toast.LENGTH_LONG).show();
                                                                                        Intent intent = new Intent(Welcome.this, setlist_main.class);
                                                                                        startActivity(intent);
                                                                                    }
                                                                                }
                                                                            }
                                                                        }, 2500);
                                                                    }
                                                                });
                                                            }
                                                        });
                                                    }
                                                }, 500);
                                            }
                                        });
                                    }
                                }, 200);
                            }
                        });
                    }
                }, 500);

            }
        });
    }

    private boolean checkDatabaseUser(String str)
    {
        final boolean[] value = {false};
        Retrofit retrofit = RetrofitClient.getInstance();
        final API api = retrofit.create(API.class);
        Call<User> call = api.getUserProfile(str);
        call.enqueue(new Callback<User>() {
                         @Override
                         public void onResponse(Call<User> call, Response<User> response) {
                             if (response.code()==200) {
                                value[0] =true;
                             }
                             else if (response.code()==404)
                             {
                                value[0] = false;
                             }
                         }

                         @Override
                         public void onFailure(Call<User> call, Throwable t) {
                             Toast.makeText(Welcome.this,t.getMessage(),Toast.LENGTH_LONG).show();
                         }
                     }
        );
        return value[0];
    }
}
