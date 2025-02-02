package com.example.fitsync;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class BMICalculator extends AppCompatActivity {

    private EditText heightInput, weightInput;
    private Button calculateButton;
    private TextView bmiResult, bmiCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi_calculator);

        heightInput = findViewById(R.id.height_input);
        weightInput = findViewById(R.id.weight_input);
        calculateButton = findViewById(R.id.calculate_button);
        bmiResult = findViewById(R.id.bmi_result);
        bmiCategory = findViewById(R.id.bmi_category);

        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateBMI();
            }
        });
    }

    private void calculateBMI() {
        // Get inputs
        String heightText = heightInput.getText().toString();
        String weightText = weightInput.getText().toString();

        if (!heightText.isEmpty() && !weightText.isEmpty()) {
            double height = Double.parseDouble(heightText);
            double weight = Double.parseDouble(weightText);

            // Calculate BMI
            double bmi = weight / (height * height);
            bmiResult.setText(String.format("BMI: %.2f", bmi));

            // Determine BMI category
            if (bmi < 18.5) {
                bmiCategory.setText("Underweight");
            } else if (bmi < 24.9) {
                bmiCategory.setText("Normal weight");
            } else if (bmi < 29.9) {
                bmiCategory.setText("Overweight");
            } else {
                bmiCategory.setText("Obesity");
            }
        }
    }
}
