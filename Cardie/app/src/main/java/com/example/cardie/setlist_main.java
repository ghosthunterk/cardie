package com.example.cardie;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cardie.Models.CardieSet;
import com.example.cardie.RetrofitClient.API;
import com.example.cardie.RetrofitClient.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class setlist_main extends AppCompatActivity implements SetListener {

    Button profile;
    TextView totalset;
    List<CardieSet> SetList;
    @Override
    protected void onCreate(Bundle savedInstanceState)  {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cardlist_main);
        Button btnsetting = findViewById(R.id.setting);
        btnsetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (setlist_main.this,Settings.class);
                startActivity(intent);
            }
        });
        totalset = findViewById(R.id.total_sets);
        int bg = R.drawable.card_round_corner_bluelight;
        SetList = new ArrayList<>();
/*        SetList.add(new CardieSet("1235","set1",bg,10,"1234",3));
        SetList.add(new CardieSet("1235","set1",bg,10,"1234",3));
        SetList.add(new CardieSet("1235","set1",bg,10,"1234",3));
        SetList.add(new CardieSet("1235","set1",bg,10,"1234",3));
        SetList.add(new CardieSet("1235","set1",bg,10,"1234",3));
        SetList.add(new CardieSet("1235","set1",bg,10,"1234",3));
        SetList.add(new CardieSet("1235","set1",bg,10,"1234",3));
        SetList.add(new CardieSet("1235","set1",bg,10,"1234",3));
        SetList.add(new CardieSet("1235","set1",bg,10,"1234",3));
        SetList.add(new CardieSet("1235","set1",bg,10,"1234",3));
        SetList.add(new CardieSet("1235","set1",bg,10,"1234",3));
        SetList.add(new CardieSet("1235","set1",bg,10,"1234",3));
        SetList.add(new CardieSet("1235","set1",bg,10,"1234",3));
        SetList.add(new CardieSet("1235","set1",bg,10,"1234",3));
        SetList.add(new CardieSet("1235","set1",bg,10,"1234",3));*/
//        setlist.add(new CardieSet("1235","set",bg,10,"1234",2));
//        setlist.add(new CardieSet("1235","set",bg,10,"1234",1));
//        /*setlist.add(new CardieSet("1235","set",bg,10,"1234"));
//        setlist.add(new CardieSet("1235","set",bg,10,"1234"));
//        setlist.add(new CardieSet("1235","set",bg,10,"1234"));
//        setlist.add(new CardieSet("1235","set",bg,10,"1234"));
//        setlist.add(new CardieSet("1235","set",bg,10,"1234"));
//        setlist.add(new CardieSet("1235","set",bg,10,"1234"));
//        setlist.add(new CardieSet("1235","set",bg,10,"1234"));
//        setlist.add(new CardieSet("1235","set",bg,10,"1234"));
//        setlist.add(new CardieSet("1235","set",bg,10,"1234"));
//        setlist.add(new CardieSet("1235","set",bg,10,"1234"));*/

        //Get data from API
        Retrofit retrofit = RetrofitClient.getInstance();
        API api = retrofit.create(API.class);
        Call<List<CardieSet>> call = api.getAllSet();
        call.enqueue(new Callback<List<CardieSet>>() {
            @Override
            public void onResponse(Call<List<CardieSet>> call, Response<List<CardieSet>> response) {
                for (CardieSet set:response.body())
                {
                    SetList.add(set);
                    System.out.println(set.getSetName());
                }
                Toast.makeText(getApplicationContext(),String.valueOf(SetList.size()), Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(Call<List<CardieSet>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        // My set
        totalset.setText(String.valueOf(SetList.size()));
        RecyclerView Set_RV_myset =  findViewById(R.id.recyclerview_myset);
        setlist_adapter Set_Adapter_myset = new setlist_adapter(this, SetList,this);
        LinearLayoutManager linearLayoutManager_myset = new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        Set_RV_myset.setLayoutManager(linearLayoutManager_myset);
        Set_RV_myset.setAdapter(Set_Adapter_myset);


        //Explore set
        RecyclerView Set_RV_peopleset =  findViewById(R.id.recyclerview_peopleset);
        setlist_adapter Set_Adapter_peopleset = new setlist_adapter(this, SetList,this);
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
    public void onPracticeButtonClick(String setName) {
        Intent intent = new Intent(this, TestModeActivity.class);
        intent.putExtra("setName",setName);
        startActivity(intent);
    }

    @Override
    public void onSetClick(String setName) {

    }


}
