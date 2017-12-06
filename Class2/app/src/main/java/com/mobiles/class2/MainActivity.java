package com.mobiles.class2;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements Button.OnClickListener {

    TextView text1;
    Button button2;
    Button button3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text1 = (TextView)findViewById(R.id.helloText);
        text1.setText("hey");

        button2 = (Button)findViewById(R.id.button2);

        button2.setOnClickListener(new Button.OnClickListener() {

            public void onClick(View v) {

                Context context = getApplicationContext();
                String text = "hey, this is a test";
                int duration = Toast.LENGTH_SHORT;

                Toast.makeText(context, text, duration).show();
                Log.d("TEST", "This is just a log test");
            }
        });

        button3 = (Button)findViewById(R.id.button3);
        button3.setOnClickListener(this);
    }

    // whenever we listen to a UI event method must receive a View
    public void pressTest(View v){

        Toast.makeText(getApplicationContext(), "first button.", Toast.LENGTH_LONG).show();
    }


    @Override
    public void onClick(View v) {

        Toast.makeText(getApplicationContext(), "third button.", Toast.LENGTH_LONG).show();

        // Intent
        // when changing activities we don't order we ask
        Intent intent = new Intent(this, Main2Activity.class);
        startActivity(intent);
    }
}
