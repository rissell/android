package me.rissell.firstpartial;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, AdapterView.OnItemSelectedListener{

    ListView listView;
    ArrayList<sport> sportList;
    int n=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView)findViewById(R.id.listView);

        sportList = new ArrayList<sport>();

        InputStream is = getResources().openRawResource(R.raw.sports);
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);

        try {
            sportList.add(new sport(br.readLine(), br.readLine(), br.readLine()));
            sportList.add(new sport(br.readLine(), br.readLine(), br.readLine()));
            sportList.add(new sport(br.readLine(), br.readLine(), br.readLine()));
            sportList.add(new sport(br.readLine(), br.readLine(), br.readLine()));
            sportList.add(new sport(br.readLine(), br.readLine(), br.readLine()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        MyAdapter newAdapter = new MyAdapter(sportList, this);

        listView.setAdapter(newAdapter);
        listView.setOnItemClickListener(this);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Log.d("Pos: ", position + "");

        Intent i = new Intent(this, DetailActivity.class);
        i.putExtra("name", sportList.get(position).getName().toString());
        i.putExtra("country", sportList.get(position).getCountry().toString());
        startActivityForResult(i, 0);

        //Toast.makeText(getApplicationContext(), "See Details: ", Toast.LENGTH_SHORT).show();
        Toast.makeText(getApplicationContext(), "See Details: ", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
