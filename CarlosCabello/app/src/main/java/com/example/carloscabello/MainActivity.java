package com.example.carloscabello;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class MainActivity extends AppCompatActivity {

    private static final int DATA_INPUT_CODE = 0x0;

    private TextView tv;
    private Button b1, b2;
    private Properties properties;
    private static final String PROPERTIES_FILE = "properties.xml";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        properties = new Properties();

        File file = new File(getFilesDir(), PROPERTIES_FILE);

        tv = findViewById(R.id.textView);
        b1 = findViewById(R.id.button);
        b2 = findViewById(R.id.button2);

        if(file.exists()){
            Toast.makeText(this, "LOADING PROPERTIES FILE", Toast.LENGTH_SHORT).show();
            try {
                properties.put("hello", "Hello World!");
                saveProperties();
                FileInputStream fis = openFileInput(PROPERTIES_FILE);
                properties.loadFromXML(fis);
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            Toast.makeText(this, "CREATING PROPERTIES FILE", Toast.LENGTH_SHORT).show();
            properties.put("hello", "Hello World!");
            saveProperties();
        }
        tv.setText(properties.getProperty("hello"));

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), DataInputActivity.class);
                startActivityForResult(intent, DATA_INPUT_CODE);
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ListActivity.class);
                startActivity(intent);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case DATA_INPUT_CODE:
                if (resultCode == Activity.RESULT_OK) {
                    Toast.makeText(getApplicationContext(), data.getStringExtra("toastMsg"), Toast.LENGTH_LONG).show();
                }
                if(resultCode == 10){
                    try {
                        FileInputStream fis = openFileInput(PROPERTIES_FILE);
                        properties.loadFromXML(fis);
                        fis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    //saveProperties();
                    tv.setText(properties.getProperty("hello"));
                }
            break;
        }
    }
}
