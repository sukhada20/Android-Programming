package com.example.practical8;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    Button btnDialog, btnDate, btnTime;
    TextView txtResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnDialog = findViewById(R.id.btnDialog);
        btnDate = findViewById(R.id.btnDate);
        btnTime = findViewById(R.id.btnTime);
        txtResult = findViewById(R.id.txtResult);

        // Alert Dialog
        btnDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Confirmation");
                builder.setMessage("Do you want to continue?");

                builder.setPositiveButton("Yes", (dialog, which) -> {
                    Toast.makeText(MainActivity.this,"You clicked YES",Toast.LENGTH_SHORT).show();
                });

                builder.setNegativeButton("No", (dialog, which) -> {
                    Toast.makeText(MainActivity.this,"You clicked NO",Toast.LENGTH_SHORT).show();
                });

                builder.show();
            }
        });

        // Date Picker
        btnDate.setOnClickListener(view -> {

            Calendar calendar = Calendar.getInstance();

            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(
                    MainActivity.this,
                    (view1, year1, month1, dayOfMonth) -> {
                        txtResult.setText("Selected Date: " + dayOfMonth + "/" + (month1+1) + "/" + year1);
                    },
                    year, month, day
            );

            datePickerDialog.show();
        });

        // Time Picker
        btnTime.setOnClickListener(view -> {

            Calendar calendar = Calendar.getInstance();

            int hour = calendar.get(Calendar.HOUR_OF_DAY);
            int minute = calendar.get(Calendar.MINUTE);

            TimePickerDialog timePickerDialog = new TimePickerDialog(
                    MainActivity.this,
                    (view12, hourOfDay, minute1) -> {
                        txtResult.setText("Selected Time: " + hourOfDay + ":" + minute1);
                    },
                    hour,
                    minute,
                    true
            );

            timePickerDialog.show();
        });

    }
}
