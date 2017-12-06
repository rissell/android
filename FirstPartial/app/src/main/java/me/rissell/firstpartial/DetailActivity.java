package me.rissell.firstpartial;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class DetailActivity extends AppCompatActivity {

    private int n = 0;
    TextView name;
    TextView country;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        name = (TextView)findViewById(R.id.textView3);
        country = (TextView)findViewById(R.id.textView4);


        Intent i = getIntent();
        name.setText(i.getStringExtra("name"));
        country.setText(i.getStringExtra("country"));
    }

    public void pressedCount(View v){

        n = n + 1;
        Toast.makeText(getApplicationContext(), n + " times pressed", Toast.LENGTH_SHORT).show();
    }

    public void returnPressed(View v){

        Toast.makeText(getApplicationContext(), n + " times pressed", Toast.LENGTH_SHORT).show();

        Intent i = new Intent();
        setResult(Activity.RESULT_OK, i);
        finish();

    }

}
