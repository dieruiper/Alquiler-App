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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seleccionar_vehiculo);

        Intent me=getIntent();
        String efecha_resumen=me.getStringExtra("es_efecha");
        String efecha2_resumen=me.getStringExtra("es_efecha2");
        String elugar_resumen=me.getStringExtra("es_elugar");
        String elugar2_resumen=me.getStringExtra("es_elugar2");

        tv_lugar_entrega = findViewById(R.id.elugar_recogida_resumen);
        tv_lugar_entrega.setText(elugar2_resumen);
        tv_lugar_recogida = findViewById(R.id.elugar_devolucion_resumen);
        tv_lugar_recogida.setText(elugar_resumen);
        tv_fecha_recogida=findViewById(R.id.fecha_recogida_resumen);
        tv_fecha_recogida.setText(efecha_resumen);
        tv_fecha_entrega=findViewById(R.id.efecha2_resumen);
        tv_fecha_entrega.setText(efecha2_resumen);

        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracion", null, 1);
        SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();

        String nombreOficina = elugar_resumen;
        ArrayList<Vehiculo> lista_vehiculos = new ArrayList<>();

        Cursor fila = BaseDeDatos.rawQuery("select * from vehiculos where nombreOficina = '"+nombreOficina+"'", null);
        if(fila.moveToFirst()){
            do{
                Vehiculo vehiculo = new Vehiculo(fila.getString(0),fila.getString(1),
                        fila.getString(2), fila.getString(3),
                        fila.getString(4),fila.getDouble(5),
                        fila.getString(6));
                lista_vehiculos.add(vehiculo);
            }while (fila.moveToNext());
        }

        BaseDeDatos.close();

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_vehiculos);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        vAdapter = new VehiculoAdapter(lista_vehiculos);
        recyclerView.setAdapter(vAdapter);
    }

    public void volver_reserva_vehiculos(View view){
        finish();
    }

    public void confirmar_Reserva(View view){
        Intent i = new Intent(this, Activity_Confirmar_Reserva.class );
        startActivity(i);
    }

}