package me.rissell.class160301;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity implements  My.OnFragmentInteractionListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onFragmentInteraction(String message) {
        Log.d("Hola", message);

    }

    public void addFragment(View v){

        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        My fragment = My.newInstance("FRAGMENT!");
        fragment.addFragmentListener(this);
        ft.add(R.id.Container, fragment, "Fragment1");

        ft.commit();
        
    }

    public void removeFragment(View v){
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        Fragment theFragment = fm.findFragmentByTag("Fragment1");
        if(theFragment == null){
            return;
        }
        ft.remove(theFragment);
        ft.commit();

    }
}
