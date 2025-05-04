package com.example.fitsync;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;

import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class WaterReminderReceiver extends BroadcastReceiver {

    private static final int NOTIFICATION_ID = 2001;

    @Override
    public void onReceive(Context context, Intent intent) {
        // Intent for Done action
        Intent doneIntent = new Intent(context, WaterIntakeActivity.class);
        doneIntent.setAction("ACTION_DONE");
        doneIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        PendingIntent donePendingIntent = PendingIntent.getActivity(
                context, 1003, doneIntent, PendingIntent.FLAG_IMMUTABLE | PendingIntent.FLAG_UPDATE_CURRENT);

        // Intent for Remind Me Later
        Intent remindIntent = new Intent(context, WaterIntakeActivity.class);
        remindIntent.setAction("ACTION_REMIND_LATER");
        remindIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        PendingIntent remindPendingIntent = PendingIntent.getActivity(
                context, 1004, remindIntent, PendingIntent.FLAG_IMMUTABLE | PendingIntent.FLAG_UPDATE_CURRENT);

        // Intent for Skip
        Intent skipIntent = new Intent(context, WaterIntakeActivity.class);
        skipIntent.setAction("ACTION_SKIP");
        skipIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        PendingIntent skipPendingIntent = PendingIntent.getActivity(
                context, 1005, skipIntent, PendingIntent.FLAG_IMMUTABLE | PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "water_reminder_channel")
                .setSmallIcon(R.drawable.custom_progress_bar)
                .setContentTitle("Time to drink water!")
                .setContentText("Click an option below.")
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setAutoCancel(true)
                .addAction(0, "Done", donePendingIntent)
                .addAction(0, "Remind Me Later", remindPendingIntent)
                .addAction(0, "Skip", skipPendingIntent);

        if (ActivityCompat.checkSelfPermission(context, android.Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
            return;
        }

        NotificationManagerCompat.from(context).notify(NOTIFICATION_ID, builder.build());
    }
}