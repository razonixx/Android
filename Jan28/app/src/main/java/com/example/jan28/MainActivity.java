package com.example.jan28;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.InvalidPropertiesFormatException;
import java.util.Properties;

public class MainActivity extends AppCompatActivity {

    //Local storage
    //local database sqlite
    //files
    //preferences

    private Button bAdd;
    private Button bFind;
    private Button bDelete;
    private Button bMemory;
    private Button bFile;

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
        bMemory = findViewById(R.id.button4);
        bFile = findViewById(R.id.button5);
        editTextID = findViewById(R.id.editText);
        editTextName = findViewById(R.id.editText2);
        editTextGrade = findViewById(R.id.editText3);
        textView = findViewById(R.id.textView);

        properties = new Properties();

        File file = new File(getFilesDir(), PROPERTIES_FILE);

        if(file.exists()){
            Toast.makeText(this, "LOADING FILE", Toast.LENGTH_SHORT).show();
            try {
                FileInputStream fis = openFileInput(PROPERTIES_FILE);
                properties.loadFromXML(fis);
                fis.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (InvalidPropertiesFormatException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            Toast.makeText(this, "CREATING FILE", Toast.LENGTH_SHORT).show();
            saveProperties();
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

        bMemory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                properties.put(editTextName.getText().toString(), editTextGrade.getText().toString());
                Toast.makeText(getApplicationContext(), "Saving to memory...", Toast.LENGTH_SHORT).show();
            }
        });

        bFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Save what's on memory to file
                saveProperties();
                Toast.makeText(getApplicationContext(), "Saving to file...", Toast.LENGTH_SHORT).show();
            }
        });

    }
    private void saveProperties(){
        try {
            FileOutputStream fos = openFileOutput(PROPERTIES_FILE, Context.MODE_PRIVATE);
            properties.storeToXML(fos, null);
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void printProperty(View v){
        Toast.makeText(getApplicationContext(), "Property Key: " +editTextName.getText().toString() + "\nProperty Value: " + properties.get(editTextName.getText().toString()), Toast.LENGTH_SHORT).show();
    }
    public void changeActivity(View v){
        Intent intent = new Intent(getApplicationContext(), SharedPrefsActivity.class);
        startActivity(intent);
    }
}
