package mx.itesm.carloscabelloparcial2;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity implements JSONArrayFragment.RequestListener{

    public Button button2, button4;
    public TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button2 = findViewById(R.id.button2);
        button4 = findViewById(R.id.button4);
        tv = findViewById(R.id.textView2);

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentManager manager = getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                Fragment f = manager.findFragmentByTag("doggyFragment");
                Fragment f2 = manager.findFragmentByTag("jsonArrayFragment");

                if(f != null){
                    transaction.remove(f);
                }
                if(f2 != null){
                    transaction.remove(f2);
                }

                JSONArrayFragment jsonArrayFragment = new JSONArrayFragment();
                transaction.add(R.id.container, jsonArrayFragment, "jsonArrayFragment");
                transaction.commit();

            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager = getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                Fragment f = manager.findFragmentByTag("doggyFragment");
                Fragment f2 = manager.findFragmentByTag("jsonArrayFragment");

                if(f != null){
                    transaction.remove(f);
                }
                if(f2 != null){
                    transaction.remove(f2);
                }

                DoggyFragment doggyFragment = new DoggyFragment();
                transaction.add(R.id.container, doggyFragment, "doggyFragment");
                transaction.commit();
            }
        });

    }

    @Override
    public void requestDone(String text) {
        tv.setText(text);
    }
}
