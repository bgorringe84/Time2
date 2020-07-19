package com.example.time2;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class NotificationActivity extends AppCompatActivity {

    private EditText editHours, editMins;
    Button saveNotifications;
    private String CHANNEL_NAME = "Default Channel";
    private String CHANNEL_ID = "com.example.time2" + CHANNEL_NAME;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acitivty_notification);
        createChannel();

        //Initialize UI elements
        editHours = findViewById(R.id.editHour);
        editMins = findViewById(R.id.editMinute);
        saveNotifications = findViewById(R.id.button_saveNotification);

        saveNotifications.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Toast
                Toast.makeText(NotificationActivity.this, "Reminder Set!", Toast.LENGTH_SHORT).show();

                // Converts string data into integer data to use for Calendar Object
                String hours = editHours.getText().toString().trim();
                String minutes = editMins.getText().toString().trim();
                int iHours = Integer.parseInt(hours);
                int iMins = Integer.parseInt(minutes);

                // Pushes the data to the calendar object and sets it as the user's inputted notification time
                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.HOUR_OF_DAY,iHours);
                calendar.set(Calendar.MINUTE,iMins);

                Intent intent = new Intent(NotificationActivity.this, AlarmReceiver.class);
                PendingIntent pendingIntent = PendingIntent.getBroadcast(NotificationActivity.this,0 , intent,0);
                //PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 100, intent,PendingIntent.FLAG_UPDATE_CURRENT);

                AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
                alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),AlarmManager.INTERVAL_DAY, pendingIntent);
                startActivity(new Intent(getApplicationContext(), DashboardActivity.class));
            }
        });
    }

    public void createChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT);
            notificationChannel.enableLights(true);
            notificationChannel.enableVibration(true);
            notificationChannel.setDescription("This is the default notification settings");
            notificationChannel.setLightColor(Color.BLUE);
            notificationChannel.setLockscreenVisibility(Notification.VISIBILITY_PUBLIC);

            NotificationManager manager = (NotificationManager) getSystemService(NotificationManager.class);
            manager.createNotificationChannel(notificationChannel);
        }
    }

/*
    public void enableToggleButton (View view) {
        boolean isEnabled = ((ToggleButton)view).isEnabled();

        if(isEnabled)
            NotificationHelper.scheduleRepeatingRTCNotification(mContext, editHours.getText().toString(), editMins.getText().toString());
        else
            NotificationHelper.cancelAlarmRTC();
    }
*/
}
