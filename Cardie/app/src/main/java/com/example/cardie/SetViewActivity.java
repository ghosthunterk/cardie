package com.example.cardie;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.cardie.Models.Card;
import com.example.cardie.Models.Card;
import com.example.cardie.RetrofitClient.API;
import com.example.cardie.RetrofitClient.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class SetViewActivity extends AppCompatActivity {
    private String[] imageUrls = new String[]{

    };
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
        String imageurl = "https://www.aldergrovestar.com/wp-content/uploads/2020/02/20592275_web1_Langley-Weather-Sun-Clear-Sky-Skies.jpg";
//        mData.add(new Card("C01","Cat","Noun",imageurl));
//        mData.add(new Card("C01","Dog","Noun",imageurl));
//        mData.add(new Card("C01","Classification","Noun",imageurl));
        //get information to display
        initialize();

        //Get data from API
        Retrofit retrofit = RetrofitClient.getInstance();
        API api = retrofit.create(API.class);
        Call<List<Card>> call = api.getCardsBySet(setName.getText().toString());
        call.enqueue(new Callback<List<Card>>() {
            @Override
            public void onResponse(Call<List<Card>> call, Response<List<Card>> response) {
                for (Card card:response.body())
                {
                    mData.add(card);
                }
                Toast.makeText(getApplicationContext(),String.valueOf(mData.size()), Toast.LENGTH_LONG).show();

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

            @Override
            public void onFailure(Call<List<Card>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

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
        intent.putExtra("SetName",setName.getText());
        startActivity(intent);
    }
}
