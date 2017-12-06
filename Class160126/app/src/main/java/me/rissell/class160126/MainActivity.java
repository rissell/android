package me.rissell.class160126;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
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
    private final String myFile = "propierties.xml";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text = (EditText)findViewById(R.id.editText);

        //load Properies
        File file = new File(getFilesDir(), myFile);
        properties = new Properties();

        try {

            if(file.exists()) {


                FileInputStream fis = openFileInput(myFile);
                properties.loadFromXML(fis);
                fis.close();

                Toast.makeText(getApplicationContext(), "properties loaded from file", Toast.LENGTH_SHORT).show();

            } else {

                FileOutputStream fos = openFileOutput(myFile, Context.MODE_PRIVATE);
                properties.storeToXML(fos, null);

                Toast.makeText(getApplicationContext(), "properties file crated", Toast.LENGTH_SHORT).show();

            }

        }catch (IOException ioe){
            Log.e("Properites", ioe.toString());
        }
    }

    public void changeActivity(View v) {

        Intent intent = new Intent(this, Main2Activity.class);
        intent.putExtra("input", text.getText().toString());
        startActivityForResult(intent, CODE);

    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data){


        if(requestCode==CODE && resultCode == Activity.RESULT_OK){
            Toast.makeText(getApplicationContext(), data.getStringExtra("output"), Toast.LENGTH_SHORT).show();


        }

    }

    public void saveValueToProperties(View v){

        properties.setProperty("text", text.getText().toString());

    }

    public void loadValueToProperties(View v){

        Toast.makeText(getApplicationContext(), properties.getProperty("text"), Toast.LENGTH_SHORT).show();

    }

    public void rawFile(View v){
        try {
            InputStream is = getResources().openRawResource(R.raw.plaintext);
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);

            Toast.makeText(getApplicationContext(), br.readLine(), Toast.LENGTH_SHORT).show();

        }catch (IOException ioe){
            Log.wtf("Main", "raw file not readable or something");
        }
    }

}
