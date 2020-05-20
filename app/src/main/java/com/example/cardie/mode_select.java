package com.example.cardie;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class mode_select extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mode_select);
        Intent intent=this.getIntent();
        final String str = intent.getExtras().getString("setName");
        TextView mode1=findViewById(R.id.mode1);
        mode1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mode_select.this,TestMode1.class);
                intent.putExtra("setName2",str);
                startActivity(intent);
            }
        });
    }
}
