package com.example.mapaestaciones;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class Activity_Ver_Vehiculos extends AppCompatActivity {

    private TextView tv1;
    private ListView lv1;

    private String nombres []={"Pepe", "José", "Aantonio", "Diego", "Rafael", "Adrian", "Conchi"};
    private String edades []={"58", "24", "24", "24", "41", "31", "59"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__ver__vehiculos);

        tv1=(TextView)findViewById(R.id.tv1);
        lv1=(ListView)findViewById(R.id.lv1);

        //Para poder añadir los elemetos del vector dentro del ListView
        ArrayAdapter <String> adapter = new ArrayAdapter<String>(this, R.layout.list_item_vervehiculos, nombres);
        lv1.setAdapter(adapter);

        lv1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                tv1.setText("La edad de " + lv1.getItemAtPosition(position) + " es " + edades[position] + " años");
            }
        });
    }
}