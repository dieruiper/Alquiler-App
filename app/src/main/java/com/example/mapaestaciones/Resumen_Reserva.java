package com.example.mapaestaciones;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Resumen_Reserva extends AppCompatActivity {

    TextView fecha_recogida_resumen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resumen__reserva);
        fecha_recogida_resumen=findViewById(R.id.efecha);
        Bundle miBundle=this.getIntent().getExtras();
        if(miBundle!=null){
            String nombre=miBundle.getString("fecha_recogida");
        }
    }
    public void volver_reserva_vehiculos(View view){

        Intent volver_reserva_vehiculos = new Intent(this,Activity_Reservar_Vehiculos.class );
        startActivity(volver_reserva_vehiculos);

    }
}