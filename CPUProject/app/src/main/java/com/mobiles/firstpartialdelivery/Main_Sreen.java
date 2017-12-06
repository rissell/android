package com.mobiles.firstpartialdelivery;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;


public class Main_Sreen extends AppCompatActivity implements AdapterView.OnItemClickListener{

    public ListView listView;
    private final int NEWNOTE_CODE = 1;
    public ArrayList<Note> archive;
    public Adapter adapter;
    //this can be changed to the JSON part
    private Activity esta = this;
    Firebase myFirebaseRef;
    String usr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main__sreen);
        Firebase.setAndroidContext(this);
        archive = new ArrayList<Note>();
        listView = (ListView)findViewById(R.id.listView);


        usr = getIntent().getStringExtra("usr");
        myFirebaseRef = new Firebase("https://cpu.firebaseio.com/" + usr + "/posts/");

        myFirebaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
               // archive = new ArrayList<Note>();
                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                    archive.add(postSnapshot.getValue(Note.class));
                }
                adapter = new Adapter(esta, archive);
                listView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(FirebaseError error) {

            }
        });

        listView.setOnItemClickListener(this);
       // listView.setOnItemSelectedListener(this);
    }



    public void newNote(View v){

        Intent intent = new Intent(this, NewEntry_Activity.class);
        intent.putExtra("edit", false);
        intent.putExtra("usr", usr);
        startActivityForResult(intent, NEWNOTE_CODE);

    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == NEWNOTE_CODE && resultCode == Activity.RESULT_OK) {

            Toast.makeText(getApplicationContext(), "Saved", Toast.LENGTH_LONG).show();
            //stView.setOnItemClickListener(this);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


        /* De momento le paso el objecto 'Note'  */

        Intent intent = new Intent(this, NewEntry_Activity.class);
        Note selected = archive.get(position);
        intent.putExtra("Note", selected);
        intent.putExtra("edit", true);


        startActivityForResult(intent, NEWNOTE_CODE);
    }


    /*
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        Intent intent = new Intent(this, NewEntry_Activity.class);
        //startActivity(intent);
          //int position = archive.get(position);

        //intent.putExtra("content", archive.get(position).getContent());

        startActivityForResult(intent, NEWNOTE_CODE);

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }*/



    /*public void deleteNote(View v){

        v.setFocusable(false);
        //archive.remove()
        View parentRow = (View) v.getParent();
        ListView listView = (ListView) parentRow.getParent();
        final int position = listView.getPositionForView(parentRow);
        String header = archive.get(position).header.toString();
        //Toast.makeText(getApplicationContext(), "clicked on " +header,  Toast.LENGTH_LONG).show();

        //Solo visualmente nada de firebase no se como ni cual es la 'key'
        archive.remove(position);
        adapter.notifyDataSetChanged();


    }*/
}