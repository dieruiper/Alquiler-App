package com.example.mapaestaciones;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button btnOfiMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnOfiMap = (Button) findViewById(R.id.btn_ofi_map);

        btnOfiMap.setOnClickListener((v) -> {
            Intent intent = new Intent(getApplicationContext(), Mapa_con_las_oficinas.class);
            startActivity(intent);
        });
    }
}