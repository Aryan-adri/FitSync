package com.example.fitsync;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class WaterIntakeActivity extends AppCompatActivity {

    EditText waterinput;
    Button logButton;
    TextView intakeResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_water_intake);

        waterinput = findViewById(R.id.water_intake);
        //logButton = findViewById(R.id.log_button);
        //intakeResult = findViewById(R.id.intake_result);

        logButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logWaterIntake();
            }
        });
    }

    private void logWaterIntake() {
        String waterAmount = waterinput.getText().toString();

        if (!waterAmount.isEmpty()) {
            intakeResult.setText("Water Intake: " + waterAmount + " ml");
        }
    }
}
