package com.example.cardie;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class ResultScreen extends AppCompatActivity {
    TextView timecount;
    TextView correct;
    int count = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_screen);
        correct=findViewById(R.id.correct_count);
        Intent intent =this.getIntent();
        String str=intent.getExtras().getString("score");
//        System.out.println(str);
        correct.setText(str + "/"+intent.getExtras().getString("totalquestion"));
        timecount=findViewById(R.id.time_count);
        timecount.setText(intent.getExtras().getString("timer")+"s");
    }

}
