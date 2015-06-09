package com.example.nupii.json;

import android.app.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;


public class vistaindividual extends Activity{
    static final String ID_ALUMNO = "id_alumno";
    static final String KEY_NOMBRE = "nombre";
    static final String KEY_APELLIDO_P = "apellido_p";
    static final String KEY_APELLIDO_M = "apellido_m";
    static final String KEY_CARRERA = "carrera";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vistaindividual);

        Intent in = getIntent();

        // Get XML values from previous intent
        String id_alumno = in.getStringExtra(ID_ALUMNO);
        String nombre = in.getStringExtra(KEY_NOMBRE);
        String apellido_p= in.getStringExtra(KEY_APELLIDO_P);
        String apellido_m= in.getStringExtra(KEY_APELLIDO_M);
        String carrera= in.getStringExtra(KEY_CARRERA);

        // Displaying all values on the screen
        TextView lblidalum = (TextView) findViewById(R.id.id_alumno);
        TextView lblnombre = (TextView) findViewById(R.id.nombre);
        TextView lblapellidop = (TextView) findViewById(R.id.apellido_p);
        TextView lblapellidom = (TextView) findViewById(R.id.apellido_m);
        TextView lblcarrera = (TextView) findViewById(R.id.carrera);

        lblidalum.setText(id_alumno);
        lblnombre.setText(nombre);
        lblapellidop.setText(apellido_p);
        lblapellidom.setText(apellido_m);
        lblcarrera.setText(carrera);

    }
}