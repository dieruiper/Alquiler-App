package com.example.mapaestaciones;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Activity_Detalles extends AppCompatActivity {

    String matricula;
    Vehiculo vehiculo;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__detalles);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);

        Intent i = getIntent();
        matricula = i.getStringExtra("matricula_select");

        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracion", null, 1);
        SQLiteDatabase BaseDeDatos = admin.getReadableDatabase();
        ArrayList<Vehiculo> l = new ArrayList<>();

        Cursor c = BaseDeDatos.rawQuery("select * from vehiculos where matricula = '"+matricula+"'", null);
        if(c.moveToFirst()) {
            do {
                Vehiculo vehiculo = new Vehiculo(c.getString(0), c.getString(1),
                        c.getString(2), c.getString(3),
                        c.getString(4), c.getDouble(5),
                        c.getString(6));
                l.add(vehiculo);
            } while (c.moveToNext());
        }

        if (l.size() == 1){
            vehiculo = l.get(0);
            Toast.makeText(this, "Recuperando detalles del vehículo", Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(this, "Error al recuperar el vehículo", Toast.LENGTH_LONG).show();
        }
    }


}