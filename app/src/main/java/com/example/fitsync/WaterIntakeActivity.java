package com.example.fitsync;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.AlarmManagerCompat;
import androidx.core.app.NotificationManagerCompat;

public class WaterIntakeActivity extends AppCompatActivity {

    private static final int DAILY_GOAL = 3500;
    private static final int INCREMENT_AMOUNT = 350;
    private static final int NOTIFICATION_ID = 2001;
    private ProgressBar progressBar;
    private TextView statusText;
    private SharedPreferences prefs;

    private final String PREF_NAME = "water_pref";
    private final String WATER_KEY = "water_amount";
    private int currentAmount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_water_intake);

        progressBar = findViewById(R.id.water_progress_bar);
        statusText = findViewById(R.id.water_status_text);
        Button addBtn = findViewById(R.id.add_button);
        Button minusBtn = findViewById(R.id.minus_button);

        prefs = getSharedPreferences(PREF_NAME, MODE_PRIVATE);
        currentAmount = prefs.getInt(WATER_KEY, 0);
        updateProgress(currentAmount);

        addBtn.setOnClickListener(v -> animateProgress(true));
        minusBtn.setOnClickListener(v -> animateProgress(false));

        createNotificationChannel();
        scheduleReminder(60 * 1000); // Every 1 minute

        handleNotificationActions();
    }

    private void animateProgress(boolean isAdd) {
        int start = currentAmount;
        int end = isAdd ? Math.min(start + INCREMENT_AMOUNT, DAILY_GOAL) : Math.max(start - INCREMENT_AMOUNT, 0);

        Handler handler = new Handler();
        final int[] progress = {start};
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                if ((isAdd && progress[0] < end) || (!isAdd && progress[0] > end)) {
                    progress[0] += isAdd ? 10 : -10;
                    progressBar.setProgress(progress[0]);
                    statusText.setText(progress[0] + " / " + DAILY_GOAL + " ml");
                    handler.postDelayed(this, 10);
                } else {
                    currentAmount = end;
                    prefs.edit().putInt(WATER_KEY, currentAmount).apply();
                }
            }
        };
        handler.post(runnable);
    }

    private void updateProgress(int amount) {
        progressBar.setProgress(amount);
        statusText.setText(amount + " / " + DAILY_GOAL + " ml");
    }

    private void scheduleReminder(long delayMs) {
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, WaterReminderReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(
                this, 1001, intent, PendingIntent.FLAG_IMMUTABLE | PendingIntent.FLAG_UPDATE_CURRENT);

        long triggerTime = System.currentTimeMillis() + delayMs;

        AlarmManagerCompat.setExactAndAllowWhileIdle(
                alarmManager, AlarmManager.RTC_WAKEUP, triggerTime, pendingIntent);
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(
                    "water_reminder_channel", "Water Reminder", NotificationManager.IMPORTANCE_HIGH);
            channel.setDescription("Reminds user to drink water");

            NotificationManager manager = getSystemService(NotificationManager.class);
            if (manager != null) manager.createNotificationChannel(channel);
        }
    }

    private void handleNotificationActions() {
        Intent intent = getIntent();
        if (intent != null && intent.getAction() != null) {
            // Cancel the notification when any action is clicked
            NotificationManagerCompat.from(this).cancel(NOTIFICATION_ID);

            switch (intent.getAction()) {
                case "ACTION_DONE":
                    animateProgress(true); // Add water and animate
                    break;
                case "ACTION_REMIND_LATER":
                    scheduleReminder(60 * 1000); // Schedule again in 1 minute
                    break;
                case "ACTION_SKIP":
                    // No action needed for skip
                    break;
            }
        }
    }
}