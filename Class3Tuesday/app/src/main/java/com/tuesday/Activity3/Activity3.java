package com.tuesday.Activity3;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Activity3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_3);
    }

    public void returnToPrevious(View v){
        Intent i = new Intent();
        i.putExtra("output", "going back");
        setResult(Activity.RESULT_OK, i);
        finish();
    }
}
