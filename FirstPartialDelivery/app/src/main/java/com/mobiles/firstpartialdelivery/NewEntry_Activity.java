package com.mobiles.firstpartialdelivery;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.HashMap;
import java.util.Map;

public class NewEntry_Activity extends AppCompatActivity {

    EditText header, content;
    CheckBox important, privacy;
    Spinner spinner;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_entry_);

        header = (EditText) findViewById(R.id.editText);
        content = (EditText) findViewById(R.id.editText2);
        important = (CheckBox) findViewById(R.id.checkBox);
        privacy = (CheckBox) findViewById(R.id.checkBox2);

        if (getIntent().hasExtra("header")) {

            Toast.makeText(getApplicationContext(), "tiene Hols", Toast.LENGTH_LONG).show();

        }

        //Por lo pronto las carpetas estan definidas asi
        String[] folders = {"Collegue", "Funny", "Educational", "Music"};
        spinner = (Spinner) findViewById(R.id.spinner);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,
                folders);

        spinner.setAdapter(adapter);

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    public void noteOK(View v) {

        if (!header.getText().toString().isEmpty() && !content.getText().toString().isEmpty()) {
            Intent i = new Intent();

            i.putExtra("header", header.getText().toString());
            i.putExtra("content", content.getText().toString());

            Firebase ref = new Firebase("https://cpu.firebaseio.com/");

            Map<String, Object> post1 = new HashMap<String, Object>();
            post1.put("header", header.getText().toString());
            post1.put("content", content.getText().toString());
            post1.put("important", important.isChecked());
            post1.put("privacy", privacy.isChecked());

            ref.push().setValue(post1);

            //Toast.makeText(getApplicationContext(), "yeah", Toast.LENGTH_LONG).show();
            //setResult(Activity.RESULT_OK, i);
            setResult(Activity.RESULT_OK, i);
            finish();

        }
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "NewEntry_ Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.mobiles.firstpartialdelivery/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "NewEntry_ Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.mobiles.firstpartialdelivery/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }
}