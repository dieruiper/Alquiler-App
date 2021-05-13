package com.example.mapaestaciones;

import java.sql.Date;

public class Reserva {

    private Integer codigo;
    private String fechaInicio;
    private String fechaFin;
    private String matricula;
    private String nombreOficina;
    private String dni;

    public Reserva(Integer codigo, String fechaInicio, String fechaFin, String matricula,
                   String nombreOficina, String dni){
        this.codigo = codigo;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.matricula = matricula;
        this.nombreOficina = nombreOficina;
        this.dni = dni;

    }

    public String toString(){
        return codigo +" - "+ dni +" - "+ matricula;
    }
}
