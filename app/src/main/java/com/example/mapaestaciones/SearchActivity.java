package com.example.mapaestaciones;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {

    SearchView txtBuscar;
    private VehiculoAdapter vAdapter;
    TextView tv_lugar_recogida,tv_fecha_recogida,tv_fecha_entrega;
    String efecha_resumen,efecha2_resumen,elugar_resumen,fechaInicio,fechaFin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        txtBuscar=findViewById(R.id.txtBuscar);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);

        Toast.makeText(this, "Por favor, seleccione un vehículo", Toast.LENGTH_LONG).show();

        Intent me=getIntent();
        efecha_resumen=me.getStringExtra("fecha_inicio");
        efecha2_resumen=me.getStringExtra("fecha_fin");
        elugar_resumen=me.getStringExtra("lugar_recogida");

        tv_lugar_recogida = findViewById(R.id.elugar_devolucion_resumen);
        tv_lugar_recogida.setText(elugar_resumen);
        tv_fecha_recogida=findViewById(R.id.fecha_recogida_resumen);
        tv_fecha_recogida.setText(efecha_resumen);
        tv_fecha_entrega=findViewById(R.id.efecha2_resumen);
        tv_fecha_entrega.setText(efecha2_resumen);

        SharedPreferences p = getSharedPreferences("adminReserva", Context.MODE_PRIVATE);
        SharedPreferences.Editor obj_editor = p.edit();
        obj_editor.putString("fecha_recogida",efecha_resumen);
        obj_editor.putString("fecha_entrega",efecha2_resumen);
        obj_editor.putString("lugar_recogida",elugar_resumen);
        obj_editor.commit();

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

        vAdapter = new VehiculoAdapter(disponibles);
        recyclerView.setAdapter(vAdapter);

        txtBuscar.setOnQueryTextListener(this);
    }

    public void verDetalles(View view, String m){
        Intent r = new Intent(this, Activity_Detalles.class);
        r.putExtra("matricula_select", m);
        startActivity(r);
    }

    public void volver_reserva_vehiculos(View view){
        finish();
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        vAdapter.filtrado(newText);
        return false;
    }

}