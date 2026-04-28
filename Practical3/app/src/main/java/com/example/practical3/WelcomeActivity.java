package com.example.practical3;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_welcome);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        TextView tvWelcome = findViewById(R.id.tvWelcome);
        //Receive username from the intent
        String username = getIntent().getStringExtra("username");
        //Display welcome message with the username
        tvWelcome.setText("Welcome, " + username + "!");

        findViewById(R.id.btnStartService).setOnClickListener(v -> {
            Intent intent = new Intent(this, TimerService.class);
            startService(intent);
        });

        findViewById(R.id.btnStopService).setOnClickListener(v -> {
            Intent intent = new Intent(this, TimerService.class);
            stopService(intent);
        });
    }
}
