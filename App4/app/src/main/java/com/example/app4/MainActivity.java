package com.example.app4;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

    private static final int MENU_CODE = 0x0;

    private Properties properties;
    private static final String PROPERTIES_FILE = "properties.xml";

    private TextView tv;
    private TextView tv2;
    private EditText editText;
    private Button b1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editText);
        tv = findViewById(R.id.textView);
        tv2 = findViewById(R.id.textView2);
        b1 = findViewById(R.id.button);

        properties = new Properties();

        File file = new File(getFilesDir(), PROPERTIES_FILE);

        if(file.exists()){
            Toast.makeText(this, "LOADING PROPERTIES FILE", Toast.LENGTH_SHORT).show();
            try {
                FileInputStream fis = openFileInput(PROPERTIES_FILE);
                properties.loadFromXML(fis);
                editText.setText(properties.getProperty("userName"));
                tv.setText(properties.getProperty("greet1"));
                tv2.setText(properties.getProperty("greet2"));
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            Toast.makeText(this, "CREATING PROPERTIES FILE", Toast.LENGTH_SHORT).show();
            properties.put("greet1", "Hello World!");
            properties.put("greet2", "Welcome to my app.");
            saveProperties();
            tv.setText(properties.getProperty("greet1"));
            tv2.setText(properties.getProperty("greet2"));
        }

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
                intent.putExtra("userName", editText.getText().toString());
                properties.put("userName", editText.getText().toString());
                saveProperties();
                startActivityForResult(intent, MENU_CODE);
            }
        });
    }

    private void saveProperties(){
        try {
            FileOutputStream fos = openFileOutput(PROPERTIES_FILE, Context.MODE_PRIVATE);
            properties.storeToXML(fos, null);
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
