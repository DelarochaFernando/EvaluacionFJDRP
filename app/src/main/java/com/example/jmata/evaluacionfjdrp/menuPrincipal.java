package com.example.jmata.evaluacionfjdrp;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by jmata on 08/07/2016.
 */
public class menuPrincipal extends AppCompatActivity {

    private Button btnDatosPersonales;
    private Button btnFotografia;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_layout);
        btnDatosPersonales = (Button) findViewById(R.id.btnDatosPersonales);
        btnFotografia = (Button) findViewById(R.id.btnFotoGrafia);


    }

    public void ingresarDatos(View v) {
        Intent i = new Intent(this, Formulario.class);
        startActivity(i);
    }
}
