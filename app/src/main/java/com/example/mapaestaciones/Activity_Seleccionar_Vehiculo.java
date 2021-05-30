package com.example.mapaestaciones;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.sql.Date;
import java.util.ArrayList;

public class Activity_Seleccionar_Vehiculo extends AppCompatActivity {
    private ListView lv5;
    TextView tv_lugar_recogida,tv_lugar_entrega,tv_fecha_recogida,tv_fecha_entrega;
    String efecha_resumen,efecha2_resumen,elugar_resumen,fechaInicio,fechaFin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_seleccionar_vehiculo);
    lv5=(ListView)findViewById(R.id.lv5);
        Intent me=getIntent();
        efecha_resumen=me.getStringExtra("es_efecha");
        efecha2_resumen=me.getStringExtra("es_efecha2");
        elugar_resumen=me.getStringExtra("es_elugar");
        //String elugar2_resumen=me.getStringExtra("es_elugar2");

        tv_lugar_entrega = findViewById(R.id.elugar_recogida_resumen);
        //tv_lugar_entrega.setText(elugar2_resumen);
        tv_lugar_recogida = findViewById(R.id.elugar_devolucion_resumen);
        tv_lugar_recogida.setText(elugar_resumen);
        tv_fecha_recogida=findViewById(R.id.fecha_recogida_resumen);
        tv_fecha_recogida.setText(efecha_resumen);
        tv_fecha_entrega=findViewById(R.id.efecha2_resumen);
        tv_fecha_entrega.setText(efecha2_resumen);

    //Date fechaInicio = Date.valueOf(efecha_resumen);
    //Date fechaFin = Date.valueOf(efecha2_resumen);
    String splitInicio1 = efecha_resumen.substring(0,4);
        String splitInicio2 = efecha_resumen.substring(5,7);
        String splitInicio3 = efecha_resumen.substring(8);
        String splitFin1 = efecha2_resumen.substring(0,4);
        String splitFin2 = efecha2_resumen.substring(5,7);
        String splitFin3 = efecha2_resumen.substring(8);

    String fechaInicio = splitInicio1+"-"+splitInicio2+"-"+splitInicio3;
    String fechaFin = splitFin1+"-"+splitFin2+"-"+splitFin3;
    String nombreOficina = elugar_resumen;
    ArrayList<String> ocupados = new ArrayList<String>();
    ArrayList<String> todos_vehiculos = new ArrayList<>();
    ArrayList<String> disponibles = new ArrayList<>();
    AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracion", null, 1);
    SQLiteDatabase BaseDeDatos = admin.getReadableDatabase();
    //Cursor fila = BaseDeDatos.rawQuery("select * from reservas ", null);
    Cursor fila = BaseDeDatos.rawQuery("select * from reservas where nombreOficina = '"+nombreOficina+"' and ((fechaInicio between '"+fechaInicio+"' and '"+fechaFin+"') or (fechaFin between '"+fechaInicio+"' and '"+fechaFin+"'))" ,null);
        if(fila.moveToFirst()){
        do{
            Reserva reserva = new Reserva(fila.getInt(0),fila.getString(1),
                    fila.getString(2),fila.getString(3),fila.getString(4),fila.getString(5));
            ocupados.add(reserva.getMatricula());
        }while (fila.moveToNext());
    }
    Cursor todos = BaseDeDatos.rawQuery("select * from vehiculos where nombreOficina = '"+nombreOficina+"'", null);
        if(todos.moveToFirst()) {
        do {
            Vehiculo vehiculo = new Vehiculo(todos.getString(0), todos.getString(1),
                    todos.getString(2), todos.getString(3),
                    todos.getString(4), todos.getDouble(5),
                    todos.getString(6));
            todos_vehiculos.add(vehiculo.getMatricula());
        } while (todos.moveToNext());
    }
        if(ocupados.isEmpty()==true) {
            for (int i = 0; i < todos_vehiculos.size(); i++) {
                disponibles.add(todos_vehiculos.get(i));
            }
        }else {
            for (int i = 0; i < todos_vehiculos.size(); i++) {
                for (int j = 0; j < ocupados.size(); j++) {
                    if ((todos_vehiculos.get(i).equals(ocupados.get(j))) == false) {
                        disponibles.add(todos_vehiculos.get(i));
                    }
                }
            }
        }
    ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.list_item_veroficinas,disponibles);
        lv5.setAdapter(adapter);

    }

    public void volver_reserva_vehiculos(View view){
        finish();
    }

    public void confirmar_Reserva(View view){
        Intent i = new Intent(this, Activity_Confirmar_Reserva.class );
       // String c_efecha =efecha_resumen;
        //String c_efecha2 =efecha2_resumen;
        String c_elugar = elugar_resumen;
        //String s_elugar2 = et_lugar_recogida.getText().toString().trim();

        //i.putExtra("es_efecha", c_efecha);
        //i.putExtra("es_efecha2",  c_efecha2);
        i.putExtra("es_elugar",  c_elugar);
        startActivity(i);
    }

}