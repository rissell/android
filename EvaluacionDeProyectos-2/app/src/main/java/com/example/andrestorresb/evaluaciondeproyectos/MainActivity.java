package com.example.andrestorresb.evaluaciondeproyectos;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends ActionBarActivity {
    //el unico boton del layout
    Button f1;

    //se crea la vista
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //asignamos el boton al id que aparece en res/layout/activity_main
        this.f1=(Button) findViewById(R.id.button);

    }

    //creamos una funcion que se manda a llavar cuando se da clik en el boton
    //asignamos esta funcion en res/layout/activity_main en la propiedad OnClick
    public void irAl(View v){
        //creamos un intent para irnos a la vista main2activity
        Intent i=new Intent(this,Main2Activity.class);
        startActivity(i);
    }




}
