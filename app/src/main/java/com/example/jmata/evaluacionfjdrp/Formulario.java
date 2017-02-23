package com.example.jmata.evaluacionfjdrp;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by jmata on 08/07/2016.
 */
public class Formulario extends AppCompatActivity {

    private EditText etNombre;
    private EditText etApellido;
    private EditText etNacionalidad;
    private Button btnGuardar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.formulario_layout);

        etNombre = (EditText)findViewById(R.id.etNombre);
        etApellido = (EditText)findViewById(R.id.etApellido);
        etNacionalidad = (EditText)findViewById(R.id.etNacionalidad);
        btnGuardar = (Button)findViewById(R.id.btnGuardar);

    }

    public void guardar(View v){

        Toast.makeText(Formulario.this, "Has guardado con exito", Toast.LENGTH_SHORT).show();

    }
}
