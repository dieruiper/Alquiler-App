package com.example.mapaestaciones;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ResumenReserva extends AppCompatActivity {
    private ListView lv_reserva;
    TextView tx_lugar_resumen,tx_lugar2_resumen, tx_efecha_resumen,tx_ehora_resumen,tx_efecha2_resumen,tx_ehora2_resumen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resumen_reserva);

        Intent me=getIntent();
        String efecha_resumen=me.getStringExtra("es_efecha");
        String efecha2_resumen=me.getStringExtra("es_efecha2");
        String elugar_resumen=me.getStringExtra("es_elugar");
        String elugar2_resumen=me.getStringExtra("es_elugar2");

        tx_lugar2_resumen = findViewById(R.id.elugar_recogida_resumen);
        tx_lugar2_resumen.setText(elugar2_resumen);

        tx_lugar_resumen = findViewById(R.id.elugar_devolucion_resumen);
        tx_lugar_resumen.setText(elugar_resumen);

        tx_efecha_resumen=findViewById(R.id.fecha_recogida_resumen);
        tx_efecha_resumen.setText(efecha_resumen);


        tx_efecha2_resumen=findViewById(R.id.efecha2_resumen);
        tx_efecha2_resumen.setText(efecha2_resumen);

        lv_reserva=(ListView)findViewById(R.id.lv_reserva);

        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracion", null, 1);
        SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();

        String nombre = elugar_resumen;
        ArrayList<String> lista = new ArrayList<String>();

        Cursor fila = BaseDeDatos.rawQuery("select * from vehiculos where nombre = '"+nombre+"'", null);
        if(fila.moveToFirst()){
            do{
                Vehiculo vehiculo = new Vehiculo(fila.getString(0),fila.getString(1),
                        fila.getString(2), fila.getString(3),
                        fila.getString(4),fila.getDouble(5));
                lista.add(vehiculo.toString());
            }while (fila.moveToNext());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.list_item_vervehiculos,lista);
        lv_reserva.setAdapter(adapter);
        BaseDeDatos.close();
    }

    public void volver_reserva_vehiculos(View view){

        //Intent volver_reserva_vehiculos = new Intent(this,Activity_Reservar_Vehiculos.class );
        //startActivity(volver_reserva_vehiculos);
        finish();
    }

    public void registroUsuario_Reserva(View view){

        Intent formularioresumen = new Intent(this, RegistroUsuario.class );
        /*String s2_efecha =tx_efecha_resumen.getText().toString().trim();
        String s2_ehora =tx_ehora_resumen.getText().toString().trim();
        String s2_efecha2 =tx_efecha2_resumen.getText().toString().trim();
        String s2_ehora2 =tx_ehora2_resumen.getText().toString().trim();
        formularioresumen.putExtra("es2_efecha", s2_efecha);
        formularioresumen.putExtra("es2_ehora", s2_ehora);
        formularioresumen.putExtra("es2_efecha2",  s2_efecha2);
        formularioresumen.putExtra("es2_ehora2", s2_ehora2);
        */
        startActivity(formularioresumen);
    }

}