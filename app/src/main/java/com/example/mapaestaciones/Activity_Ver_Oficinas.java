package com.example.mapaestaciones;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class Activity_Ver_Oficinas extends AppCompatActivity {

    private EditText tv2;
    private ListView lv2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__ver__oficinas);

        tv2=(EditText) findViewById(R.id.tv2);
        lv2=(ListView)findViewById(R.id.lv2);
        ArrayList<String> lista = new ArrayList<String>();

        //funciona
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracion", null, 1);
        SQLiteDatabase BaseDeDatos = admin.getReadableDatabase();

        Cursor fila = BaseDeDatos.rawQuery("select * from oficinas", null);
        if(fila.moveToFirst()){
            do{
                Oficina oficina = new Oficina(fila.getString(0),fila.getDouble(1),
                        fila.getDouble(2));
                lista.add(oficina.toString());
            }while (fila.moveToNext());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.list_item_veroficinas,lista);
        lv2.setAdapter(adapter);
        BaseDeDatos.close();

    }
/*
    //Consulta por matrícula
    public void Consult(View view){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracion", null, 1);
        SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();

        String matricula = tv1.getText().toString();
        lista.removeAll(lista);

        if(!matricula.isEmpty()){
            Cursor fila = BaseDeDatos.rawQuery
                    ("select * from vehiculos where matricula =" + matricula, null);
            if(fila.moveToFirst()){
                do{
                vehiculo = new Vehiculo(fila.getInt(0),fila.getString(1),
                        fila.getString(2), fila.getString(3),
                        fila.getString(4),fila.getDouble(5));
                lista.add(vehiculo.getToString());
                }while (fila.moveToNext());
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.list_item_vervehiculos,lista);
                lv1.setAdapter(adapter);
            }else{
                Toast.makeText(this, "No existe el vehiculo", Toast.LENGTH_SHORT).show();
                BaseDeDatos.close();
            }
        }else{
            Toast.makeText(this, "Debes introducir la matricula del vehiculo", Toast.LENGTH_LONG).show();
        }
    }*/

    //Consulta por marca
    public void ConsultarOficina(View view){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracion", null, 1);
        SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();

        String nombre = tv2.getText().toString();
        ArrayList<String> lista = new ArrayList<String>();

        if(!nombre.isEmpty()){
            Cursor fila = BaseDeDatos.rawQuery
                    ("select * from oficinas where nombre ='" + nombre + "'", null);
            if(fila.moveToFirst()){
                do{
                    Oficina oficina = new Oficina(fila.getString(0),fila.getDouble(1),
                            fila.getDouble(2));
                    lista.add(oficina.toString());
                }while (fila.moveToNext());
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.list_item_veroficinas,lista);
                lv2.setAdapter(adapter);
            }else{
                Toast.makeText(this, "No existe el vehiculo", Toast.LENGTH_SHORT).show();
                BaseDeDatos.close();
            }
        }else{
            Toast.makeText(this, "Debes introducir la matricula del vehiculo", Toast.LENGTH_LONG).show();
        }
    }

    /*
    public void Consult (View view){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracion", null, 1);
        SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();
        String marca = tv1.getText().toString();
        Cursor fila = BaseDeDatos.rawQuery("select * from vehiculos where marca=" + marca, null);
        if(fila.moveToFirst()){
            do{
                vehiculo = new Vehiculo(fila.getInt(0),fila.getString(1),
                        fila.getString(2), fila.getString(3),
                        fila.getString(4),fila.getDouble(5));
                lista.add(vehiculo.getMarca());
            }while (fila.moveToNext());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.list_item_vervehiculos,lista);
        lv1.setAdapter(adapter);
    }*/


}