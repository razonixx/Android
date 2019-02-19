package com.example.feb14;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, AdapterView.OnItemSelectedListener{

    private ListView list;
    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Widgets deal with sets / groups
        //3 parts:
        // - data source: Raw Information (DB, JSON)
        // - Adapter: Translate data source into UI Widget
        // - UI Widget: Display information to user

        list = findViewById(R.id.listView);
        spinner = findViewById(R.id.spinner);

        //data source
        String[] data = {"Student 1", "Student 2", "Student 3"};

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, data);

        //Use our custom adapter

        ArrayList<Student> students = new ArrayList<>();

        students.add(new Student("Carlos", 100.0f, 100));
        students.add(new Student("Emanuel", 0.5f, 200));
        students.add(new Student("Humberto", 80.1f, 300));
        students.add(new Student("Rosa", 25.5f, 400));
        students.add(new Student("Quirino", 12.4f, 500));

        CustomAdapter customAdapter = new CustomAdapter(students, this);
        list.setAdapter(customAdapter);
        list.setOnItemClickListener(this);
        spinner.setAdapter(customAdapter);
        list.setOnItemClickListener(this);
        spinner.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(getApplicationContext(), "position: " + position + " id: " + id, Toast.LENGTH_SHORT).show();
    }

    /**
     * <p>Callback method to be invoked when an item in this view has been
     * selected. This callback is invoked only when the newly selected
     * position is different from the previously selected position or if
     * there was no selected item.</p>
     * <p>
     * Implementers can call getItemAtPosition(position) if they need to access the
     * data associated with the selected item.
     *
     * @param parent   The AdapterView where the selection happened
     * @param view     The view within the AdapterView that was clicked
     * @param position The position of the view in the adapter
     * @param id       The row id of the item that is selected
     */
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(getApplicationContext(), "position: " + position + " id: " + id, Toast.LENGTH_SHORT).show();
    }

    /**
     * Callback method to be invoked when the selection disappears from this
     * view. The selection can disappear for instance when touch is activated
     * or when the adapter becomes empty.
     *
     * @param parent The AdapterView that now contains no selected item.
     */
    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
