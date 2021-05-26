package com.example.mapaestaciones;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

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

        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracion", null, 1);
        SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();

        String nombreOficina = elugar_resumen;
        ArrayList<Vehiculo> vehiculos_no_disponibles = new ArrayList<>();
        ArrayList<Vehiculo> vehiculos = new ArrayList<>();
        ArrayList<Vehiculo> vehiculos_disponibles = new ArrayList<>();
        String fechaInicio = efecha_resumen;
        String fechaFin = efecha2_resumen;

        /*
        Cursor disponibles = BaseDeDatos.rawQuery("select fechaInicio,fechaFin from reservas where nombreOficina = '"+nombreOficina+"' and reservas.fechaInicio not between reservas.fechaInicio ='"+fechaInicio+"' and reservas.fechaFin = '"+fechaFin+"'",null);
        if(disponibles.moveToFirst()){
            fechaInicio = disponibles.getString(disponibles.getColumnIndex("fechaInicio"));
            fechaFin = disponibles.getString(disponibles.getColumnIndex("fechaFin"));
        }
        */


        //Cursor fila = BaseDeDatos.rawQuery("select * from vehiculos where nombreOficina = '"+nombreOficina+"'" , null);
        //Cursor fila = BaseDeDatos.rawQuery("select * from vehiculos left join reservas on vehiculos.nombreOficina=reservas.nombreOficina where vehiculos.nombreOficina='"+nombreOficina+"' is null not between reservas.fechaInicio and reservas.fechaFin" , null);
        //Cursor fila = BaseDeDatos.rawQuery("select * from vehiculos where nombreOficina = '"+nombreOficina+"' in (select  nombreOficina from vehiculos) and nombreOficina in (select nombreOficina from reservas where nombreOficina not between fechaInicio and fechaFin )" , null);

        //modificado solo consulta en reserva
        Cursor no_disponibles = BaseDeDatos.rawQuery("select fechaInicio,fechaFin from reservas where nombreOficina = '"+nombreOficina+"' and reservas.fechaInicio not between reservas.fechaInicio ='"+fechaInicio+"' and reservas.fechaFin = '"+fechaFin+"'",null);
        if(no_disponibles.moveToFirst()){
            do{
                Vehiculo vehiculo = new Vehiculo(no_disponibles.getString(0),no_disponibles.getString(1),
                        no_disponibles.getString(2), no_disponibles.getString(3),
                        no_disponibles.getString(4),no_disponibles.getDouble(5),
                        no_disponibles.getString(6));
                vehiculos_no_disponibles.add(vehiculo);
            }while (no_disponibles.moveToNext());
        }

        //obtengo todos los vehículos
        Cursor todos = BaseDeDatos.rawQuery("select * from vehiculos", null);
        if(todos.moveToFirst()){
            do{
                Vehiculo vehiculo = new Vehiculo(todos.getString(0),todos.getString(1),
                        todos.getString(2), todos.getString(3),
                        todos.getString(4),todos.getDouble(5),
                        todos.getString(6));
                vehiculos.add(vehiculo);
            }while (todos.moveToNext());
        }
        BaseDeDatos.close();

        //ya tengo los disponibles y los no disponibles en dos listas, comparos sus pk(son las matriculas) si son distintas inserto los vehículos en una lista de disponibles
        for (int i=0;i < vehiculos.size();i++){
            for(int j=0;j < vehiculos_no_disponibles.size();j++){

                if(!(vehiculos.get(i).getMatricula().equals(vehiculos_no_disponibles.get(j).getMatricula()))){
                    vehiculos_disponibles.add(vehiculos.get(i));
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
        vAdapter = new VehiculoAdapter(vehiculos_disponibles);
        recyclerView.setAdapter(vAdapter);
    }

    public void volver_reserva_vehiculos(View view){
        finish();
    }

    public void confirmar_Reserva(View view){
        Intent i = new Intent(this, Activity_Confirmar_Reserva.class );
        String c_efecha =efecha_resumen;
        String c_efecha2 =efecha2_resumen;
        String c_elugar = elugar_resumen;
        //String s_elugar2 = et_lugar_recogida.getText().toString().trim();

        i.putExtra("es_efecha", c_efecha);
        i.putExtra("es_efecha2",  c_efecha2);
        i.putExtra("es_elugar",  c_elugar);
        startActivity(i);
    }

}