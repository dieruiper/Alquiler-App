package com.example.mapaestaciones;

import com.google.android.gms.maps.model.LatLng;

import java.sql.Date;

public class Oficina {

    private Double latitude;
    private Double longitude;
    private String nombre;

    public Oficina(Double latitude, Double longitude, String nombre){
        this.nombre = nombre;
        this.latitude = latitude;
        this.longitude = longitude;

    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public Double getLatitude(){
        return latitude;
    }

    public Double getLongitude(){
        return longitude;
    }
    public String toString(){
        return "Nombre: "+ nombre +" - Latitud: "+ latitude +" - Longitud: "+ longitude;
    }
}
