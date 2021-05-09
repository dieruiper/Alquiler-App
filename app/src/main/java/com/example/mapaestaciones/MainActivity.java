package com.example.mapaestaciones;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        }

    /*public void Actividad_Maps (View view){
        Intent mapas_adelante = new Intent(getApplicationContext(), Mapa_con_las_oficinas.class);
        startActivity(mapas_adelante);
    }*/
    public void Actividad_Reserva_adelante (View view){
        Intent reserva_adelante = new Intent(getApplicationContext(), Activity_Reservar_Vehiculos.class);
        startActivity(reserva_adelante);
    }
    public void Actividad_Acciones_BD (View view){
        Intent acciones_bd_adelante = new Intent(getApplicationContext(), Activity_Acciones_BD.class);
        startActivity(acciones_bd_adelante);
    }
    public void Actividad_Ver_Vehiculos (View view){
        Intent ver_vehiculos_adelante = new Intent(getApplicationContext(), Activity_Ver_Vehiculos.class);
        startActivity(ver_vehiculos_adelante);
    }
    public void Actividad_Acciones_Oficinas_BD (View view){
        Intent acciones_oficinas_bd_adelante = new Intent(getApplicationContext(), Activity_Acciones_Oficinas_BD.class);
        startActivity(acciones_oficinas_bd_adelante);
    }
    public void Actividad_Ver_Oficinas (View view) {
        Intent ver_oficinas_adelante = new Intent(getApplicationContext(), Activity_Ver_Oficinas.class);
        startActivity(ver_oficinas_adelante);
    }
}