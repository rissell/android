package com.tuesday.class5;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by gdaalumno on 2/9/16.
 */
public class JSONAdapter extends BaseAdapter {

    JSONArray array;
    Activity activity;

    public JSONAdapter(JSONArray array, Activity activity){

        this.array = array;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return array.length();
    }

    @Override
    public Object getItem(int position) {

        JSONObject result = null;

        try{

            result = array.getJSONObject(position);

        }catch(JSONException jse){

            jse.printStackTrace();
        }
        return result;
    }

    @Override
    public long getItemId(int position) {


        long id = -1;

        try{

            JSONObject result = array.getJSONObject(position);
            id = result.getLong("id");

        }catch(JSONException jse){

            jse.printStackTrace();
        }
        return id;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){

            convertView = activity.getLayoutInflater().inflate(R.layout.json_row, null);
        }

        TextView t1 = (TextView)convertView.findViewById(R.id.textView4);
        TextView t2 = (TextView)convertView.findViewById(R.id.textView5);
        TextView t3 = (TextView)convertView.findViewById(R.id.textView6);
        TextView t4 = (TextView)convertView.findViewById(R.id.textView7);
        ImageView image = (ImageView)convertView.findViewById(R.id.imageView);


        try{

            JSONObject json = array.getJSONObject(position);
            t1.setText(json.getString("userId"));
            t2.setText(json.getString("id"));
            t3.setText(json.getString("title"));
            t4.setText(json.getString("body"));

            // setting image programmatically
            image.setImageResource(R.drawable.pizza);

        }catch (JSONException jse){

            jse.printStackTrace();
        }

        return convertView;
    }
}
