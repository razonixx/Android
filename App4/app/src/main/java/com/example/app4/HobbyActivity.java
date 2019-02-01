package com.example.app4;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class HobbyActivity extends AppCompatActivity {

    private Button b1;
    private EditText ed1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hobby);

        b1 = findViewById(R.id.button2);
        ed1 = findViewById(R.id.editText2);

        Intent intent = getIntent();
        final DBHelper db = new DBHelper(getApplicationContext());

        final String rootName = intent.getStringExtra("name");

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                String hobby = ed1.getText().toString();
                Toast.makeText(getApplicationContext(), "Name: " + rootName + "\nHobby: " + hobby, Toast.LENGTH_SHORT).show();
                if(!TextUtils.isEmpty(hobby)) {
                    db.add(rootName, hobby);
                    intent.putExtra("hobby", hobby);
                    setResult(Activity.RESULT_OK, intent);
                    finish();
                }
                else{
                    Toast.makeText(getApplicationContext(), "Please enter your hobby", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
