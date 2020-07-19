package com.example.time2;

import android.app.Notification;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import java.util.Random;

public class AlarmReceiver extends BroadcastReceiver {

    private String CHANNEL_NAME = "Default Channel";
    private String CHANNEL_ID = "com.example.time2" + CHANNEL_NAME;
    //NotificationHelper notificationHelper;

    @Override
    public void onReceive(Context context, Intent intent) {

        // Create Notification
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context,CHANNEL_ID)
                .setContentTitle("What's Your Time Worth?")
                .setContentText("Value your time by saving today!")
                .setSmallIcon(R.drawable.ic_baseline_access_time_24)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setAutoCancel(true);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
        notificationManager.notify(0, builder.build());
       // NotificationManagerCompat.from(this).notify(new Random().nextInt(), notification);

        //Initialize Notification Helper
        //notificationHelper = new NotificationHelper(context);
        //notificationHelper.sendDefaultNotification();

        //Intent repeating_intent = new Intent(context, MainActivity.class);

        // Replaces old activity
        //repeating_intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

    }
}