package com.example.mapaestaciones;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        }

    public void Actividad_Maps (View view){
        Intent mapas_adelante = new Intent(getApplicationContext(), Mapa_con_las_oficinas.class);
        startActivity(mapas_adelante);
    }
    public void Actividad_Reserva_adelante (View view){
        Intent reserva_adelante = new Intent(getApplicationContext(), Activity_Reservar_Vehiculos.class);
        startActivity(reserva_adelante);
    }
    public void Actividad_Ver_Vehiculos (View view){
        Intent vehiculos_adelante = new Intent(getApplicationContext(), Activity_Ver_Vehiculos.class);
        startActivity(vehiculos_adelante);
    }
}