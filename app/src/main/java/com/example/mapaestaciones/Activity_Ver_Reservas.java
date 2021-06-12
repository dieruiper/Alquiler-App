package com.example.mapaestaciones;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Activity_Ver_Reservas extends AppCompatActivity {
    TextView r_nombre, r_apellidos, r_dni, r_telefono, r_email;
    private ListView lv1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__ver__reservas);
        r_nombre = findViewById(R.id.tv_nombre);
        r_apellidos = findViewById(R.id.tv_apellidos);
        r_dni = findViewById(R.id.tv_dni);
        r_telefono = findViewById(R.id.tv_telefono);
        r_email = findViewById(R.id.tv_email);

        lv1=(ListView)findViewById(R.id.lv1);


        SharedPreferences preferences = getSharedPreferences("credenciales", Context.MODE_PRIVATE);
        r_nombre.setText(preferences.getString("nombre", ""));
        r_apellidos.setText(preferences.getString("apellidos", ""));
        r_dni.setText(preferences.getString("dni", ""));
        r_telefono.setText(preferences.getString("telefono", ""));
        r_email.setText(preferences.getString("email", ""));



    }
    public void Consultar_Reservas(View view){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracion", null, 1);
        SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();

        String dni = r_dni.getText().toString();
        ArrayList<String> lista = new ArrayList<String>();

        if(!dni.isEmpty()){
            Cursor fila = BaseDeDatos.rawQuery
                    ("select * from reservas where dni ='" + dni + "'", null);
            if(fila.moveToFirst()){
                do{
                    Reserva reserva = new Reserva(fila.getInt(0),fila.getString(1),fila.getString(2),
                            fila.getString(3),fila.getString(4),fila.getString(5));
                    lista.add(reserva.toString());
                }while (fila.moveToNext());
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.list_item_verreservas,lista);
                lv1.setAdapter(adapter);
            }else{
                Toast.makeText(this, "No hay ninguna reserva", Toast.LENGTH_SHORT).show();
                BaseDeDatos.close();
            }
        }else{
            Toast.makeText(this, "Algo salió mal", Toast.LENGTH_LONG).show();
        }
    }
}