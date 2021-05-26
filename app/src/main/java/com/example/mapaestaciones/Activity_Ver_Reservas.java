package com.example.mapaestaciones;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Date;

public class Activity_Ver_Reservas extends AppCompatActivity {
    private ListView lv3,lv4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__ver__reservas);
        lv3=(ListView)findViewById(R.id.lv3);
        lv4=(ListView)findViewById(R.id.lv4);
        ArrayList<String> lista = new ArrayList<String>();
        ArrayList<String> todos_vehiculos = new ArrayList<>();
        ArrayList<String> disponibles = new ArrayList<>();
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracion", null, 1);
        SQLiteDatabase BaseDeDatos = admin.getReadableDatabase();
        //Cursor fila = BaseDeDatos.rawQuery("select * from reservas ", null);
        Cursor fila = BaseDeDatos.rawQuery("select * from reservas where nombreOficina = 'Sevilla' and ((fechaInicio between '2021-05-24' and '2021-05-27') or (fechaFin between '2021-05-24' and '2021-05-27'))" ,null);
        if(fila.moveToFirst()){
            do{
                Reserva reserva = new Reserva(fila.getInt(0),fila.getString(1),
                        fila.getString(2),fila.getString(3),fila.getString(4),fila.getString(5));
                lista.add(reserva.getMatricula());
            }while (fila.moveToNext());
        }
        Cursor todos = BaseDeDatos.rawQuery("select * from vehiculos where nombreOficina = 'Sevilla'", null);
        if(todos.moveToFirst()) {
            do {
                Vehiculo vehiculo = new Vehiculo(todos.getString(0), todos.getString(1),
                        todos.getString(2), todos.getString(3),
                        todos.getString(4), todos.getDouble(5),
                        todos.getString(6));
                todos_vehiculos.add(vehiculo.getMatricula());
            } while (todos.moveToNext());
        }
        for (int i=0;i < todos_vehiculos.size();i++){
            for(int j=0;j < lista.size();j++){

                if(!(todos_vehiculos.get(i).equals(lista.get(j)))){
                    disponibles.add(todos_vehiculos.get(i));
                }
            }
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.list_item_veroficinas,lista);
        lv3.setAdapter(adapter);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this,R.layout.list_item_veroficinas,disponibles);
        lv4.setAdapter(adapter2);
        BaseDeDatos.close();
    }
}