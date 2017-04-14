package com.example.jmata.evaluacionfjdrp;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

//import com.facebook.FacebookSdk;

import com.example.jmata.evaluacionfjdrp.data.DBAdapter;

import java.io.IOException;

/**
 * Created by jmata on 08/07/2016.
 */
public class myLogin extends AppCompatActivity {


    private Button btnEntrar;
    private DBAdapter db;
    private EditText edituser;
    private EditText editPsw;
    FrameLayout home_container;
    IntroVideoSurfaceView msurfaceView = null;
    private ImageView logo;
    private MediaPlayer mp = null;
    private String usuario, contraseña, mensaje;
    private TextView txtSignUp;
    private Tools tools;




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.mylogin);
        try{
            db = new DBAdapter(myLogin.this);
            btnEntrar = (Button)findViewById(R.id.btnEntrar);
            edituser = (EditText)findViewById(R.id.edituser);
            editPsw = (EditText)findViewById(R.id.editPsw);
            logo = (ImageView)findViewById(R.id.logo);
            txtSignUp = (TextView)findViewById(R.id.txtSignUp);
            tools = new Tools(this);
            //btnEntrar.getBackground().setAlpha(128);
            home_container = (FrameLayout)findViewById(R.id.home_container);
            msurfaceView = (IntroVideoSurfaceView)findViewById(R.id.surface);
            SurfaceHolder mholder = msurfaceView.getHolder();
            mholder.addCallback(msurfaceView);

        }catch (Exception e){
            e.printStackTrace();
        }
        //logo.setFocusable(true);
        logo.requestFocus();
        btnEntrar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //validaCredenciales();
                Intent intent = new Intent(myLogin.this, menuPrincipal.class);
                startActivity(intent);

            }
        });

        txtSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(myLogin.this, Formulario.class);
                startActivity(intent);
            }
        });


        //String userTyped = edituser.getText().toString();

        edituser.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                String userTyped = edituser.getText().toString();
                if(hasFocus){
                    edituser.setTextAlignment(View.TEXT_ALIGNMENT_VIEW_START);
                    edituser.setHint("");
                }else{
                    if(userTyped.length() == 0||userTyped.equals("")){
                        edituser.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                        edituser.setHint("User");
                    }else{edituser.setText(userTyped);}
                }
            }
        });

        editPsw.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                String pswTyped = editPsw.getText().toString();
                if(hasFocus){
                    editPsw.setTextAlignment(View.TEXT_ALIGNMENT_VIEW_START);
                    editPsw.setHint("");
                }else {
                    if(pswTyped.length() == 0||pswTyped.equals("")){
                        editPsw.setHint("Password");
                        editPsw.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                    }else{editPsw.setText(pswTyped);}
                }
            }
        });

        /*btnEntrar.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                if(event.getAction() == MotionEvent.ACTION_BUTTON_PRESS){
                    btnEntrar.setBackground(R.drawable.clicked_state);
                }
                return false;
            }
        });
        /*mp = new MediaPlayer();
        msurfaceView = (SurfaceView)findViewById(R.id.surface);
        SurfaceHolder mholder = msurfaceView.getHolder();
        mholder.addCallback(this);
        etUsuario = (EditText) findViewById(R.id.etuser);
        etPass = (EditText) findViewById(R.id.etPass);
        btnEntrar = (Button) findViewById(R.id.btnEntrar);

        //setContentView(R.layout.mylogin);*/


    }

    public boolean validaCredenciales() {

        try {
            usuario = edituser.getText().toString();
            contraseña = editPsw.getText().toString();
            if (usuario.length() == 0) {
                edituser.setError("Ingrese el Usuario.");
                return false;
            }

            if (contraseña.length() == 0) {
                editPsw.setError("Ingrese el password");
                return false;
            }

            String respuestaPsw = db.validarAcceso(usuario);
            if (respuestaPsw.equals(contraseña)) {

                tools.setStringPreferences("user", usuario);
                tools.setStringPreferences("contraseña", contraseña);

                mensaje = "Acceso permitido, Bienvenido: " + usuario;
                AlertDialog dialog = new AlertDialog.Builder(myLogin.this)
                .setTitle("Login").setMessage(mensaje).setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                    Intent intent = new Intent(myLogin.this,menuPrincipal.class);
                    startActivity(intent);
                }
                }).create();
                dialog.show();
                return true;


            }else {

                mensaje ="No es el password valido, ingrese el correcto.";
                AlertDialog dialog = new AlertDialog.Builder(myLogin.this)
                        .setTitle("Login").setMessage(mensaje).setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        }).create();
                dialog.show();
                return false;
            }

        }catch (Exception e){
            e.printStackTrace();
            mensaje = "Error de Acceso, vuelva a intentar.";
            AlertDialog dialog = new AlertDialog.Builder(myLogin.this)
                    .setTitle("Login").setMessage(mensaje).setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    }).create();
            dialog.show();
            return false;
        }


    }

    /*@Override
    public void surfaceCreated(SurfaceHolder holder) {
        AssetFileDescriptor afd = getResources().openRawResourceFd(R.raw.clouds);
        //Uri video = Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.clouds);
        try {
            mp.setDataSource(afd.getFileDescriptor(),afd.getStartOffset(),afd.getDeclaredLength());
            mp.prepare();

            int videoWidth = mp.getVideoWidth();
            int videoHeight = mp.getVideoHeight();

            int screenWidth = getWindowManager().getDefaultDisplay().getWidth();
            int screenHeight = getWindowManager().getDefaultDisplay().getHeight();
            android.view.ViewGroup.LayoutParams lp = msurfaceView.getLayoutParams();
            lp.width = screenWidth;
            lp.height = screenHeight;
            //lp.height = (int)(((float)videoHeight/(float)videoWidth)*(float)screenWidth);
            msurfaceView.setLayoutParams(lp);
            mp.setDisplay(holder);
            mp.start();
            if(!mp.isPlaying()){
                mp.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }*/

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK){
            myLogin.this.finishAffinity();
            System.exit(0);
            return true;
        }else{
            return false;
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        SurfaceHolder mholder = msurfaceView.getHolder();
        mholder.addCallback(msurfaceView);
    }
}
