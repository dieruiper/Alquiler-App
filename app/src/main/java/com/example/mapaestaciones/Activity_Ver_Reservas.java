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
    private ListView lv3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__ver__reservas);
        lv3=(ListView)findViewById(R.id.lv3);
        ArrayList<String> lista = new ArrayList<String>();

        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracion", null, 1);
        SQLiteDatabase BaseDeDatos = admin.getReadableDatabase();
        Cursor fila = BaseDeDatos.rawQuery("select * from reservas ", null);

        if(fila.moveToFirst()){
            do{
                Reserva reserva = new Reserva(fila.getInt(0),fila.getString(1),
                        fila.getString(2),fila.getString(3),fila.getString(4),fila.getString(5));
                lista.add(reserva.toString());
            }while (fila.moveToNext());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.list_item_veroficinas,lista);
        lv3.setAdapter(adapter);
        BaseDeDatos.close();
    }
}