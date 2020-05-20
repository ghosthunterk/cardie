package com.example.cardie;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class setlist_main extends AppCompatActivity implements SetListener {

    Button profile;

    List<CardieSet> setlist;
    @Override
    protected void onCreate(Bundle savedInstanceState)  {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cardlist_main);
        int bg = R.drawable.card_round_corner_bluelight;
        setlist = new ArrayList<>();
        setlist.add(new CardieSet("1235","set1",bg,10,"1234",3));
        setlist.add(new CardieSet("1235","set",bg,10,"1234",2));
        setlist.add(new CardieSet("1235","set",bg,10,"1234",1));
        /*setlist.add(new CardieSet("1235","set",bg,10,"1234"));
        setlist.add(new CardieSet("1235","set",bg,10,"1234"));
        setlist.add(new CardieSet("1235","set",bg,10,"1234"));
        setlist.add(new CardieSet("1235","set",bg,10,"1234"));
        setlist.add(new CardieSet("1235","set",bg,10,"1234"));
        setlist.add(new CardieSet("1235","set",bg,10,"1234"));
        setlist.add(new CardieSet("1235","set",bg,10,"1234"));
        setlist.add(new CardieSet("1235","set",bg,10,"1234"));
        setlist.add(new CardieSet("1235","set",bg,10,"1234"));
        setlist.add(new CardieSet("1235","set",bg,10,"1234"));*/


        RecyclerView Set_RV_myset =  findViewById(R.id.recyclerview_myset);
        setlist_adapter Set_Adapter_myset = new setlist_adapter(this,setlist,this);
        LinearLayoutManager linearLayoutManager_myset = new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        Set_RV_myset.setLayoutManager(linearLayoutManager_myset);
        Set_RV_myset.setAdapter(Set_Adapter_myset);


        RecyclerView Set_RV_peopleset =  findViewById(R.id.recyclerview_peopleset);
        setlist_adapter Set_Adapter_peopleset = new setlist_adapter(this,setlist,this);
        LinearLayoutManager linearLayoutManager_peopleset = new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        Set_RV_peopleset.setLayoutManager(linearLayoutManager_peopleset);
        Set_RV_peopleset.setAdapter(Set_Adapter_peopleset);


        //button profile
        profile = (Button) findViewById(R.id.profile_pic);
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(setlist_main.this,UserProfile.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onSetClick(CardieSet set) {
        Intent intent = new Intent(setlist_main.this,SetViewActivity.class);
        intent.putExtra("SetName",set.getSetName());
        intent.putExtra("Difficulty",set.getDifficulty());
        startActivity(intent);
    }

    @Override
    public void onSetClick(String setName) {

    }


}
