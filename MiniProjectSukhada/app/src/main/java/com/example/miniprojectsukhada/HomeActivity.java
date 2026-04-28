package com.example.miniprojectsukhada;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.Button;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        findViewById(R.id.btnStartSleep).setOnClickListener(v -> 
            startActivity(new Intent(this, SleepTrackingActivity.class)));
            
        findViewById(R.id.btnSleepHistory).setOnClickListener(v -> 
            startActivity(new Intent(this, SleepHistoryActivity.class)));
            
        findViewById(R.id.btnStatistics).setOnClickListener(v -> 
            startActivity(new Intent(this, StatisticsActivity.class)));
            
        findViewById(R.id.btnProfile).setOnClickListener(v -> 
            startActivity(new Intent(this, ProfileActivity.class)));
    }
}