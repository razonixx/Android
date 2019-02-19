package com.example.jan28;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class SharedPrefsActivity extends AppCompatActivity {

    private static final String PREFS_FILE = "Jan28";

    private SharedPreferences prefs;
    private EditText valueText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_prefs);

        valueText = findViewById(R.id.valueText);
    }

    public void loadPrefs(View v){
        prefs = getSharedPreferences(PREFS_FILE, Context.MODE_PRIVATE);
        Toast.makeText(getApplicationContext(), "Loading Shared Preferences", Toast.LENGTH_SHORT).show();

    }
    public void savePrefs(View v){
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("valueText", valueText.getText().toString());
        //In order to actually save values to storage, do commit
        editor.commit();
        Toast.makeText(getApplicationContext(), "Value Saved", Toast.LENGTH_SHORT).show();
    }
    public void printValue(View v){
        Toast.makeText(getApplicationContext(), prefs.getString("valueText", "NO VALUE ERR"), Toast.LENGTH_SHORT).show();
    }
    public void deleteValue(View v){
        SharedPreferences.Editor editor = prefs.edit();
        editor.remove("valueText");
        editor.commit();
        Toast.makeText(getApplicationContext(), "Value Removed", Toast.LENGTH_SHORT).show();
    }
    public void deleteEverything(View v){
        SharedPreferences.Editor editor = prefs.edit();
        editor.clear();
        editor.commit();
        Toast.makeText(getApplicationContext(), "Everything was removed", Toast.LENGTH_SHORT).show();
    }
}
