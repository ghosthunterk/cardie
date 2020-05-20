package com.example.cardie;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
    TextView definition;
    Dialog myDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_mode1);
        setDefinition=findViewById(R.id.test_definition);
        currentCardNum=findViewById(R.id.currentQuestionNum);
        choice1=findViewById(R.id.choice1);
        choice2=findViewById(R.id.choice2);
        choiceStop = findViewById(R.id.choiceStop);

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
                        myDialog.setContentView(R.layout.popup_answer_right);
                        myDialog.show();
                    }
                    else {
                        myDialog.setContentView(R.layout.popup_answer_wrong);
                        myDialog.show();
                    }
                    int nextpos = pos + 1;
                    viewPager.setCurrentItem(nextpos);
                    if (getRandomNum(0,2,1)==2){
                        choice1.setText(mData.get(nextpos).getCardWord());
                        choice2.setText(mData.get(getRandomNum(0,mData.size()-1,nextpos)+1).getCardWord());
                    }
                    else {
                        choice2.setText(mData.get(nextpos).getCardWord());
                        choice1.setText(mData.get(getRandomNum(0,mData.size()-1,nextpos)+1).getCardWord());
                    }
                }
                else {
                    if (choice1.getText().toString()==mData.get(pos).getCardWord()){
                        score++;
                        myDialog.setContentView(R.layout.popup_answer_right);
                        myDialog.show();
                    }
                    else {
                        myDialog.setContentView(R.layout.popup_answer_wrong);
                        myDialog.show();
                    }
                    System.out.println(score);
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
                        myDialog.setContentView(R.layout.popup_answer_right);
                        myDialog.show();
                    }
                    else {
                        myDialog.setContentView(R.layout.popup_answer_wrong);
                        myDialog.show();
                    }
                    int nextpos = pos + 1;
                    viewPager.setCurrentItem(nextpos);
                    if (getRandomNum(0,2,1)==2){
                        choice1.setText(mData.get(nextpos).getCardWord());
                        choice2.setText(mData.get(getRandomNum(0,mData.size()-1,nextpos)+1).getCardWord());
                    }
                    else {
                        choice2.setText(mData.get(nextpos).getCardWord());
                        choice1.setText(mData.get(getRandomNum(0,mData.size()-1,nextpos)+1).getCardWord());
                    }
                }
                else {
                    if (choice2.getText().toString()==mData.get(pos).getCardWord()){
                        score++;
                        myDialog.setContentView(R.layout.popup_answer_right);
                        myDialog.show();
                    }
                    else {
                        myDialog.setContentView(R.layout.popup_answer_wrong);
                        myDialog.show();
                    }
                    System.out.println(score);
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
        startActivity(intent);
    }

}
