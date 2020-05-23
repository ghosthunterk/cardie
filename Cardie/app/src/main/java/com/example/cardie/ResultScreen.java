package com.example.cardie;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import static java.sql.Types.INTEGER;

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
        Spinner spinner = findViewById(R.id.week_spinner);
        final String week_selected=spinner.getSelectedItem().toString();
        correct.setText(str + "/"+intent.getExtras().getString("totalquestion"));
        timecount=findViewById(R.id.time_count);
        timecount.setText(intent.getExtras().getString("timer")+"s");
        Button btnfinish = findViewById(R.id.result_button_finish);
        Button btncontinue= findViewById(R.id.result_button_continue);
        createNotificationChannel();
        btnfinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (ResultScreen.this,setlist_main.class);
                LearningReminder(Integer.valueOf(week_selected.charAt(0)));
                startActivity(intent);

            }
        });
        btncontinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (ResultScreen.this,setlist_main.class);
                LearningReminder(Integer.valueOf(week_selected.charAt(0)));
                startActivity(intent);

            }
        });
    }

    public void LearningReminder(int week){
        Toast.makeText(this,"Reminder Set!", Toast.LENGTH_SHORT).show();
        Intent mIntent = new Intent(ResultScreen.this, ReminderBroadcast.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(ResultScreen.this,0,mIntent,0);
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        long timeAtButtonPressed = System.currentTimeMillis();
        long remindertimer = 24*3600*week;
        alarmManager.set(AlarmManager.RTC_WAKEUP,
                timeAtButtonPressed + remindertimer,
                pendingIntent);
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= 23){
            CharSequence name = "LearningReminderChannel";
            String description = "Channel for Learning Reminder";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("LearningReminder",name,importance);
            channel.setDescription(description);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }

    }

}
