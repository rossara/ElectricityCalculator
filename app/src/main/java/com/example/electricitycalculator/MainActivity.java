package com.example.electricitycalculator;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText etUnits, etRebate;
    private TextView tvResult;
    private Button btnCalculate, btnClear, btnAbout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize views
        etUnits = findViewById(R.id.etUnits);
        etRebate = findViewById(R.id.etRebate);
        tvResult = findViewById(R.id.tvResult);
        btnCalculate = findViewById(R.id.btnCalculate);
        btnClear = findViewById(R.id.btnClear);
        btnAbout = findViewById(R.id.btnAbout);

        // Calculate Button
        btnCalculate.setOnClickListener(v -> {
            String unitsStr = etUnits.getText().toString();
            String rebateStr = etRebate.getText().toString();

            if (TextUtils.isEmpty(unitsStr) || TextUtils.isEmpty(rebateStr)) {
                Toast.makeText(MainActivity.this, "Please fill in all fields!", Toast.LENGTH_SHORT).show();
                return;
            }

            double units = Double.parseDouble(unitsStr);
            double rebate = Double.parseDouble(rebateStr);

            if (rebate < 0 || rebate > 5) {
                Toast.makeText(MainActivity.this, "Rebate must be between 0% and 5%!", Toast.LENGTH_SHORT).show();
                return;
            }

            double totalBill = calculateBill(units);
            double finalBill = totalBill - (totalBill * (rebate / 100));

            tvResult.setText(String.format("Total Bill: RM %.2f", finalBill));
        });

        // Clear Button
        btnClear.setOnClickListener(v -> {
            etUnits.setText("");
            etRebate.setText("");
            tvResult.setText("Result will be displayed here");
        });

        // About Me Button
        btnAbout.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AboutMeActivity.class);
            startActivity(intent);
        });
    }

    // Function to calculate bill based on units
    private double calculateBill(double units) {
        double bill = 0;

        if (units <= 200) {
            bill = units * 0.218;
        } else if (units <= 300) {
            bill = (200 * 0.218) + ((units - 200) * 0.334);
        } else if (units <= 600) {
            bill = (200 * 0.218) + (100 * 0.334) + ((units - 300) * 0.516);
        } else {
            bill = (200 * 0.218) + (100 * 0.334) + (300 * 0.516) + ((units - 600) * 0.546);
        }

        return bill;
    }
}
