package com.example.cardie;

import android.app.NotificationChannel;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class ReminderBroadcast extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context,"LearningReminder")
                .setSmallIcon(R.drawable.ic_settings_black_24dp)
                .setContentTitle("Learning Reminder")
                .setContentText("Hi, have you been practicing this set?")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);
        NotificationManagerCompat notificationManager= NotificationManagerCompat.from(context);
        notificationManager.notify(200,builder.build());
    }
}
