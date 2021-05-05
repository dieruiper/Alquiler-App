package com.example.mapaestaciones;

import java.sql.Date;

public class Reserva {

    private Integer codigo;
    private Date fechaInicio;
    private Date fechaFin;
    private Integer matricula;
    private String nombreOficina;
    private String dni;

    public Reserva(Integer codigo, Date fechaInicio, Date fechaFin, Integer matricula,
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
