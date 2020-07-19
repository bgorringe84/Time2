package com.example.time2;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;

import java.util.Calendar;

public class AlarmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        Intent repeating_intent = new Intent(context, MainActivity.class);

        // Replaces old activity
        repeating_intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        PendingIntent pendingIntent = PendingIntent.getActivity(context, 100, repeating_intent, PendingIntent.FLAG_UPDATE_CURRENT);

        // Create Notification
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
                .setContentIntent(pendingIntent)
                .setContentTitle("What's Your Time Worth?")
                .setContentText("Value your time by saving today!")
                .setSmallIcon(R.drawable.ic_baseline_access_time_24)
                .setAutoCancel(true);

        // Build Notification
        notificationManager.notify(100,builder.build());
        /*
        // App will start on MainActivity when notification is clicked
        Intent intentToRepeat = new Intent(context, MainActivity.class);
        intentToRepeat.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        // Pending Intent to handle launch of Activity in the above intent
        PendingIntent pendingIntent = PendingIntent.getActivity(context, NotificationHelper.ALARM_TYPE_RTC, intentToRepeat, PendingIntent.FLAG_UPDATE_CURRENT);

        // Build & Send Notification
        Notification repeatedNotification = buildLocalNotification(context, pendingIntent).build();
        NotificationHelper.getNotificationManager(context).notify(NotificationHelper.ALARM_TYPE_RTC, repeatedNotification);

         */
    }

/*
    public NotificationCompat.Builder buildLocalNotification(Context context, PendingIntent pendingIntent) {

        NotificationCompat.Builder builder =
                (NotificationCompat.Builder) new NotificationCompat.Builder(context)
                        .setContentIntent(pendingIntent)
                        .setSmallIcon(R.drawable.ic_baseline_access_time_24)
                        .setPriority(NotificationCompat.PRIORITY_HIGH)
                        .setCategory(NotificationCompat.CATEGORY_ALARM)
                        .setWhen(Calendar.getInstance().getTimeInMillis())
                        .setContentTitle("What's Your Time Worth?")
                        .setContentText("Value your time by saving today!")
                        .setAutoCancel(true);

        return builder;
    }

 */
}
