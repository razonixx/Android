package com.example.carloscabello;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter {

    private ArrayList<Pet> source;
    private Activity activity;

    public CustomAdapter(ArrayList<Pet> source, Activity activity){
        this.source = source;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return source.size();
    }

    @Override
    public Object getItem(int position) {
        return source.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // view - the actual specific row
        if(convertView == null){
            // inflate - from xml to java object
            convertView = activity.getLayoutInflater().inflate(R.layout.row, null);
        }

        TextView name = convertView.findViewById(R.id.textView2);
        TextView age = convertView.findViewById(R.id.textView3);
        TextView weight = convertView.findViewById(R.id.textView4);

        Pet thePet = source.get(position);
        name.setText(thePet.getName());
        age.setText(thePet.getAge() + "");
        weight.setText(thePet.getWeight() + "");

        return convertView;
    }
}