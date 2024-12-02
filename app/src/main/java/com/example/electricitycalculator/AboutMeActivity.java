package com.example.electricitycalculator;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class AboutMeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_me);

        // Button to redirect to Main Page
        Button btnMainPage = findViewById(R.id.btnMainPage);
        btnMainPage.setOnClickListener(v -> {
            Intent intent = new Intent(AboutMeActivity.this, MainActivity.class);
            startActivity(intent);
        });

        // Button to redirect to GitHub
        Button btnGithub = findViewById(R.id.btnGithub);
        btnGithub.setOnClickListener(v -> {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/rossara"));
            startActivity(browserIntent);
        });
    }
}
