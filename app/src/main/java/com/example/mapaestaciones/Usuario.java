package com.example.mapaestaciones;

public class Usuario {

    private String dni;
    private String nombre;
    private Integer telefono;
    private String email;
    private String pass;

    public Usuario(String dni, String nombre, Integer telefono, String email, String pass){
        this.dni = dni;
        this.nombre = nombre;
        this.telefono = telefono;
        this.email = email;
        this.pass = pass;
    }

    public String getDni(){
        return dni;
    }

    public String getNombre(){
        return nombre;
    }

    public String toString(){
        return nombre +" - "+ dni;
    }

}
