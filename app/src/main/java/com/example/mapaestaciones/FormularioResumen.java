package com.example.mapaestaciones;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Date;

public class FormularioResumen extends AppCompatActivity {
    EditText formres_nombre,formres_apellidos,formres_correo,formres_fecha,formres_comentarios;
    private int dia,mes,ano;
    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_resumen);
        formres_nombre=(EditText) findViewById(R.id.formres_nombre);
        formres_apellidos =(EditText) findViewById(R.id.formres_apellidos);
        formres_correo=(EditText) findViewById(R.id.formres_correo);
        formres_fecha=(EditText) findViewById(R.id.formres_fecha);
        formres_comentarios= (EditText) findViewById(R.id.formres_comentarios);


    }
    public void onClick(View v) {

        if (v == formres_fecha) {
            final Calendar c = Calendar.getInstance();
            dia = c.get(Calendar.DAY_OF_MONTH);
            mes = c.get(Calendar.MONTH);
            ano = c.get(Calendar.YEAR);
            DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    formres_fecha.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);

                }
            }
                    , dia, mes, ano);
            datePickerDialog.show();
        }
    }
    public void volver_reserva_vehiculos(View view){

        //Intent volver_reserva_vehiculos = new Intent(this,Activity_Reservar_Vehiculos.class );
        //startActivity(volver_reserva_vehiculos);
        finish();
    }
}