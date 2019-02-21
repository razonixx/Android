package com.example.carloscabello;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class DataInputActivity extends AppCompatActivity {

    private EditText et1, et2;
    private Button b1, b2;

    private Properties properties;
    private static final String PROPERTIES_FILE = "properties.xml";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_input);

        et1 = findViewById(R.id.editText);
        et2 = findViewById(R.id.editText2);
        b1 = findViewById(R.id.button3);
        b2 = findViewById(R.id.button4);

        properties = new Properties();

       // File file = new File(getFilesDir(), PROPERTIES_FILE);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                if(!TextUtils.isEmpty(et1.getText().toString())) {
                    properties.put("hello", et1.getText().toString());
                    saveProperties();
                    setResult(10, intent);
                    finish();
                }
                else{
                    Toast.makeText(getApplicationContext(), "Please  enter a string", Toast.LENGTH_SHORT).show();
                }
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                String returnText = et2.getText().toString();
                if(!TextUtils.isEmpty(returnText)) {
                    intent.putExtra("toastMsg", returnText);
                    setResult(Activity.RESULT_OK, intent);
                    finish();
                }
                else{
                    Toast.makeText(getApplicationContext(), "Please  enter a string", Toast.LENGTH_SHORT).show();
                }

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
