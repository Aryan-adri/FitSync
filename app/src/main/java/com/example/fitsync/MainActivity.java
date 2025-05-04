package com.example.fitsync;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button bmiButton, workoutLogButton, waterIntakeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bmiButton = findViewById(R.id.bmi_button);
        workoutLogButton = findViewById(R.id.workout_log_button);
        waterIntakeButton = findViewById(R.id.water_intake);

        bmiButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, BMICalculator.class);
                startActivity(intent);
            }
        });

        workoutLogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, WorkoutType.class);
                startActivity(intent);
            }
        });

        waterIntakeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, WaterIntakeActivity.class);
                startActivity(intent);
            }
        });
    }

}
