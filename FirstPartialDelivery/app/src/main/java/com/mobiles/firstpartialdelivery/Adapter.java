package com.mobiles.firstpartialdelivery;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.firebase.client.ValueEventListener;

import java.util.ArrayList;

/**
 * Created by Kimberly on 13/02/2016.
 */
public class Adapter extends BaseAdapter {

    // adapter needs a structure to translate to GUI
    private ArrayList<Note> archive;
    private Activity activity;

    public Adapter(Activity activity, ArrayList<Note> a) {
        this.archive = a;
        this.activity = activity;
    }



    @Override
    public int getCount() {
        return archive.size();
    }

    @Override
    public Object getItem(int position) {
        return archive.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {

            convertView = activity.getLayoutInflater().inflate(R.layout.notelist, null);
        }

        TextView text1 = (TextView) convertView.findViewById(R.id.textView4);
        TextView text2 = (TextView) convertView.findViewById(R.id.textView5);

        Note n = archive.get(position);
        text1.setText(n.getHeader());
        text2.setText(n.getContent());

        return convertView;
    }
}