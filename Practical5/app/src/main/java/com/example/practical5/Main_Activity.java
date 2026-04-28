package com.example.practical5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Main_Activity extends AppCompatActivity {

    EditText editText;
    Button btnInsert, btnShow;

    // Updated authority to match AndroidManifest.xml
    Uri uri = Uri.parse("content://com.example.practical5.provider");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editText);
        btnInsert = findViewById(R.id.btnInsert);
        btnShow = findViewById(R.id.btnShow);

        btnInsert.setOnClickListener(v -> {
            ContentValues values = new ContentValues();
            values.put("name", editText.getText().toString());

            try {
                getContentResolver().insert(uri, values);
                Toast.makeText(this, "Data Inserted", Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                Toast.makeText(this, "Insert Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        btnShow.setOnClickListener(v -> {
            try (Cursor cursor = getContentResolver().query(uri, null, null, null, null)) {
                if (cursor != null) {
                    StringBuilder result = new StringBuilder();
                    while (cursor.moveToNext()) {
                        result.append(cursor.getString(0)).append("\n");
                    }
                    if (result.length() == 0) {
                        Toast.makeText(this, "No data found", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(this, result.toString(), Toast.LENGTH_LONG).show();
                    }
                }
            } catch (Exception e) {
                Toast.makeText(this, "Query Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
