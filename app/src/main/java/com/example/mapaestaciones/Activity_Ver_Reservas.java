package com.example.mapaestaciones;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.transition.Explode;
import android.transition.Fade;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Activity_Ver_Reservas extends AppCompatActivity {
    TextView r_nombre, r_apellidos, r_dni, r_telefono, r_email;
    EditText r_codigo;
    private listAdapter resevaAdapter;
    private ListView lv1;
    private CardView cv1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Explode explode = new Explode();
        explode.setDuration(MainActivity.DURATION_TRANSITION);
        explode.setInterpolator(new DecelerateInterpolator());

        getWindow().setEnterTransition(explode);



        setContentView(R.layout.activity__ver__reservas);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);

        r_nombre = findViewById(R.id.tv_nombre);
        r_apellidos = findViewById(R.id.tv_apellidos);
        r_dni = findViewById(R.id.tv_dni);
        r_telefono = findViewById(R.id.tv_telefono);
        r_email = findViewById(R.id.tv_email);

        //cv1 =(CardView) findViewById(R.id.cv1);
        lv1 = (ListView) findViewById(R.id.lv1);
        r_codigo = (EditText) findViewById(R.id.et_inserte_codigo);

        SharedPreferences preferences = getSharedPreferences("credenciales", Context.MODE_PRIVATE);
        r_nombre.setText(preferences.getString("nombre", ""));
        r_apellidos.setText(preferences.getString("apellidos", ""));
        r_dni.setText(preferences.getString("dni", ""));
        r_telefono.setText(preferences.getString("telefono", ""));
        r_email.setText(preferences.getString("email", ""));
    }

    public void Consultar_Reservas(View view) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracion", null, 1);
        SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();

        String dni = r_dni.getText().toString();
        ArrayList<String> lista = new ArrayList<>();
        lista.clear();

        if (!dni.isEmpty()) {
            Cursor fila = BaseDeDatos.rawQuery
                    ("select * from reservas where dni ='" + dni + "'", null);
            if (fila.moveToFirst()) {
                do {
                    Reserva reserva = new Reserva(fila.getInt(0), fila.getString(1), fila.getString(2),
                            fila.getString(3), fila.getString(4), fila.getString(5));
                    lista.add(reserva.toString());
                } while (fila.moveToNext());
                //ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.list_item_verreservas, lista);
                //lv1.setAdapter(adapter);

                //Array<String> array = new Array<String>(this, R.layout.list_item_verreservas, lista);
                //lv1.setAdapter(adapter);


            } else {
                Toast.makeText(this, "No hay ninguna reserva", Toast.LENGTH_SHORT).show();
                BaseDeDatos.close();
            }
        } else {
            Toast.makeText(this, "Algo salió mal", Toast.LENGTH_LONG).show();
        }
    }

    public void EliminarReserva(View view) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracion", null, 1);
        SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();

        String codigo = r_codigo.getText().toString();
        String dni = r_dni.getText().toString();

        if (!codigo.isEmpty()) {
            //int cantidad = BaseDeDatos.delete("vehiculos", "matricula=" + "matricula", null);
            BaseDeDatos.execSQL("DELETE FROM reservas WHERE codigo = '" + codigo + "' and (dni = '" + dni + "')");
            BaseDeDatos.close();
            r_codigo.setText("");
        } else {
            Toast.makeText(this, "No hay ninguna reserva", Toast.LENGTH_SHORT).show();
        }

        Intent i = new Intent(this, Activity_Ver_Reservas.class);
        startActivity(i);
        finish();

    }

}