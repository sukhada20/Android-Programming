package com.example.miniprojectsukhada;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.miniprojectsukhada.models.SleepRecord;
import com.example.miniprojectsukhada.utils.DataManager;

public class StatisticsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);

        int total = DataManager.records.size();
        long totalDuration = 0;
        for (SleepRecord r : DataManager.records) {
            totalDuration += r.duration;
        }

        long avgMillis = total > 0 ? totalDuration / total : 0;
        long hours = avgMillis / (1000 * 60 * 60);
        long minutes = (avgMillis % (1000 * 60 * 60)) / (1000 * 60);

        ((TextView) findViewById(R.id.tvTotalRecords)).setText("Total Records: " + total);
        ((TextView) findViewById(R.id.tvAvgSleep)).setText(String.format("Average Sleep: %d hrs %d min", hours, minutes));
    }
}