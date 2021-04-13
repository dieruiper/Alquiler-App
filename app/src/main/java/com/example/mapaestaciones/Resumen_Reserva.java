package com.example.mapaestaciones;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Resumen_Reserva extends AppCompatActivity {

    TextView efecha_resumen,ehora_resumen,efecha2_resumen,ehora2_resumen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resumen__reserva);

        Intent me=getIntent();
        String efecha_resumen=me.getStringExtra("es_efecha");
        String ehora_resumen=me.getStringExtra("es_ehora");
        String efecha2_resumen=me.getStringExtra("es_efecha2");
        String ehora2_resumen=me.getStringExtra("es_ehora2");




    }
    public void volver_reserva_vehiculos(View view){

        Intent volver_reserva_vehiculos = new Intent(this,Activity_Reservar_Vehiculos.class );
        startActivity(volver_reserva_vehiculos);

    }
}