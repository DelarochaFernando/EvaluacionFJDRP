package com.example.jmata.evaluacionfjdrp;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.media.ExifInterface;
import android.media.Image;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.PersistableBundle;
import android.provider.MediaStore;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
//import org.kobjects.base64.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jmata.evaluacionfjdrp.data.DBAdapter;
import com.example.jmata.evaluacionfjdrp.data.Usuario;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import org.w3c.dom.Text;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by jmata on 08/07/2016.
 */
public class Formulario extends ActionBarActivity {


    private Button btnGuardar;
    private Bitmap bmToSave;
    private boolean info;
    private CircleImageView profile_image;
    private CoordinatorLayout coordinatorLayout;
    private DBAdapter db;
    private Dialog dialog = null;
    private EditText editNombre,editUsuario,editEmail,editPsw;
    private String mensaje;
    private Toolbar toolbar;
    private TextView txtPicture;
    private Usuario nuevoUsuario;

    private int rotar = 0;
    private int fotoElegida;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.formulario_layout);

        db = new DBAdapter(Formulario.this);
        coordinatorLayout = (CoordinatorLayout)findViewById(R.id.coordinatorLayout);
        editNombre = (EditText) findViewById(R.id.editNombre);
        editUsuario = (EditText) findViewById(R.id.editUsuario);
        editEmail = (EditText) findViewById(R.id.editEmail);
        editPsw = (EditText) findViewById(R.id.editPsw);
        //btnSnack = (Button) findViewById(R.id.btnGuardar);
        txtPicture = (TextView) findViewById(R.id.txtPicture);
        //toolbar = (Toolbar) findViewById(R.id.toolbar);
        profile_image = (CircleImageView) findViewById(R.id.profile_image);


        //setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Sign up");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Window wd = this.getWindow();
        wd.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        wd.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            wd.setStatusBarColor(this.getResources().getColor(R.color.colorPrimaryDark));
        }

        /*
        editNombre.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                String userTyped = editNombre.getText().toString();
                if (hasFocus) {
                    editNombre.setTextAlignment(View.TEXT_ALIGNMENT_VIEW_START);
                    editNombre.setHint("");
                } else {
                    if (userTyped.length() == 0 || userTyped.equals("")) {
                        editNombre.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                        editNombre.setHint("Nombre");
                    } else {
                        editNombre.setText(userTyped);
                    }
                }
            }
        });

        editUsuario.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                String userTyped = editUsuario.getText().toString();
                if (hasFocus) {
                    editUsuario.setTextAlignment(View.TEXT_ALIGNMENT_VIEW_START);
                    editUsuario.setHint("");
                } else {
                    if (userTyped.length() == 0 || userTyped.equals("")) {
                        editUsuario.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                        editUsuario.setHint("Usuario");
                    } else {
                        editUsuario.setText(userTyped);
                    }
                }
            }
        });

        editEmail.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                String userTyped = editEmail.getText().toString();
                if (hasFocus) {
                    editEmail.setTextAlignment(View.TEXT_ALIGNMENT_VIEW_START);
                    editEmail.setHint("");
                } else {
                    if (userTyped.length() == 0 || userTyped.equals("")) {
                        editEmail.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                        editEmail.setHint("Email");
                    } else {
                        editEmail.setText(userTyped);
                    }
                }
            }
        });

        editPsw.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                String userTyped = editPsw.getText().toString();
                if (hasFocus) {
                    editPsw.setTextAlignment(View.TEXT_ALIGNMENT_VIEW_START);
                    editPsw.setHint("");
                } else {
                    if (userTyped.length() == 0 || userTyped.equals("")) {
                        editPsw.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                        editPsw.setHint("Password");
                    } else {
                        editPsw.setText(userTyped);
                    }
                }
            }
        });*/

        profile_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takePictureIntent();
            }
        });

        txtPicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takePictureIntent();
            }
        });

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    int REQUEST_IMAGE_CAPTURE = 0;
    int CHOOSE_PIC_CODE = 0;

    private void takePictureIntent() {

        AlertDialog.Builder dialogProfPic = new AlertDialog.Builder(this);
        dialogProfPic.setTitle("Picture");
        dialogProfPic.setPositiveButton("From Photos", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                //intent.setType("image/*");
                CHOOSE_PIC_CODE = 2;
                startActivityForResult(intent, CHOOSE_PIC_CODE);
            }
        });
        dialogProfPic.setNegativeButton("From Camera", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (intent.resolveActivity(getPackageManager()) != null) {
                    REQUEST_IMAGE_CAPTURE = 1;
                    startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);
                }
            }
        });
        AlertDialog dialog = dialogProfPic.create();
        dialog.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        Bitmap bm = null;
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            try {
                //dialog = ProgressDialog.show(Formulario.this, "Fotografia", "Procesando...", true, false);
                fotoElegida = 1;
                Bundle extras = data.getExtras();
                bm = (Bitmap) extras.get("data");
                bmToSave = bm;
                profile_image.setImageBitmap(bm);
            } catch (Exception e) {
                e.printStackTrace();

            }
        }

        if (requestCode == CHOOSE_PIC_CODE && resultCode == RESULT_OK) {
            Uri imgUri = data.getData();
            try {
                    fotoElegida = 2;
                byte[] imgbytes = imageFromGallery(imgUri);
                //String cadenaImg = Base64.encodeToString(imgbytes,Base64.DEFAULT);
                bmToSave = BitmapFactory.decodeByteArray(imgbytes,0,imgbytes.length);
                profile_image.setImageBitmap(bmToSave);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    private String getOrientation(Uri uri){
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        String orientation = "landscape";
        try{
            String image = new File(uri.getPath()).getAbsolutePath();
            BitmapFactory.decodeFile(image, options);
            int imageHeight = options.outHeight;
            int imageWidth = options.outWidth;
            if (imageHeight > imageWidth){
                orientation = "portrait";
            }
        }catch (Exception e){
            //Do nothing
        }
        return orientation;
    }

    private byte[] imageFromGallery(Uri uri){
        boolean landscape = false;
        boolean portrait = false;
        Bitmap bitResult = null;
        byte[] imagen = null;
        try{
            Bitmap bm = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bm.compress(Bitmap.CompressFormat.JPEG,80,stream);
            byte[] byteArray = stream.toByteArray();
            //FileInputStream inputStream = new FileInputStream(uri.getPath());
            //ExifInterface exif = new ExifInterface(uri.getPath());
            //int orientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION,ExifInterface.ORIENTATION_NORMAL);
            /*if(orientation<=0){
                orientation
            }
            int angle = 0;
            switch(orientation) {
                case ExifInterface.ORIENTATION_ROTATE_270:
                    angle = 270;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_180:
                    angle = 180;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_90:
                    angle = 90;
                    break;
                default:
                    angle = 0;
                    break;
            }
            if(bm.getWidth()>bm.getHeight()) {
                landscape = true;
            }else{
                portrait = true;
            }
            //boolean landscape = bm.getWidth()>bm.getHeight();
            //boolean portrait = bm.getHeight()>bm.getWidth();
            Matrix m = new Matrix();
            if(angle == 0&& landscape){
                m.postRotate(270);
            }else if(angle == 0 && portrait) {
                m.postRotate(angle);
            }*/

            imagen = byteArray;
            //bitResult = bm;
            //Bitmap.createBitmap(bm,0,0,bm.getWidth(),bm.getHeight(),m,true);

        }catch(IOException e){
           String msg = e.getMessage();
            Log.e(msg, "-- Error in setting image");
        }catch (OutOfMemoryError oom){
            Log.e("", "-- OOM Error in setting image");
        }
        return imagen;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //
        info = false;
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent = new Intent(Formulario.this, myLogin.class);
                startActivity(intent);
                return true;
            case R.id.btnSave:
                info = checkInfo();
                Snackbar snackbar = Snackbar.make(coordinatorLayout,mensaje,Snackbar.LENGTH_LONG);
                snackbar.show();
                Intent savedUser = new Intent(Formulario.this,myLogin.class);
                startActivity(savedUser);
                //Toast.makeText(Formulario.this, mensaje, Toast.LENGTH_SHORT).show();
                return info;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu,menu);
        return true;
        //super.onCreateOptionsMenu(menu);
    }

    public boolean checkInfo() {
        //Drawable errorIcon = getResources().getDrawable(R.drawable.error);
        //Drawable warningIcon = getResources().getDrawable(R.drawable.warning);

        if(editNombre.length()==0){
            editNombre.setError("Ingrese nombre");
            return false;
        }

        if(editUsuario.length()==0){
            editUsuario.setError("Ingrese usuario");
            return false;
        }

        if(editEmail.length() == 0){
            editEmail.setError("Ingrese email");
            return false;
        }

        if(editPsw.length()==0){
            editPsw.setError("Ingrese el password");
            return false;
        }

        if(editPsw.length()<8){
            editPsw.setError("El password debe ser una longitud de 8 caracteres mínimo");
            return false;
        }

        if(fotoElegida == 0){
            txtPicture.setError("Se recomienda incluir una fotografía");
            return false;
        }

        String nombre = editNombre.getText().toString();
        String usuario = editUsuario.getText().toString();
        String email = editEmail.getText().toString();
        String password = editPsw.getText().toString();


            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bmToSave.compress(Bitmap.CompressFormat.JPEG,80,stream);
            byte[] data_image = stream.toByteArray();
            String img_str = Base64.encodeToString(data_image, Base64.DEFAULT);


        nuevoUsuario = new Usuario(nombre,usuario, img_str,email,password);
        boolean result = updateUsuario();
        if(data_image!=null){
            data_image = null;
        }

        return result;
    }

    public boolean updateUsuario(){

        try{
            String name = nuevoUsuario.getNombre();
            String user = nuevoUsuario.getUsuario();
            String imgString = nuevoUsuario.getImgString();
            String mail = nuevoUsuario.getEmail();
            String pssword = nuevoUsuario.getPassword();

            db.open();

            long id = 1;
            id = db.save_update_usuarios(name, user, imgString, mail, pssword);

            db.closeDB();

            if(id ==-1){
                mensaje = "Error, no pudo guardarse el usuario.";
                return false;
            }else{
                mensaje = "Usuario guardado exitosamente.";
                return true;
            }
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Formulario Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.example.jmata.evaluacionfjdrp/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Formulario Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.example.jmata.evaluacionfjdrp/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }
}
