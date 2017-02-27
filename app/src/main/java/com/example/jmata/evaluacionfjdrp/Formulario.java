package com.example.jmata.evaluacionfjdrp;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
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
    private Toolbar toolbar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.formulario_layout);

        etNombre = (EditText)findViewById(R.id.editNombre);
        etApellido = (EditText)findViewById(R.id.editApellido);
        etNacionalidad = (EditText)findViewById(R.id.editNacionalidad);
        btnGuardar = (Button)findViewById(R.id.btnGuardar);
        toolbar = (Toolbar)findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Sign up");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        etNombre.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                String userTyped = etNombre.getText().toString();
                if(hasFocus){
                    etNombre.setTextAlignment(View.TEXT_ALIGNMENT_VIEW_START);
                    etNombre.setHint("");
                }else{
                    if(userTyped.length() == 0||userTyped.equals("")){
                        etNombre.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                        etNombre.setHint("Nombre");
                    }else{etNombre.setText(userTyped);}
                }
            }
        });

        etApellido.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                String userTyped = etApellido.getText().toString();
                if(hasFocus){
                    etApellido.setTextAlignment(View.TEXT_ALIGNMENT_VIEW_START);
                    etApellido.setHint("");
                }else{
                    if(userTyped.length() == 0||userTyped.equals("")){
                        etApellido.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                        etApellido.setHint("Apellido");
                    }else{etApellido.setText(userTyped);}
                }
            }
        });

        etNacionalidad.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                String userTyped = etNacionalidad.getText().toString();
                if(hasFocus){
                    etNacionalidad.setTextAlignment(View.TEXT_ALIGNMENT_VIEW_START);
                    etNacionalidad.setHint("");
                }else{
                    if(userTyped.length() == 0||userTyped.equals("")){
                        etNacionalidad.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                        etNacionalidad.setHint("Nacionalidad");
                    }else{etNacionalidad.setText(userTyped);}
                }
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //
        switch (item.getItemId()){
            case android.R.id.home:
                Intent intent  = new Intent(Formulario.this,myLogin.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);

    }

    public void guardar(View v){

        Toast.makeText(Formulario.this, "Has guardado con exito", Toast.LENGTH_SHORT).show();

    }
}
