package com.example.cardie;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.viewpager.widget.ViewPager;

import android.app.Dialog;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.example.cardie.Models.Card;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class TestMode1 extends AppCompatActivity {
    ViewPagerAdapter_SetResult Adapter;
    ViewPager viewPager;
    List<Card> mData;
    TextView setDefinition;
    TextView currentCardNum;
    Button choice1;
    Button choice2;
    Button choiceStop;
    int pos=0;
    int score;
    int count=0;
    TextView definition;
    Dialog myDialog;
    TextView mySetName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_mode1);
        timer();
        setDefinition=findViewById(R.id.test_definition);
        currentCardNum=findViewById(R.id.currentQuestionNum);
        choice1=findViewById(R.id.choice1);
        choice2=findViewById(R.id.choice2);
        choiceStop = findViewById(R.id.choiceStop);
        Intent intent = this.getIntent();
        String str=intent.getExtras().getString("setName2");
        mySetName=findViewById(R.id.testmode1_setname);
        mySetName.setText(str);
        mData=new ArrayList<>();
        mData.add(new Card("C01","Cat","Cute","Noun",R.drawable.cat_standing));
        mData.add(new Card("C01","Dog","Friendly","Noun",R.drawable.cat_standing));
        mData.add(new Card("C01","Classification","What","Noun",R.drawable.cat_standing));
        mData.add(new Card("C01","Cat","Cute","Noun",R.drawable.cat_standing));
        mData.add(new Card("C01","Dog","Friendly","Noun",R.drawable.cat_standing));
        mData.add(new Card("C01","Classification","What","Noun",R.drawable.cat_standing));
        mData.add(new Card("C01","Cat","Cute","Noun",R.drawable.cat_standing));
        mData.add(new Card("C01","Dog","Friendly","Noun",R.drawable.cat_standing));
        mData.add(new Card("C01","Classification","What","Noun",R.drawable.cat_standing));
        currentCardNum.setText("Question " + String.valueOf(1)+"/"+(mData.size()));
        choice1.setText(mData.get(0).getCardWord());
        choice2.setText(mData.get(getRandomNum(0,mData.size()-1,0)).getCardWord());
        Adapter = new ViewPagerAdapter_SetResult(TestMode1.this,mData);
        viewPager = findViewById(R.id.viewPager_testmode1);
        viewPager.setAdapter(Adapter);
        myDialog=new Dialog(this);
        // Declare Event's Listener
        ViewPager.OnPageChangeListener viewPagerListener = new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                pos=position;
                currentCardNum.setText("Question " + String.valueOf(position+1)+"/" +(mData.size()));
                choice1.setText(mData.get(position).getCardDefinition());
                choice2.setText(mData.get(getRandomNum(0,mData.size()-1,position)).getCardDefinition());

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        };
        viewPager.addOnPageChangeListener(viewPagerListener); //Add Listener to ViewPager
        score=0;
        initAns();

