package com.example.jmata.evaluacionfjdrp.data;

/**
 * Created by jmata on 10/03/2017.
 */
public class Usuario {

    private String nombre;
    private String usuario;
    private String imgString;
    private String email;
    private String password;

    public String getImgString() {
        return imgString;
    }

    public void setImgString(String imgString) {
        this.imgString = imgString;
    }



    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }



    public Usuario(){

    }

    public Usuario(String nombre, String usuario, String imgString, String email, String password){

        this.nombre = nombre;
        this.usuario = usuario;
        this.email = email;
        this.password = password;
        this.imgString = imgString;
    }
}
