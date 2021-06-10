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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.sql.Date;
import java.util.ArrayList;

public class Activity_Seleccionar_Vehiculo extends AppCompatActivity {
    private VehiculoAdapter vAdapter;
    TextView tv_lugar_recogida,tv_lugar_entrega,tv_fecha_recogida,tv_fecha_entrega;
    String efecha_resumen,efecha2_resumen,elugar_resumen,fechaInicio,fechaFin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seleccionar_vehiculo);

        Intent me=getIntent();
        efecha_resumen=me.getStringExtra("es_efecha");
        efecha2_resumen=me.getStringExtra("es_efecha2");
        elugar_resumen=me.getStringExtra("es_elugar");
        //String elugar2_resumen=me.getStringExtra("es_elugar2");

        //tv_lugar_entrega = findViewById(R.id.elugar_recogida_resumen);
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
        ArrayList<Reserva> ocupados = new ArrayList<>();
        ArrayList<Vehiculo> todos_vehiculos = new ArrayList<>();
        ArrayList<Vehiculo> disponibles = new ArrayList<>();

        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracion", null, 1);
        SQLiteDatabase BaseDeDatos = admin.getReadableDatabase();

        Cursor fila = BaseDeDatos.rawQuery("select * from reservas where nombreOficina = '"+nombreOficina+"' and ((fechaInicio between '"+fechaInicio+"' and '"+fechaFin+"') or (fechaFin between '"+fechaInicio+"' and '"+fechaFin+"'))" ,null);
        if(fila.moveToFirst()){
            do{
                Reserva reserva = new Reserva(fila.getInt(0),fila.getString(1),
                        fila.getString(2),fila.getString(3),fila.getString(4),fila.getString(5));
                ocupados.add(reserva);
            }while (fila.moveToNext());
        }

        Cursor todos = BaseDeDatos.rawQuery("select * from vehiculos where nombreOficina = '"+nombreOficina+"'", null);
        if(todos.moveToFirst()) {
            do {
                Vehiculo vehiculo = new Vehiculo(todos.getString(0), todos.getString(1),
                        todos.getString(2), todos.getString(3),
                        todos.getString(4), todos.getDouble(5),
                        todos.getString(6));
                todos_vehiculos.add(vehiculo);
            } while (todos.moveToNext());
        }

        if(ocupados.isEmpty()==true) {
            for (int i = 0; i < todos_vehiculos.size(); i++) {
                disponibles.add(todos_vehiculos.get(i));
            }
        }else {
            for (int i = 0; i < todos_vehiculos.size(); i++) {
                for (int j = 0; j < ocupados.size(); j++) {
                    if ((todos_vehiculos.get(i).getMatricula().equals(ocupados.get(j).getMatricula())) == false) {
                        disponibles.add(todos_vehiculos.get(i));
                    }
                }
            }
        }

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_vehiculos);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        //este es del método original
        /*vAdapter = new VehiculoAdapter(lista_vehiculos);
        recyclerView.setAdapter(vAdapter);
         */
        //quizás no lo muestre por el rView sino prueba con un list o algo parecido, mas sencillo de implementar
        vAdapter = new VehiculoAdapter(disponibles);
        recyclerView.setAdapter(vAdapter);
    }

    public void volver_reserva_vehiculos(View view){
        finish();
    }

    public void seleccionar_Vehiculo(View view){
        Intent i = new Intent(this, Activity_Confirmar_Reserva.class );

        String lugar = elugar_resumen;
        String fecha_inicio = efecha_resumen;
        String fecha_fin = efecha2_resumen;
        i.putExtra("lugar",  lugar);
        i.putExtra("fecha_inicio",  fecha_inicio);
        i.putExtra("fecha_fin", fecha_fin);

        startActivity(i);
    }

}