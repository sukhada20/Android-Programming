package com.example.practical12156;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    DBHelper db;
    EditText editName;
    Button btnInsert, btnView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new DBHelper(this);

        editName = findViewById(R.id.editName);
        btnInsert = findViewById(R.id.btnInsert);
        btnView = findViewById(R.id.btnView);

        // Insert Data
        btnInsert.setOnClickListener(v -> {
            String name = editName.getText().toString();
            if (name.isEmpty()) {
                Toast.makeText(this, "Please enter a name", Toast.LENGTH_SHORT).show();
                return;
            }

            boolean isInserted = db.insertData(name);
            if (isInserted) {
                Toast.makeText(this, "Data Inserted", Toast.LENGTH_SHORT).show();
                editName.setText("");
            } else {
                Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
            }
        });

        // View Data
        btnView.setOnClickListener(v -> {
            Cursor cursor = db.getData();

            if (cursor.getCount() == 0) {
                Toast.makeText(this, "No Data Found", Toast.LENGTH_SHORT).show();
                return;
            }

            StringBuilder buffer = new StringBuilder();
            while (cursor.moveToNext()) {
                buffer.append("ID: ").append(cursor.getInt(0)).append("\n");
                buffer.append("Name: ").append(cursor.getString(1)).append("\n\n");
            }

            // Using AlertDialog instead of Toast to show all records clearly
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setCancelable(true);
            builder.setTitle("Student Records");
            builder.setMessage(buffer.toString());
            builder.setPositiveButton("OK", null);
            builder.show();
        });
    }
}
