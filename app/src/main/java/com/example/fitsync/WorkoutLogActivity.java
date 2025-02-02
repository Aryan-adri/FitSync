package com.example.fitsync;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class WorkoutLogActivity extends AppCompatActivity {

    private EditText exerciseInput, durationInput, caloriesBurnedInput;
    private Button logButton;
    private TextView logResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_log);

        exerciseInput = findViewById(R.id.exercise_input);
        durationInput = findViewById(R.id.duration_input);
        caloriesBurnedInput = findViewById(R.id.calories_burned_input);
        logButton = findViewById(R.id.log_button);
        logResult = findViewById(R.id.log_result);

        logButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logWorkout();
            }
        });
    }

    private void logWorkout() {
        // Get inputs
        String exercise = exerciseInput.getText().toString();
        String duration = durationInput.getText().toString();
        String calories = caloriesBurnedInput.getText().toString();

        if (!exercise.isEmpty() && !duration.isEmpty() && !calories.isEmpty()) {
            logResult.setText("Logged Workout:\n" +
                    "Exercise: " + exercise + "\n" +
                    "Duration: " + duration + " minutes\n" +
                    "Calories Burned: " + calories + " kcal");
        }
    }
}
