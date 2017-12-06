package com.tuesday.Activity3;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Activity2 extends AppCompatActivity {

    TextView text;
    private final int CODE = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);
        text = (TextView)findViewById(R.id.textView);

        Intent i = getIntent();
        text.setText("Hi " + i.getStringExtra("input"));
    }

    public void returnToPrevious(View v){

        Intent i = new Intent();
        i.putExtra("output", "going back");
        setResult(Activity.RESULT_OK, i);
        finish();
    }


    public void goHobbies(View v){

        Intent intent = new Intent(this, Activity3.class);
        startActivityForResult(intent, CODE);
    }

}
