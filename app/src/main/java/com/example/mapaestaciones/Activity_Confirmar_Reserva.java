package com.example.mapaestaciones;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
public class Activity_Confirmar_Reserva extends AppCompatActivity {
    TextView r_nombre, r_apellidos, r_dni, r_telefono, r_email, r_lugar, r_fechaInicio, r_fechaFin, r_vehiculo;
    Button btn_confirmarReserva;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__confirmar__reserva);

        r_nombre = findViewById(R.id.nombre_conf);
        r_apellidos = findViewById(R.id.apellidos_conf);
        r_dni = findViewById(R.id.dni_conf);
        r_telefono = findViewById(R.id.telefono_conf);
        r_email = findViewById(R.id.email_conf);
        r_lugar = findViewById(R.id.lugar_conf);
        r_fechaInicio = findViewById(R.id.fechaInicio_conf);
        r_fechaFin = findViewById(R.id.fechaFin_conf);
        r_vehiculo = findViewById(R.id.vehiculo_conf);

        Intent i = getIntent();
        r_lugar.setText(i.getStringExtra("lugar"));
        r_fechaInicio.setText(i.getStringExtra("fecha_inicio"));
        r_fechaFin.setText(i.getStringExtra("fecha_fin"));

        SharedPreferences preferences = getSharedPreferences("credenciales", Context.MODE_PRIVATE);
        r_nombre.setText(preferences.getString("nombre", ""));
        r_apellidos.setText(preferences.getString("apellidos", ""));
        r_dni.setText(preferences.getString("dni", ""));
        r_telefono.setText(preferences.getString("telefono", ""));
        r_email.setText(preferences.getString("email", ""));

    }

    public void Confirmar_reserva(View view){
      
    }
}