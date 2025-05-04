package com.example.fitsync;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class ExerciseActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ExerciseAdapter adapter;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_exercise);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        textView = findViewById(R.id.exerciseTextView);
        recyclerView = findViewById(R.id.exerciseRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // ✅ Initialize adapter with empty list and attach to RecyclerView
        adapter = new ExerciseAdapter(this, new ArrayList<>());
        recyclerView.setAdapter(adapter);

        String bodyPart = getIntent().getStringExtra("bodyPart");

        ExerciseApi.fetchExercisesBodyPart(bodyPart, new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                Log.e("Fetch Fail", "Failed to fetch exercises", e);
                runOnUiThread(() -> textView.setText("Failed to fetch exercises."));
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if (response.isSuccessful() && response.body() != null) {
                    String responseBody = response.body().string();
                    Log.d("API Response", responseBody);
                    Gson gson = new Gson();
                    Type type = new TypeToken<List<Exercise>>() {}.getType();
                    List<Exercise> exercises = gson.fromJson(responseBody, type);
                    Log.d("ExerciseSize", "Exercises fetched: " + (exercises != null ? exercises.size() : 0));

                    runOnUiThread(() -> {
                        if (exercises != null && !exercises.isEmpty()) {
                            adapter.updateData(exercises);
                        } else {
                            textView.setText("No exercises found.");
                        }
                    });
                } else {
                    runOnUiThread(() -> textView.setText("Response unsuccessful."));
                    Log.e("Fetch Fail", "Unsuccessful response");
                }
            }
        });
    }
}
