package com.example.fitsync;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import java.util.ArrayList;

public class BMICalculator extends AppCompatActivity {

    private EditText heightInput, weightInput;
    private TextView bmiResult, bmiCategory;
    private Button calculateButton;
    private LineChart bmiChart;

    private ArrayList<Entry> bmiEntries = new ArrayList<>();
    private int entryIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi_calculator);

        heightInput = findViewById(R.id.height_input);
        weightInput = findViewById(R.id.weight_input);
        calculateButton = findViewById(R.id.calculate_button);
        bmiResult = findViewById(R.id.bmi_result);
        bmiCategory = findViewById(R.id.bmi_category);
        bmiChart = findViewById(R.id.bmi_chart);

        setupChart();

        calculateButton.setOnClickListener(v -> calculateBMI());
    }

    private void setupChart() {
        bmiChart.getDescription().setEnabled(false);
        bmiChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        bmiChart.getAxisRight().setEnabled(false);
        bmiChart.getLegend().setEnabled(false);
    }

    private void calculateBMI() {
        String heightStr = heightInput.getText().toString();
        String weightStr = weightInput.getText().toString();

        if (heightStr.isEmpty() || weightStr.isEmpty()) {
            bmiResult.setText("Please enter both height and weight");
            bmiCategory.setText("");
            return;
        }

        float height = Float.parseFloat(heightStr);
        float weight = Float.parseFloat(weightStr);
        float bmi = weight / (height * height);

        bmiResult.setText(String.format("BMI: %.2f", bmi));
        bmiCategory.setText("Category: " + getBMICategory(bmi));

        // Add BMI to chart
        bmiEntries.add(new Entry(entryIndex++, bmi));
        LineDataSet dataSet = new LineDataSet(bmiEntries, "BMI");
        dataSet.setColor(Color.BLUE);
        dataSet.setCircleColor(Color.BLUE);
        dataSet.setValueTextSize(12f);
        dataSet.setCircleRadius(5f);
        dataSet.setLineWidth(2f);

        LineData lineData = new LineData(dataSet);
        bmiChart.setData(lineData);
        bmiChart.invalidate(); // Refresh chart
    }

    private String getBMICategory(float bmi) {
        if (bmi < 18.5)
            return "Underweight";
        else if (bmi < 25)
            return "Normal weight";
        else if (bmi < 30)
            return "Overweight";
        else
            return "Obese";
    }
}