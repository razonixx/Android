package com.example.jan21;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class PerritoActivity extends AppCompatActivity {

    private Button b1;
    private TextView tv1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perrito);

        Intent intent = getIntent();

        tv1 = findViewById(R.id.textView4);
        tv1.setText(intent.getStringExtra("userName") + ": " + intent.getIntExtra("age", -1));
        intent.putExtra("A", "HOla");

        b1 = findViewById(R.id.button3);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Go back sending information
                //use intents
                Intent intent = new Intent();
                intent.putExtra("returnValue", "going back");
                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        });
    }
}
