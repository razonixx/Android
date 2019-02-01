package com.example.jan21;

import android.app.Activity;
import android.content.Intent;
import android.os.strictmode.WebViewMethodCalledOnWrongThreadViolation;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //Variables to keep reference
    private TextView textito;
    private EditText input;
    private Button b1, b2;
    private static final int PERRITO_CODE = 0x0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Give reference of the interface to variables
        textito = findViewById(R.id.textView);
        input = findViewById(R.id.editText);
        b1 = findViewById(R.id.button);
        b2 = findViewById(R.id.button2);

        textito.setText("Hello world from code");

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //intent - A request to open an activity

                Intent intent = new Intent(getApplicationContext(), PerritoActivity.class);
                intent.putExtra("userName", input.getText().toString());
                intent.putExtra("age", 21);

                //startActivity(intent);
                startActivityForResult(intent, PERRITO_CODE);


                //Toast.makeText(v.getContext(), "Text from code", Toast.LENGTH_SHORT).show();
            }
        });
    }

    //Listen to click
    //2 ways to do it
    // 1: Add listener through code
    // 2: Add listener through XML

    //Link through XML
    //Need a signature that returns void and receives a View
    public void buttonTestXML(View v){
        //Show toast using factory method
        //Toast.makeText(getApplicationContext(), "Max es gay", Toast.LENGTH_SHORT).show();
        Toast.makeText(this, input.getText().toString(), Toast.LENGTH_SHORT).show();

        //Log
        Log.i("buttonTest", "Log logged through buttonTestXML as info");
        Log.e("buttonTest", "Log logged through buttonTestXML as error");

        //intent - A request to open an activity
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == PERRITO_CODE)
        {
            Toast.makeText(this, "Returned from activity", Toast.LENGTH_SHORT).show();
            if(resultCode == Activity.RESULT_OK)
            {
                Toast.makeText(this, "Returned from activity: " + data.getStringExtra("returnValue"), Toast.LENGTH_SHORT).show();
            }
        }
    }
}
