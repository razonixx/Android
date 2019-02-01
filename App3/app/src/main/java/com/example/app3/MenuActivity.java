package com.example.app3;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class MenuActivity extends AppCompatActivity {

    private static final int HOBBY_CODE  = 0x0;
    private static final int FRIEND_CODE = 0x1;
    private static final int MSG_CODE    = 0x2;


    private TextView tv1, tv2;
    private ImageButton imageButton1, imageButton2, imageButton3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Intent intent = getIntent();

        tv1 = findViewById(R.id.textView);
        tv1.setText("Hello, " + intent.getStringExtra("userName"));

        tv2 = findViewById(R.id.textView3);

        imageButton1 = findViewById(R.id.imageButton);
        imageButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), HobbyActivity.class);
                startActivityForResult(intent, HOBBY_CODE);
            }
        });

        imageButton2 = findViewById(R.id.imageButton2);
        imageButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), FriendsActivity.class);
                startActivity(intent);
            }
        });

        imageButton3 = findViewById(R.id.imageButton3);
        imageButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MessageActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case HOBBY_CODE:
                if(resultCode == Activity.RESULT_OK){
                    tv2.setText("I see, your hobby is " + data.getStringExtra("hobby"));
                }
            break;

            case FRIEND_CODE:
            break;

            case MSG_CODE:
            break;
        }
    }
}
