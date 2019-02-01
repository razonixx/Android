package com.example.app4;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.TextView;
        import android.widget.Toast;

public class FriendsActivity extends AppCompatActivity {

    //Local storage
    //local database sqlite
    //files
    //preferences

    private Button bAdd;
    private Button bFind;
    private Button bDelete;
    private Button bBack;

    private EditText editTextName;
    private EditText editTextHobby;

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends);
        final DBHelper db = new DBHelper(getApplicationContext());
        bAdd = findViewById(R.id.button);
        bFind = findViewById(R.id.button2);
        bDelete = findViewById(R.id.button3);
        bBack = findViewById(R.id.button4);
        editTextName = findViewById(R.id.editText2);
        editTextHobby = findViewById(R.id.editText3);
        textView = findViewById(R.id.textView);

        bAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.add(editTextName.getText().toString(), editTextHobby.getText().toString());
                Toast.makeText(getApplicationContext(), editTextName.getText().toString() + " was added.", Toast.LENGTH_SHORT).show();
            }
        });

        bFind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String found = db.find(editTextName.getText().toString());
                //Toast.makeText(getApplicationContext(), found, Toast.LENGTH_SHORT).show();
                textView.setText(found);
                if(TextUtils.isEmpty(textView.getText().toString())){
                    Toast.makeText(getApplicationContext(), editTextName.getText().toString() + " was not found.", Toast.LENGTH_SHORT).show();
                }

                Intent intent = new Intent();
                if(!TextUtils.isEmpty(textView.getText().toString())) {
                    intent.putExtra("hobby",db.findHobby(editTextName.getText().toString()));
                    setResult(Activity.RESULT_OK, intent);
                    //finish();
                }
            }
        });

        bDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int target = db.delete(editTextName.getText().toString());
                if(target > 0){
                    Toast.makeText(getApplicationContext(), editTextName.getText().toString() + " was deleted.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        bBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}

