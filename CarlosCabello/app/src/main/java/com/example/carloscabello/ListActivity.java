package com.example.carloscabello;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity implements
        AdapterView.OnItemClickListener, AdapterView.OnItemSelectedListener  {

    private ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);



        list = findViewById(R.id.listView);


        ArrayList<Pet> pets = new ArrayList<>();

        pets.add(new Pet("Julito", 4, 11.2f));
        pets.add(new Pet("Tomas", 8, 21.2f));
        pets.add(new Pet("Hades", 1, 1.2f));

        CustomAdapter customAdapter = new CustomAdapter(pets, this);

        list.setAdapter(customAdapter);
        list.setOnItemClickListener(this);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(this, "position: " + position + " id: " + id, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(this, "position: " + position + " id: " + id, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
