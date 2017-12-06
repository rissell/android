package me.rissell.class160126;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {

    TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        text = (TextView) findViewById(R.id.textView);

        Intent i = getIntent();
        text.setText(i.getStringExtra("input"));
    }
    public void returnToPrevius(View v) {
        Intent i = new Intent();
        i.putExtra("output", "going back");
        setResult(Activity.RESULT_OK, i);
        finish();
    }

}
