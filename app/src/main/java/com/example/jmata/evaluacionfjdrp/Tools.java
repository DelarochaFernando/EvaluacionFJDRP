package com.example.jmata.evaluacionfjdrp;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.example.jmata.evaluacionfjdrp.data.DBAdapter;
import com.example.jmata.evaluacionfjdrp.data.Usuario;

/**
 * Created by jmata on 22/03/2017.
 */
public class Tools {

    private Context ctx;
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    private DBAdapter dbAdapter;

    public Tools(Context c){
        this.ctx = c;
        preferences = PreferenceManager.getDefaultSharedPreferences(ctx);
        this.editor = preferences.edit();
        dbAdapter = new DBAdapter(ctx);
    }

    public void setStringPreferences( String name, String value){
        try {
            editor.putString(name,value);
            editor.commit();
        }catch (Exception e){

        }

    }

    public String getStringPreferences(String key){
        try{
            return preferences.getString(key,"");
        }catch (Exception e){
            return "";
        }

    }

    public Usuario getDatosUsuario(String user){
        Usuario userFromDB = dbAdapter.getDatosUser(user);
        return userFromDB;
    }
}
