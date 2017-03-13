package com.example.jmata.evaluacionfjdrp.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by jmata on 10/03/2017.
 */
public class DBAdapter {

    public static final String DATABASE_NAME = "DATA_VINE";
    public static final String TABLE_USUARIOS = "Usuarios";

    public static int DATABASE_VERSION = 1;

    public static final String CREATE_USUARIOS =
            "CREATE TABLE "+TABLE_USUARIOS+" (_id integer PRIMARY KEY AUTOINCREMENT,"+
            "nombre TEXT NULL, "+
            "usuario TEXT NULL, "+
            "imgString TEXT NULL, "+
            "email TEXT NULL, "+
            "password TEXT NULL"+
            " );";

    private final Context context;
    private DataBaseHelper DbHelper;
    private SQLiteDatabase db;

    public DBAdapter(Context ctx){
        context = ctx;
        DbHelper = new DataBaseHelper(ctx);
    }

    private static class DataBaseHelper extends SQLiteOpenHelper{

        DataBaseHelper(Context context){

            super(context,DATABASE_NAME,null,DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {

            db.execSQL(CREATE_USUARIOS);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            Log.w("DATABASE","Upgrading DB from Version "+oldVersion+"to "+newVersion);

            switch (newVersion){

                case 2:
                    //db.execSQL("ALTER TABLE "++"ADD COLUMN");
                    break;
            }
        }
    }

    public DBAdapter open() throws SQLException {
        db = DbHelper.getWritableDatabase();
        return this;
    }

    public DBAdapter openToRead() throws SQLException{
        try {
            db = DbHelper.getReadableDatabase();
        }catch (SQLException ex){
            db = DbHelper.getReadableDatabase();
        }
        return this;
    }

    public void closeDB(){
        DbHelper.close();
    }

    public long save_update_usuarios
    (
            String nombre,
            String usuario,
            String imgString,
            String email,
            String password)
    {
        int count = 0;
        long res = 0;
        Cursor c = null;

        try{
            c = db.rawQuery("SELECT nombre FROM "+ TABLE_USUARIOS+ " WHERE usuario ='"+usuario+"' ",null);
            count = c.getCount();
            if(count>0){
                if(c.moveToFirst()){
                    ContentValues val = new ContentValues();
                    val.put("nombre", nombre);
                    val.put("usuario", usuario);
                    val.put("imgString", imgString);
                    val.put("email", email);
                    val.put("password", password);
                    Log.d("Update Usuario","Guardar");
                    res = db.update(TABLE_USUARIOS,val," usuario ='"+usuario+"' ",null);
                }
            }else {
                ContentValues val = new ContentValues();
                val.put("nombre", nombre);
                val.put("usuario", usuario);
                val.put("imgString", imgString);
                val.put("email", email);
                val.put("password", password);
                Log.d("Insert Usuario","Guardar");
                res = db.insert(TABLE_USUARIOS,null,val);
            }
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }finally {
            c.close();
        }
        return res;
    }

    public String validarAcceso(String usuario){

        String psw = new String();
        try{
        Cursor c = null;
        int count = 0;
        openToRead();

            c = db.rawQuery("SELECT password FROM "+TABLE_USUARIOS+" WHERE usuario ='"+usuario+"' ",null);
            if(c!=null){
                count = c.getCount();
                if(count>0){
                    psw = c.getString(0);
                    return psw;
                }else{
                    psw = "";
                    return psw;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            return "";
        }
        return psw;

    }
}
