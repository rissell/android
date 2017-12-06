package com.example.andrestorresb.evaluaciondeproyectos;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

public class Main2Activity extends ActionBarActivity implements Payback.OnFragmentInteractionListener,NVP.OnFragmentInteractionListener,Checklist.OnFragmentInteractionListener {

    //botones del layout
    Button f1,f2,f3,f4;
    //contenedor de los fragmentos
    RelativeLayout conteiner;

    //se crea la vista
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        //asignamos los botones al id que aparece en res/layout/activity_main2
        this.f1=(Button)findViewById(R.id.aFragPayback);
        this.f2=(Button)findViewById(R.id.aFragNVP);
        this.f3=(Button)findViewById(R.id.aFragChecklist);
        this.f4=(Button)findViewById(R.id.aFragExcel);

        //asignamos el layout al id que aparece en res/layout/activity_main2
        //aqui se mostraran los fragmentos
        this.conteiner=(RelativeLayout)findViewById(R.id.fragConteiner);
    }

    //creamos una funcion que se manda a llavar cuando se da clik en el boton f1
    //asignamos esta funcion en res/layout/activity_main2 en la propiedad OnClick
    public void aPayback(View v){
        //se crea para manejar los fragmentos y las transacciones de fragmentos
        FragmentManager fm= getFragmentManager();
        FragmentTransaction ft= fm.beginTransaction();
        //se crea el fragmento de Payback
        Payback frag=new Payback();
        //agregamos el listener
        frag.addFragmentListener(this);
        //si hay algo lo remplazamos por el fragmento de Payback
        ft.replace(R.id.fragConteiner,frag,"FRAGMENT1");
        ft.addToBackStack(null);
        //iniciamos el fragmento
        ft.commit();
    }

    public void aNVP(View v){
        //se crea para manejar los fragmentos y las transacciones de fragmentos
        FragmentManager fm= getFragmentManager();
        FragmentTransaction ft= fm.beginTransaction();
        //se crea el fragmento de NPV
        NVP frag=new NVP();
        //agregamos el listener
        frag.addFragmentListener(this);
        //si hay algo lo remplazamos por el fragmento de Payback
        ft.replace(R.id.fragConteiner,frag,"FRAGMENT2");
        ft.addToBackStack(null);
        //iniciamos el fragmento
        ft.commit();
    }

    public void aChecklist(View v){
        //se crea para manejar los fragmentos y las transacciones de fragmentos
        FragmentManager fm= getFragmentManager();
        FragmentTransaction ft= fm.beginTransaction();
        //se crea el fragmento de NPV
        Checklist frag=new Checklist();
        //agregamos el listener
        frag.addFragmentListener(this);
        //si hay algo lo remplazamos por el fragmento de Payback
        ft.replace(R.id.fragConteiner,frag,"FRAGMENT3");
        ft.addToBackStack(null);
        //iniciamos el fragmento
        ft.commit();
    }

    public void aExcel(View v){
        Intent i=new Intent(this,ProjectScreeningMatrix.class);
        startActivity(i);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                // User chose the "Settings" item, show the app settings UI...
                return true;
            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

}
