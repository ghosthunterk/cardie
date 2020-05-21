package com.example.cardie;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.cardie.Models.Card;

import java.util.ArrayList;
import java.util.List;

public class SetViewActivity extends AppCompatActivity {
    ViewPagerAdapter_SetCard Adapter;
    ViewPager viewPager;
    List<Card> mData;
    TextView setName;
    ImageView diffEasy;
    ImageView diffMedium;
    ImageView diffHard;
    Button addcards;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_view);
        setName = findViewById(R.id.SetName);
        diffEasy = findViewById(R.id.diffEasy);
        diffMedium = findViewById(R.id.diffMedium);
        diffHard = findViewById(R.id.diffHard);
        addcards=findViewById(R.id.add_card);
        addcards.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addcard();
            }
        });
        mData = new ArrayList<>();
        mData.add(new Card("C01","Cat","Noun",R.drawable.cat_standing));
        mData.add(new Card("C01","Dog","Noun",R.drawable.cat_standing));
        mData.add(new Card("C01","Classification","Noun",R.drawable.cat_standing));
        //get information to display
        initialize();
        //Setup Viewpager
        Adapter = new ViewPagerAdapter_SetCard(mData,SetViewActivity.this);
        viewPager = findViewById(R.id.CardViewPager);
        viewPager.setAdapter(Adapter);
        // Declare Event's Listener
        ViewPager.OnPageChangeListener viewPagerListener = new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        };

        viewPager.addOnPageChangeListener(viewPagerListener); //Add Listener to ViewPager

    }

    private void initialize()
    {
        Intent previousIntent = this.getIntent();
        setName.setText(previousIntent.getExtras().getString("SetName"));
        int diff = previousIntent.getExtras().getInt("SetDifficulty");
        switch (diff)
        {
            case 1:
                diffEasy.setBackgroundTintList(getResources().getColorStateList(R.color.greendiff,null));
                break;
            case 2:
                diffEasy.setBackgroundTintList(getResources().getColorStateList(R.color.greendiff,null));
                diffEasy.setBackgroundTintList(getResources().getColorStateList(R.color.backgroundbluelight,null));
                break;
            case 3:
                diffEasy.setBackgroundTintList(getResources().getColorStateList(R.color.greendiff,null));
                diffEasy.setBackgroundTintList(getResources().getColorStateList(R.color.backgroundbluelight,null));
                diffHard.setBackgroundTintList(getResources().getColorStateList(R.color.backgroundOrange,null));
                break;
            default:
                break;
        }
    }
    public void addcard(){
        Intent intent = new Intent(SetViewActivity.this,AddCard.class);
        startActivity(intent);
    }
}
