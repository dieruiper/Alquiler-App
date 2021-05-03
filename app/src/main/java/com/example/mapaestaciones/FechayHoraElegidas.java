package com.example.mapaestaciones;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class FechayHoraElegidas extends AppCompatActivity {

    TextView tx_efecha_resumen,tx_ehora_resumen,tx_efecha2_resumen,tx_ehora2_resumen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fechayhora_elegidas);

        Intent me=getIntent();
        String efecha_resumen=me.getStringExtra("es_efecha");
        String ehora_resumen=me.getStringExtra("es_ehora");
        String efecha2_resumen=me.getStringExtra("es_efecha2");
        String ehora2_resumen=me.getStringExtra("es_ehora2");

        tx_efecha_resumen=findViewById(R.id.fecha_recogida_resumen);
        tx_efecha_resumen.setText(efecha_resumen);

        tx_ehora_resumen=findViewById(R.id.ehora_resumen);
        tx_ehora_resumen.setText(ehora_resumen);

        tx_efecha2_resumen=findViewById(R.id.efecha2_resumen);
        tx_efecha2_resumen.setText(efecha2_resumen);

        tx_ehora2_resumen=findViewById(R.id.ehora2_resumen);
        tx_ehora2_resumen.setText(ehora2_resumen);



    }
    public void volver_reserva_vehiculos(View view){

        //Intent volver_reserva_vehiculos = new Intent(this,Activity_Reservar_Vehiculos.class );
        //startActivity(volver_reserva_vehiculos);
        finish();
    }

    public void registroUsuario_Reserva(View view){

        Intent formularioresumen = new Intent(this, RegistroUsuario.class );
        /*String s2_efecha =tx_efecha_resumen.getText().toString().trim();
        String s2_ehora =tx_ehora_resumen.getText().toString().trim();
        String s2_efecha2 =tx_efecha2_resumen.getText().toString().trim();
        String s2_ehora2 =tx_ehora2_resumen.getText().toString().trim();
        formularioresumen.putExtra("es2_efecha", s2_efecha);
        formularioresumen.putExtra("es2_ehora", s2_ehora);
        formularioresumen.putExtra("es2_efecha2",  s2_efecha2);
        formularioresumen.putExtra("es2_ehora2", s2_ehora2);
        */
        startActivity(formularioresumen);
    }

}