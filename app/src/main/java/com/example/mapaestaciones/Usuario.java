package com.example.mapaestaciones;

public class Usuario {

    Integer telefono;
    String dni, usuario, nombre, apellidos, email, password;

    public Usuario(String dni, String usuario, String nombre, String apellidos,
                   Integer telefono, String email, String password){
        this.dni = dni;
        this.usuario = usuario;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.telefono = telefono;
        this.email = email;
        this.password = password;
    }

    public Usuario(){
        this.dni = "";
        this.usuario = "";
        this.nombre = "";
        this.apellidos = "";
        this.telefono = 0;
        this.email = "";
        this.password = "";
    }

    public String getDni(){ return dni; }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getUsuario(){
        return usuario;
    }

    public void setUsuario(String usuario) { this.usuario = usuario; }

    public String getNombre(){
        return nombre;
    }

    public void setNombre(String nombre) { this.nombre = nombre; }

    public Integer getTelefono() {
        return telefono;
    }

    /*public void setTelefono(Integer telefono) {
        this.telefono = telefono;
    }*/

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
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

    public String toString(){
        return nombre +" - "+ dni;
    }
/*
    public Boolean isNull(){
        return dni.equals("") | usuario.equals("") | nombre.equals("") | apellidos.equals("") |
                telefono.equals(0) | email.equals("") | password.equals("");
    }*/

}
