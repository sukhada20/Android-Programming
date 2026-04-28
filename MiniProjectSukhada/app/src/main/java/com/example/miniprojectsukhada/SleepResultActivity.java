package com.example.miniprojectsukhada;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.miniprojectsukhada.models.SleepRecord;
import com.example.miniprojectsukhada.utils.DataManager;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class SleepResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sleep_result);

        long duration = getIntent().getLongExtra("DURATION", 0);
        
        long hours = duration / (1000 * 60 * 60);
        String quality;
        if (hours > 7) quality = "Good";
        else if (hours >= 5) quality = "Average";
        else quality = "Poor";

        TextView tvDuration = findViewById(R.id.tvDurationResult);
        TextView tvQuality = findViewById(R.id.tvQualityResult);
        
        long minutes = (duration % (1000 * 60 * 60)) / (1000 * 60);
        tvDuration.setText(String.format("Duration: %d hrs %d min", hours, minutes));
        tvQuality.setText("Quality: " + quality);

        findViewById(R.id.btnSave).setOnClickListener(v -> {
            String date = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
            DataManager.records.add(new SleepRecord(date, duration, quality));
            finish();
        });
    }
}