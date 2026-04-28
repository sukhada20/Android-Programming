package com.example.practical7;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
public class MainActivity extends AppCompatActivity {
    EditText editName;
    CheckBox checkJava, checkAndroid;
    RadioGroup radioGroup;
    Button btnSubmit;
    TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        editName = findViewById(R.id.editName);
        checkJava = findViewById(R.id.checkJava);
        checkAndroid = findViewById(R.id.checkAndroid);
        radioGroup = findViewById(R.id.radioGroup);
        btnSubmit = findViewById(R.id.btnSubmit);
        tvResult = findViewById(R.id.tvResults);


        btnSubmit.setOnClickListener(view -> {

            String name = editName.getText().toString();

            StringBuilder skills = new StringBuilder();
            if (checkJava.isChecked())
                skills.append("Java ");

            if (checkAndroid.isChecked())
                skills.append("Android");

            int selectedId = radioGroup.getCheckedRadioButtonId();
            String gender = "Not Selected";
            if (selectedId != -1) {
                RadioButton radioButton = findViewById(selectedId);
                gender = radioButton.getText().toString();
            }

            String result = "Name: " + name +
                    "\nSkills: " + skills.toString().trim() +
                    "\nGender: " + gender;
            tvResult.setText(result);
        });
    }
}
