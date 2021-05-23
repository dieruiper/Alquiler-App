package com.example.mapaestaciones;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class Activity_Confirmar_Reserva extends AppCompatActivity {
    String efecha_resumen,efecha2_resumen,elugar_resumen;
    TextView tv_lugar_recogida,tv_lugar_entrega,tv_fecha_recogida,tv_fecha_entrega;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__confirmar__reserva);


        Intent me=getIntent();
        efecha_resumen=me.getStringExtra("es_efecha");
        efecha2_resumen=me.getStringExtra("es_efecha2");
        elugar_resumen=me.getStringExtra("es_elugar");

        tv_lugar_recogida.setText(elugar_resumen);
        tv_fecha_recogida=findViewById(R.id.fecha_recogida_resumen);
        tv_fecha_recogida.setText(efecha_resumen);
        tv_fecha_entrega=findViewById(R.id.efecha2_resumen);
        tv_fecha_entrega.setText(efecha2_resumen);
    }
}