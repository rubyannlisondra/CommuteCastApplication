package com.example.commutecast;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;

public class Notification_Class extends Application {

    public static final String channel1_id = "My First Channel";
    public static final String channel2_id = "My Second Channel";

    @Override
    public void onCreate() {
    super.onCreate();
    createNotificationChannel();

    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel my_channel1 = new NotificationChannel(channel1_id,"My First Channel", NotificationManager.IMPORTANCE_HIGH);
            my_channel1.setDescription("This is my first channel");

            NotificationChannel my_channel2 = new NotificationChannel(channel2_id,"My Second Channel", NotificationManager.IMPORTANCE_DEFAULT);
            my_channel2.setDescription("This is my second channel");

            NotificationManager notifmanager = getSystemService(NotificationManager.class);
            notifmanager.createNotificationChannel(my_channel1);
            notifmanager.createNotificationChannel(my_channel2);
        }
    }
}