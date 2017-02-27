package com.example.jmata.evaluacionfjdrp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by jmata on 08/07/2016.
 */
public class Formulario extends AppCompatActivity {

    private EditText etNombre;
    private EditText etApellido;
    private EditText etNacionalidad;
    private Button btnGuardar;
    private Toolbar toolbar;
    private TextView txtPicture;
    private CircleImageView profile_image;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.formulario_layout);

        etNombre = (EditText)findViewById(R.id.editNombre);
        etApellido = (EditText)findViewById(R.id.editApellido);
        etNacionalidad = (EditText)findViewById(R.id.editNacionalidad);
        btnGuardar = (Button)findViewById(R.id.btnGuardar);
        txtPicture = (TextView)findViewById(R.id.txtPicture);
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        profile_image = (CircleImageView)findViewById(R.id.profile_image);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Sign up");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Window wd = this.getWindow();
        wd.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        wd.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            wd.setStatusBarColor(this.getResources().getColor(R.color.colorPrimaryDark));
        }
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

        txtPicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takePictureIntent();
            }
        });

    }

    int REQUEST_IMAGE_CAPTURE = 0;
    int CHOOSE_PIC_CODE = 0;
    private void takePictureIntent(){

        AlertDialog.Builder dialogProfPic = new AlertDialog.Builder(this);
        dialogProfPic.setTitle("Picture");
        dialogProfPic.setPositiveButton("From Photos", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                //intent.setType("image/*");
                CHOOSE_PIC_CODE = 2;
                startActivityForResult(intent,CHOOSE_PIC_CODE);
            }
        });
        dialogProfPic.setNegativeButton("From Camera", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if(intent.resolveActivity(getPackageManager())!=null){
                    REQUEST_IMAGE_CAPTURE = 1;
                    startActivityForResult(intent,REQUEST_IMAGE_CAPTURE);
                }
            }
        });
        AlertDialog dialog = dialogProfPic.create();
        dialog.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK){
            Bundle extras = data.getExtras();
            Bitmap bp = (Bitmap)extras.get("data");
            profile_image.setImageBitmap(bp);
        }
        if(requestCode == CHOOSE_PIC_CODE && resultCode == RESULT_OK){
            Uri imgUri = data.getData();
            profile_image.setImageURI(imgUri);
        }
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
