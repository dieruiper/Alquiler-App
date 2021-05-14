package com.example.mapaestaciones;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView tv_credenciales;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_credenciales=(TextView)findViewById(R.id.tv_credenciales);

        boolean bandera = getValuePreference(getApplicationContext());

        if(bandera){
            Intent i = new Intent(getApplicationContext(), Activity_Login.class);
            startActivity(i);
        }

        SharedPreferences preferences = getSharedPreferences("credenciales", Context.MODE_PRIVATE);
        tv_credenciales.setText(preferences.getString("usuario", ""));
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
    public void Actividad_Acciones_Reservas_BD (View view){
        Intent acciones_reservas_bd_adelante = new Intent(getApplicationContext(), Activity_Acciones_Reservas_BD.class);
        startActivity(acciones_reservas_bd_adelante);
    }

    public void Actividad_Ver_Oficinas (View view) {
        Intent ver_oficinas_adelante = new Intent(getApplicationContext(), Activity_Ver_Oficinas.class);
        startActivity(ver_oficinas_adelante);
    }

    public void Actividad_Prueba_Login (View view) {
        Intent login = new Intent(getApplicationContext(), Activity_Login.class);
        startActivity(login);
    }

    public void Cerrar_Sesion (View view){
        saveValuePreference(getApplicationContext(), true);
        reiniciarActivity(this);
    }

    public static void reiniciarActivity(Activity actividad){
        Intent intent=new Intent();
        intent.setClass(actividad, actividad.getClass());
        actividad.startActivity(intent);
        actividad.finish();
    }

    public static void saveValuePreference(Context context, Boolean b) {
        SharedPreferences sesion = context.getSharedPreferences("sesion", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor;
        editor = sesion.edit();
        editor.putBoolean("logueado", b);
        editor.commit();
    }

    public boolean getValuePreference(Context context) {
        SharedPreferences preferences = context.getSharedPreferences("sesion", MODE_PRIVATE);
        return  preferences.getBoolean("logueado", true);
    }

}