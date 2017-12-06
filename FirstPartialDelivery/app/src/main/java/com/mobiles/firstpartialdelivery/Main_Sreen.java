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


public class Main_Sreen extends AppCompatActivity implements AdapterView.OnItemClickListener, AdapterView.OnItemSelectedListener{

    public ListView listView;
    private final int NEWNOTE_CODE = 1;
    public ArrayList<Note> archive;
    //this can be changed to the JSON part
    private Activity esta = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main__sreen);
        Firebase.setAndroidContext(this);

        archive = new ArrayList<Note>();
        listView = (ListView)findViewById(R.id.listView);

        Firebase myFirebaseRef = new Firebase("https://cpu.firebaseio.com/");

        myFirebaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                    archive.add(postSnapshot.getValue(Note.class));
                }
                Adapter newAdapter = new Adapter(esta, archive);
                listView.setAdapter(newAdapter);
            }


            @Override
            public void onCancelled(FirebaseError error) {

            }
        });

        //Ingresar data al adapter
       // archive.add(new Note ("Esta es la tarea", "http://dropbox.com", false, true));

        listView.setOnItemClickListener(this);
        listView.setOnItemSelectedListener(this);


        //Adapter newAdapter = new Adapter(this, archive);


    }


    public void newNote(View v){

        Intent intent = new Intent(this, NewEntry_Activity.class);
        //startActivity(intent);
        startActivityForResult(intent, NEWNOTE_CODE);

    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        //Toast.makeText(getApplicationContext(), "Saved", Toast.LENGTH_LONG).show();

        if (requestCode == NEWNOTE_CODE && resultCode == Activity.RESULT_OK) {

            Toast.makeText(getApplicationContext(), "Saved", Toast.LENGTH_LONG).show();
            //Log.d("ITEM CLICK", "YEEEESS!!");
            //stView.setOnItemClickListener(this);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


        /* Por alguna razon estos  metodos no sirven*/

        Toast.makeText(getApplicationContext(), "tiene header",  Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this, NewEntry_Activity.class);
        //startActivity(intent);
        intent.putExtra("header", archive.get(position).getHeader());
        intent.putExtra("content", archive.get(position).getContent());

        startActivityForResult(intent, NEWNOTE_CODE);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }



    public void deleteNote(View v){

        //archive.remove()

    }

}