/*        final LottieAnimationView lottieAnimationWrong=findViewById(R.id.wrong_anim);
        lottieAnimationWrong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lottieAnimationWrong.setSpeed(1);
                lottieAnimationWrong.playAnimation();
            }
        });*/
    }
    public void anim_correct() {
        final LottieAnimationView lottieAnimationCorrect=findViewById(R.id.correct_anim);
        lottieAnimationCorrect.setSpeed(2);
        lottieAnimationCorrect.playAnimation();

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                lottieAnimationCorrect.setSpeed(-2);
                lottieAnimationCorrect.playAnimation();
            }
        }, 1000);
    }
    public void anim_wrong() {
        final LottieAnimationView lottieAnimationWrong=findViewById(R.id.wrong_anim);
        lottieAnimationWrong.setSpeed(2);
        lottieAnimationWrong.playAnimation();

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                lottieAnimationWrong.setSpeed(-2);
                lottieAnimationWrong.playAnimation();
            }
        }, 1000);
    }

    //GETRANDOM

    public int getRandomNum(int min,int max, int excludeNum){
        int rand=(int) (Math.random()*((max-min)+1)+min);
        while (excludeNum==rand){
            rand=(int) (Math.random() * ((max-min)+1)+min);
        }
        return rand;
    }

    //INIT ANSWER

    public void initAns(){
        choiceStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initResult(score);
            }
        });
        //CHOICE1

        choice1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (pos<mData.size()-1){
                    if (choice1.getText().toString()==mData.get(pos).getCardWord()){
                        score++;
                        /*myDialog.setContentView(R.layout.popup_answer_right);
                        myDialog.show();*/
                        anim_correct();

                    }
                    else {
                        /*myDialog.setContentView(R.layout.popup_answer_wrong);
                        myDialog.show();*/
                        anim_wrong();

                    }
                    final int nextpos = pos + 1;

                    if (getRandomNum(0,2,1)==2){
                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                viewPager.setCurrentItem(nextpos);
                                choice1.setText(mData.get(nextpos).getCardWord());
                                choice2.setText(mData.get(getRandomNum(0,mData.size()-1,nextpos)+1).getCardWord());
                            }
                        }, 1500);
                    }
                    else {
                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                viewPager.setCurrentItem(nextpos);
                                choice2.setText(mData.get(nextpos).getCardWord());
                                choice1.setText(mData.get(getRandomNum(0,mData.size()-1,nextpos)+1).getCardWord());
                            }
                        }, 1500);
                    }
                }
                else {
                    if (choice1.getText().toString()==mData.get(pos).getCardWord()){
                        score++;
                       /* myDialog.setContentView(R.layout.popup_answer_right);
                        myDialog.show();*/
                        anim_correct();

                    }
                    else {
                 /*       myDialog.setContentView(R.layout.popup_answer_wrong);
                        myDialog.show();*/
                        anim_wrong();

                    }
                    initResult(score);
                }
            }

        });

        //CHOICE2

        choice2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (pos<mData.size()-1){
                    if (choice2.getText().toString()==mData.get(pos).getCardWord()){
                        score++;
/*                        myDialog.setContentView(R.layout.popup_answer_right);
                        myDialog.show();*/
                        anim_correct();

                    }
                    else {
/*                        myDialog.setContentView(R.layout.popup_answer_wrong);
                        myDialog.show();*/
                        anim_wrong();

                    }
                    final int nextpos = pos + 1;

                    if (getRandomNum(0,2,1)==2){
                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                viewPager.setCurrentItem(nextpos);
                                choice1.setText(mData.get(nextpos).getCardWord());
                                choice2.setText(mData.get(getRandomNum(0,mData.size()-1,nextpos)+1).getCardWord());
                            }
                        }, 1500);

                    }
                    else {
                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                viewPager.setCurrentItem(nextpos);
                                choice2.setText(mData.get(nextpos).getCardWord());
                                choice1.setText(mData.get(getRandomNum(0,mData.size()-1,nextpos)+1).getCardWord());
                            }
                        }, 1500);

                    }
                }
                else {
                    if (choice2.getText().toString()==mData.get(pos).getCardWord()){
                        score++;
/*                        myDialog.setContentView(R.layout.popup_answer_right);
                        myDialog.show();*/
                        anim_correct();

                    }
                    else {
/*                        myDialog.setContentView(R.layout.popup_answer_wrong);
                        myDialog.show();*/
                        anim_wrong();

                    }
                    initResult(score);
                }
            }

        });

    }




    public void initResult(int score){
        System.out.println(score);
        Intent intent = new Intent(TestMode1.this,ResultScreen.class);
        intent.putExtra("score",String.valueOf(score));
        intent.putExtra("totalquestion",String.valueOf(mData.size()));
        intent.putExtra("timer",String.valueOf(count));
        startActivity(intent);
    }
    public void timer(){
        Timer T=new Timer();
        T.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        count++;
                    }
                });
            }
        }, 1000, 1000);

    }

}
