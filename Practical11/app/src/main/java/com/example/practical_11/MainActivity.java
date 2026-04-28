package com.example.practical_11;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper myDb;
    EditText editName, editMarks, editId;
    Button btnAdd, btnView, btnUpdate, btnDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDb = new DatabaseHelper(this);

        editName = findViewById(R.id.editName);
        editMarks = findViewById(R.id.editMarks);
        editId = findViewById(R.id.editId);

        btnAdd = findViewById(R.id.btnAdd);
        btnView = findViewById(R.id.btnView);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);

        // Add Data
        btnAdd.setOnClickListener(v -> {
            boolean isInserted = myDb.insertData(
                    editName.getText().toString(),
                    editMarks.getText().toString()
            );

            Toast.makeText(this,
                    isInserted ? "Data Inserted" : "Failed",
                    Toast.LENGTH_SHORT).show();
        });

        // View Data
        btnView.setOnClickListener(v -> {
            Cursor res = myDb.getAllData();

            if (res.getCount() == 0) {
                showMessage("Error", "No Data Found");
                return;
            }

            StringBuilder buffer = new StringBuilder();
            while (res.moveToNext()) {
                buffer.append("ID: ").append(res.getString(0)).append("\n");
                buffer.append("Name: ").append(res.getString(1)).append("\n");
                buffer.append("Marks: ").append(res.getString(2)).append("\n\n");
            }

            showMessage("Data", buffer.toString());
        });

        // Update
        btnUpdate.setOnClickListener(v -> {
            boolean isUpdated = myDb.updateData(
                    editId.getText().toString(),
                    editName.getText().toString(),
                    editMarks.getText().toString()
            );

            Toast.makeText(this,
                    isUpdated ? "Updated" : "Failed",
                    Toast.LENGTH_SHORT).show();
        });

        // Delete
        btnDelete.setOnClickListener(v -> {
            int deletedRows = myDb.deleteData(editId.getText().toString());
            Toast.makeText(this,
                    deletedRows > 0 ? "Deleted" : "Not Found",
                    Toast.LENGTH_SHORT).show();
        });
    }

    public void showMessage(String title, String message) {
        new android.app.AlertDialog.Builder(this)
                .setTitle(title)
                .setMessage(message)
                .show();
    }
}
