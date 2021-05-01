package com.example.mapaestaciones;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.Calendar;

public class RegistroUsuario extends AppCompatActivity {
    EditText formres_nombre,formres_apellidos,formres_correo,formres_fecha,formres_comentarios;
    Button bfechaRegistro;
    private int dia,mes,ano;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_usuario);
        formres_nombre=(EditText) findViewById(R.id.formres_nombre);
        formres_apellidos =(EditText) findViewById(R.id.formres_apellidos);
        //----------------¿HARÁ FILTRO DE FORMATO DE CORREO NO VÁLIDO?--------------
        formres_correo=(EditText) findViewById(R.id.formres_correo);
        //----------------FILTRO DE MAYOR DE 18--------------
        formres_fecha=(EditText) findViewById(R.id.formres_fecha);
        bfechaRegistro=(Button)findViewById(R.id.bfechaRegistro);
        //formres_comentarios= (EditText) findViewById(R.id.formres_comentarios);


    }
    public void onClick(View v) {

        if (v == bfechaRegistro) {
            final Calendar c = Calendar.getInstance();
            dia = c.get(Calendar.DAY_OF_MONTH);
            mes = c.get(Calendar.MONTH);
            ano = c.get(Calendar.YEAR);
            DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    bfechaRegistro.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);

                }
            }
                    , dia, mes, ano);
            datePickerDialog.show();
        }
    }
    public void volver_reserva_vehiculos(View view){

        Intent volver_reserva_vehiculos = new Intent(this,Activity_Reservar_Vehiculos.class );
        //if(formres_fecha.compa)
        startActivity(volver_reserva_vehiculos);


        //finish();
    }
}