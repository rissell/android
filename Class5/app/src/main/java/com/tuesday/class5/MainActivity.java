package com.tuesday.class5;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import org.json.JSONArray;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener,
        AdapterView.OnItemSelectedListener,
        JSONRequest.JSONListener{

    Spinner spinner;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinner = (Spinner)findViewById(R.id.spinner);
        listView = (ListView)findViewById(R.id.listView);

        String[] students = {"Enciso", "Noe", "Mike", "Zamora :("};

        // adapter ?
        // - translate data from a structure to UI
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,
                students);

        ArrayList<Student> studentList = new ArrayList<Student>();

        studentList.add(new Student("Andres", "ISC", 65));
        studentList.add(new Student("Mario", "ISC", 62));
        studentList.add(new Student("Zamora :(", "ISC", 25));
        studentList.add(new Student("Mike", "ISC", 69.4999999f));

        MyAdapter newAdapter = new MyAdapter(this, studentList);

        spinner.setAdapter(newAdapter);
        listView.setAdapter(newAdapter);

        listView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Log.d("ITEM CLICK", position + "");
        new JSONRequest(this, this).execute("http://jsonplaceholder.typicode.com/posts");
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void doSomething(JSONArray array) {

        JSONAdapter adapter = new JSONAdapter(array, this);
        listView.setAdapter(adapter);
    }
}
