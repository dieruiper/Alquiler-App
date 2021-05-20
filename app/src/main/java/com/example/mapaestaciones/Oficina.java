package com.example.mapaestaciones;

import com.google.android.gms.maps.model.LatLng;

import java.sql.Date;

public class Oficina {

    private Double latitude;
    private Double longitude;
    private String nombreOficina;

    public Oficina( String nombreOficina,Double latitude, Double longitude){
        this.nombreOficina = nombreOficina;
        this.latitude = latitude;
        this.longitude = longitude;

    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public void setNombre(String nombreOficina) {
        this.nombreOficina = nombreOficina;
    }

    public String getNombre() {
        return nombreOficina;
    }

    public Double getLatitude(){
        return latitude;
    }

    public Double getLongitude(){
        return longitude;
    }

    public String toString(){
        return "Nombre: "+ nombreOficina +" - Latitud: "+ latitude +" - Longitud: "+ longitude;
    }
}
