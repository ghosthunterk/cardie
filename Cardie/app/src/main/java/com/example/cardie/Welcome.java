package com.example.cardie;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;

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
                                                                                if (username.isEmpty()) {
                                                                                    Intent intent = new Intent(Welcome.this, LoginScreen.class);
                                                                                    startActivity(intent);
                                                                                }
                                                                                else
                                                                                {
                                                                                    Intent intent = new Intent(Welcome.this, setlist_main.class);
                                                                                    startActivity(intent);
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
}
