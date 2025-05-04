package com.example.fitsync;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MusclesType extends AppCompatActivity {
    private Button chestButton,backButton,shouldersButton,armsButton,legsButton,coreButton,cardioButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_muscles_type);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        chestButton = findViewById(R.id.chest);
        backButton = findViewById(R.id.back);
        shouldersButton = findViewById(R.id.shoulders_bt);
        armsButton = findViewById(R.id.arms_bt);
        legsButton = findViewById(R.id.legs);
        coreButton = findViewById(R.id.core);
        cardioButton = findViewById(R.id.cardio);

        chestButton.setOnClickListener(v -> openExerciseActivity("chest"));
        backButton.setOnClickListener(v -> openExerciseActivity("back"));
        shouldersButton.setOnClickListener(v -> openExerciseActivity("shoulders"));
        armsButton.setOnClickListener(v -> openExerciseActivity("upper arms"));
        legsButton.setOnClickListener(v -> openExerciseActivity("upper legs"));
        coreButton.setOnClickListener(v -> openExerciseActivity("waist"));
        cardioButton.setOnClickListener(v -> openExerciseActivity("cardio"));

    }
    private void openExerciseActivity(String bodyPart) {
        Intent intent = new Intent(this, ExerciseActivity.class);
        intent.putExtra("bodyPart", bodyPart);
        startActivity(intent);
    }

}