package com.example.mapaestaciones;

public class Vehiculo {
    private Integer matricula;
    private String categoria;
    private String marca;
    private String modelo;
    private String descripcion;
    private Double precio;

    public Vehiculo(Integer matricula, String categoria, String marca, String modelo,
                    String descripcion, Double precio){
        this.matricula = matricula;
        this.categoria = categoria;
        this.marca = marca;
        this.modelo = modelo;
        this.descripcion = descripcion;
        this.precio = precio;
    }

    public String getMarca(){
        return marca;
    }

    public String getModelo(){
        return modelo;
    }
}
