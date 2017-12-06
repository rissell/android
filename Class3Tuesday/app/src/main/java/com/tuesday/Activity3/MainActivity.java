package com.tuesday.Activity3;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

public class MainActivity extends AppCompatActivity {


    EditText text;
    private final int CODE = 0;
    private Properties properties;
    private final String myFile = "properties.xml";

    // properties! (NOT my chanclas)
    // An XML file in which we can easily save data

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text = (EditText)findViewById(R.id.editText);

        // load properties
        Log.d("FOLDER LOCATION", getFilesDir().toString());
        File file = new File(getFilesDir(), myFile);
        properties = new Properties();

        try {

            if(file.exists()){

                // file exists! (Sorry.)
                FileInputStream fis = openFileInput(myFile);

                // load properties into object
                properties.loadFromXML(fis);

                fis.close();

                Toast.makeText(getApplicationContext(),
                        "properties loaded from file",
                        Toast.LENGTH_SHORT).show();

            } else {

                // file not there
                // just do it!
                FileOutputStream fos = openFileOutput(myFile, Context.MODE_PRIVATE);
                properties.storeToXML(fos, null);
                Toast.makeText(getApplicationContext(),
                        "properties file created",
                        Toast.LENGTH_SHORT).show();

            }

        }catch(IOException ioe){

            Log.e("Properties", ioe.toString());
        }
    }

    public void changeActivity(View v){

        Intent intent = new Intent(this, Activity2.class);
        intent.putExtra("input", text.getText().toString());
        startActivityForResult(intent, CODE);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(requestCode == CODE && resultCode == Activity.RESULT_OK){

            Toast.makeText(getApplicationContext(),
                    data.getStringExtra("output"),
                    Toast.LENGTH_SHORT).show();
        }

    }

    public void saveValueToProperties(View v) {

        properties.setProperty("text", text.getText().toString());
    }


    public void readValueFromProperties(View v){


        Toast.makeText(getApplicationContext(),
                properties.getProperty("text"),
                Toast.LENGTH_SHORT).show();
    }

    public void rawFile(View v) {

        try {

            // verbose - verboso (?)
            InputStream is = getResources().openRawResource(R.raw.plaintext);
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);

            Toast.makeText(getApplicationContext(),
                    br.readLine(),
                    Toast.LENGTH_SHORT).show();

        } catch(IOException ioe){

            Log.wtf("MAIN", "raw file not readable or something");
        }
    }
}
