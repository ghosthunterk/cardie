package com.example.cardie;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.cardie.Models.Card;
import com.example.cardie.RetrofitClient.API;
import com.example.cardie.RetrofitClient.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class AddCard extends AppCompatActivity {

    EditText cardName;
    EditText cardDescription;
    Button addBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_card);
        cardName = findViewById(R.id.add_card_name);
        cardDescription = findViewById(R.id.add_card_definition);
        addBtn = findViewById(R.id.addBtn);
        Intent intent = this.getIntent();
        final String ParentSet = intent.getExtras().getString("SetName");
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Card newCard = new Card(null,
                        cardName.getText().toString(),
                        cardDescription.getText().toString(),
                        ParentSet,"");
                Toast.makeText(AddCard.this,newCard.getCardWord(),Toast.LENGTH_SHORT).show();
                addCardToDatabase(newCard);
            }
        });
    }

    private void addCardToDatabase(Card newCard) {

        Retrofit retrofit = RetrofitClient.getInstance();
        API api = retrofit.create(API.class);
        Call<Card> call = api.addCard(newCard.getCardWord(),newCard.getCardDefinition(),newCard.getCardImageUrl(),newCard.getCardType());
        call.enqueue(new Callback<Card>() {
            @Override
            public void onResponse(Call<Card> call, Response<Card> response) {
                Toast.makeText(AddCard.this,"Add Successfully",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Card> call, Throwable t) {
                Toast.makeText(AddCard.this,t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }
}
