package com.example.mapaestaciones;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class Activity_Reservar_Vehiculos extends AppCompatActivity implements View.OnClickListener{

    Button btn_fecha_inicio,btn_fecha_fin, btn_lugar_recogida,btn_lugar_entrega;
    EditText et_fecha_inicio,et_fecha_fin, et_lugar_recogida,et_lugar_entrega;
    String title,title2;
    private int dia,mes,ano;
    /*

     */
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservar_vehiculos);

        et_lugar_entrega = (EditText) findViewById(R.id.elugar2);
        et_lugar_recogida = (EditText) findViewById(R.id.elugar);
        btn_fecha_inicio=(Button) findViewById(R.id.bfecha);
        et_fecha_inicio=(EditText)findViewById(R.id.efecha);
        btn_fecha_fin=(Button) findViewById(R.id.bfecha2);
        et_fecha_fin=(EditText)findViewById(R.id.efecha2);
        btn_lugar_recogida = (Button)findViewById(R.id.blugar);
        btn_lugar_entrega = (Button)findViewById(R.id.blugar2);

        title2 = getIntent().getStringExtra("title");
        title = getIntent().getStringExtra("title");
        et_lugar_recogida.setText(title);
        et_lugar_entrega.setText(title2);

    }
    public void Actividad_Reserva_atras(View view){
        Intent reserva_veh_atras = new Intent(this,MainActivity.class);
        startActivity(reserva_veh_atras);
    }

    /*Fecha*/
    @Override
    public void onClick(View v) {

        if (v == btn_fecha_inicio) {
            final Calendar c = Calendar.getInstance();
            dia = c.get(Calendar.DAY_OF_MONTH);
            mes = c.get(Calendar.MONTH);
            ano = c.get(Calendar.YEAR);
            DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    et_fecha_inicio.setText(year + "/" + (monthOfYear + 1) + "/" + dayOfMonth);

                }
            }
                    , ano, mes, dia);
            datePickerDialog.show();
        }

        if (v == btn_fecha_fin) {
            final Calendar c = Calendar.getInstance();
            dia = c.get(Calendar.DAY_OF_MONTH);
            mes = c.get(Calendar.MONTH);
            ano = c.get(Calendar.YEAR);
            DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    et_fecha_fin.setText(year + "/" + (monthOfYear + 1) + "/" + dayOfMonth);

                }
            }
                    , ano, mes, dia);
            datePickerDialog.show();
        }
    }

    public void Actividad_Maps (View view){

        Intent mapas_adelante = new Intent(getApplicationContext(), Mapa_con_las_oficinas.class);
        startActivityForResult(mapas_adelante,100);
    }

   /* public void Actividad_Maps2 (View view){

        Intent mapas_adelante = new Intent(getApplicationContext(), Mapa_con_las_oficinas.class);
        startActivityForResult(mapas_adelante,101);
    }*/

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 100) // Debe coincidir con "startActivityForResult"
        {
        // Recuperamos el valor
            //short code = (short) data.getExtras().get("mi_return");
            Bundle tap = data.getExtras();
            double lng=(double) data.getExtras().get("lng");

            double lat=data.getDoubleExtra("lat",0.0);
            TextView elugar =findViewById(R.id.elugar);
            elugar.setText("Long: " + lng + "Lat :" + lat);

        }

    }


    public void Consultar_Disponibilidad(View view) {
        Intent myIntent=new Intent(Activity_Reservar_Vehiculos.this, Activity_Seleccionar_Vehiculo.class);

        String s_efecha =et_fecha_inicio.getText().toString().trim();
        String s_efecha2 =et_fecha_fin.getText().toString().trim();
        String s_elugar = et_lugar_entrega.getText().toString().trim();
        String s_elugar2 = et_lugar_recogida.getText().toString().trim();

        myIntent.putExtra("es_efecha", s_efecha);
        myIntent.putExtra("es_efecha2",  s_efecha2);
        myIntent.putExtra("es_elugar",  s_elugar);

        startActivity(myIntent);
    }
}
