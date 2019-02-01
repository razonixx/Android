package com.example.jan28;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final DBHelper db = new DBHelper(getApplicationContext());
        bAdd = findViewById(R.id.button);
        bFind = findViewById(R.id.button2);
        bDelete = findViewById(R.id.button3);
        editTextID = findViewById(R.id.editText);
        editTextName = findViewById(R.id.editText2);
        editTextGrade = findViewById(R.id.editText3);
        textView = findViewById(R.id.textView);

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
