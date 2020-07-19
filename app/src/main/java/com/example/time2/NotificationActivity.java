package com.example.time2;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ToggleButton;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class NotificationActivity extends AppCompatActivity {

    //private Context mContext;
    private EditText editHours, editMins;
    //ToggleButton toggleButton;
    Button saveNotifications;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acitivty_notification);
       // mContext = getApplicationContext();

        //Initialize UI elements
        editHours = findViewById(R.id.editHour);
        editMins = findViewById(R.id.editMinute);
        //toggleButton = findViewById(R.id.toggleButton_repeating);
        saveNotifications = findViewById(R.id.button_saveNotification);

        saveNotifications.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String hours = editHours.getText().toString().trim();
                String minutes = editMins.getText().toString().trim();
                int iHours = Integer.parseInt(hours);
                int iMins = Integer.parseInt(minutes);

                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.HOUR_OF_DAY,iHours);
                calendar.set(Calendar.MINUTE,iMins);

                Intent intent = new Intent(getApplicationContext(), AlarmReceiver.class);
                PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 100, intent,PendingIntent.FLAG_UPDATE_CURRENT);

                AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
                alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),AlarmManager.INTERVAL_DAY, pendingIntent);
                startActivity(new Intent(getApplicationContext(), DashboardActivity.class));
            }
        });
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
