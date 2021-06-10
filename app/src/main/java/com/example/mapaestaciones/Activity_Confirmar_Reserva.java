package com.example.mapaestaciones;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Activity_Confirmar_Reserva extends AppCompatActivity {
    TextView r_nombre, r_apellidos, r_dni, r_telefono, r_email, r_lugar, r_fechaInicio, r_fechaFin, r_matricula;
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
        r_matricula = findViewById(R.id.matricula_conf);

        Intent i = getIntent();
        r_matricula.setText(i.getStringExtra("matricula_select"));

        SharedPreferences res = getSharedPreferences("adminReserva", Context.MODE_PRIVATE);
        r_lugar.setText(res.getString("lugar_recogida","AAA"));
        r_fechaInicio.setText(res.getString("fecha_recogida","AA"));
        r_fechaFin.setText(res.getString("fecha_entrega","AA"));

        SharedPreferences preferences = getSharedPreferences("credenciales", Context.MODE_PRIVATE);
        r_nombre.setText(preferences.getString("nombre", ""));
        r_apellidos.setText(preferences.getString("apellidos", ""));
        r_dni.setText(preferences.getString("dni", ""));
        r_telefono.setText(preferences.getString("telefono", ""));
        r_email.setText(preferences.getString("email", ""));

    }

    public void Confirmar_reserva(View view){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracion", null, 1);
        SQLiteDatabase BaseDeDatos = admin.getReadableDatabase();

        String fecha_inicio = r_fechaInicio.getText().toString();
        String fecha_fin = r_fechaFin.getText().toString();
        String matricula = r_matricula.getText().toString();
        String lugar = r_lugar.getText().toString();
        String dni = r_dni.getText().toString();

        if(!fecha_inicio.isEmpty() && !fecha_fin.isEmpty() && !matricula.isEmpty()
                && !lugar.isEmpty() && !dni.isEmpty()){

            ContentValues registro = new ContentValues();
            registro.put("fecha_inicio", fecha_inicio);
            registro.put("fecha_fin", fecha_fin);
            registro.put("matricula", matricula);
            registro.put("lugar", lugar);
            registro.put("dni", dni);

            double i = Math.random();
            int m = 2;
            Integer n = (int) i*m;

            BaseDeDatos.execSQL("insert into reservas values('"+n+"','"+fecha_inicio+"' ,'"+fecha_fin+"','"+matricula+"','"+lugar+"' ,'"+dni+"')");
            BaseDeDatos.close();

            Toast.makeText(this, "Su reserva ha sido realizada con éxito", Toast.LENGTH_SHORT).show();


        }else{
            Toast.makeText(this, "Debes rellenar todos los campos", Toast.LENGTH_LONG).show();
        }
    }
}