package me.rissell.activity6;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import layout.HobbyFragment;

public class MainActivity extends AppCompatActivity implements HobbyFragment.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onFragmentInteraction(String message) {
        Log.d("MAIN", message);

    }

}
