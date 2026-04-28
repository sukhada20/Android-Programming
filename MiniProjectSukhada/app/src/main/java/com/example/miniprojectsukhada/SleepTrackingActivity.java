package com.example.miniprojectsukhada;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class SleepTrackingActivity extends AppCompatActivity {

    private long startTime;
    private Button btnStart, btnStop;
    private TextView tvStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sleep_tracking);

        btnStart = findViewById(R.id.btnStart);
        btnStop = findViewById(R.id.btnStop);
        tvStatus = findViewById(R.id.tvStatus);

        btnStart.setOnClickListener(v -> {
            startTime = System.currentTimeMillis();
            btnStart.setVisibility(View.GONE);
            btnStop.setVisibility(View.VISIBLE);
            tvStatus.setText("Tracking Sleep...");
        });

        btnStop.setOnClickListener(v -> {
            long endTime = System.currentTimeMillis();
            long duration = endTime - startTime;
            
            Intent intent = new Intent(this, SleepResultActivity.class);
            intent.putExtra("DURATION", duration);
            startActivity(intent);
            finish();
        });
    }
}