package com.example.cardie;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class TestModeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mode_select);
        Intent intent=this.getIntent();
        final String str = intent.getExtras().getString("setName");
        TextView mode1=findViewById(R.id.mode1);
        TextView btnreturn = findViewById(R.id.return_mode_select);
        btnreturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (TestModeActivity.this,setlist_main.class);
                startActivity(intent);
            }
        });
        mode1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TestModeActivity.this,TestMode1.class);
                intent.putExtra("setName2",str);
                startActivity(intent);
            }
        });
    }
}
