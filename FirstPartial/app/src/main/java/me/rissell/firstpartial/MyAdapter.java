package me.rissell.firstpartial;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by gdaalumno on 2/5/16.
 */
public class MyAdapter extends BaseAdapter {

    // container we need to adapt
    ArrayList<sport> sports;

    // to attach interface we need a reference to an activity
    Activity activity;

    public MyAdapter(ArrayList<sport> sports, Activity activity){

        this.sports = sports;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return sports.size();
    }

    @Override
    public Object getItem(int position) {
        return sports.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    // this is where we actually build the row UI
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){

            convertView = activity.getLayoutInflater().inflate(R.layout.row, null);
        }

        TextView name = (TextView) convertView.findViewById(R.id.textView);
        TextView country = (TextView) convertView.findViewById(R.id.textView2);

        name.setText(sports.get(position).getName());
        country.setText(sports.get(position).getCountry() + "");

        return convertView;
    }

}
