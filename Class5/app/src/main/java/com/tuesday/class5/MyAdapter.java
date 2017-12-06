package com.tuesday.class5;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by gdaalumno on 2/9/16.
 */
public class MyAdapter extends BaseAdapter {

    // adapter needs a structure to translate to GUI
    private ArrayList<Student> students;
    private Activity activity;

    public MyAdapter(Activity activity, ArrayList<Student> students){
        this.students = students;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return students.size();
    }

    @Override
    public Object getItem(int position) {
        return students.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null) {

            convertView = activity.getLayoutInflater().inflate(R.layout.row, null);
        }

        TextView text1 = (TextView)convertView.findViewById(R.id.textView);
        TextView text2 = (TextView)convertView.findViewById(R.id.textView2);
        TextView text3 = (TextView)convertView.findViewById(R.id.textView3);

        Student s = students.get(position);
        text1.setText(s.getName());
        text2.setText(s.getMajor());
        text3.setText(s.getGrade() + "");

        return convertView;
    }
}
