package com.example.jan28;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.util.Properties;

public class MainActivity extends AppCompatActivity {

    //Local storage
    //local database sqlite
    //files
    //preferences

    private Button bAdd;
    private Button bFind;
    private Button bDelete;

    private EditText editTextID;
    private EditText editTextName;
    private EditText editTextGrade;

    private TextView textView;

    private DBHelper db;

    //Properties
    //For java applications
    //A way to save key-value sets on local storage

    private Properties properties;
    private static final String PROPERTIES_FILE = "properties.xml";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = new DBHelper(getApplicationContext());
        bAdd = findViewById(R.id.button);
        bFind = findViewById(R.id.button2);
        bDelete = findViewById(R.id.button3);
        editTextID = findViewById(R.id.editText);
        editTextName = findViewById(R.id.editText2);
        editTextGrade = findViewById(R.id.editText3);
        textView = findViewById(R.id.textView);

        properties = new Properties();

        File file = new File(getFilesDir(), PROPERTIES_FILE);

        if(file.exists()){

        } else{

        }

        bAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.add(editTextName.getText().toString(), Integer.parseInt(editTextGrade.getText().toString()));
            }
        });

        bFind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String found = db.find(editTextName.getText().toString());
                //Toast.makeText(getApplicationContext(), found, Toast.LENGTH_SHORT).show();
                textView.setText(found);
                if(TextUtils.isEmpty(textView.getText().toString()))
                {
                    textView.setText("Entry " + editTextName.getText().toString() + ": not found.");
                }
            }
        });

        bDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int target = db.delete(Integer.parseInt(editTextID.getText().toString()));
                if(target > 0){
                    Toast.makeText(getApplicationContext(), "Student with id:" + editTextID.getText().toString() + "deleted.", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
