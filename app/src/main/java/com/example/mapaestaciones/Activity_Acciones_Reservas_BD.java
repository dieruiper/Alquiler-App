package com.example.mapaestaciones;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Activity_Acciones_Reservas_BD extends AppCompatActivity {
    private EditText et_codigo, et_fecha_inicio, et_fecha_fin, et_matricula, et_oficina, et_dni;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_acciones_reservas_bd);
        et_codigo = (EditText) findViewById(R.id.int_codigo_res);
        et_fecha_inicio = (EditText) findViewById(R.id.txt_fecha_inicio_res);
        et_fecha_fin = (EditText) findViewById(R.id.txt_fecha_vuelta_res);
        et_matricula = (EditText) findViewById(R.id.txt_matricula_res);
        et_oficina = (EditText) findViewById(R.id.txt_oficina_res);
        et_dni = (EditText) findViewById(R.id.txt_dni_res);
    }

    public void RegistrarReserva(View view) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracion", null, 1);
        SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();

        String codigo = et_codigo.getText().toString();
        String fechaInicio = et_fecha_inicio.getText().toString();
        String fechaFin = et_fecha_fin.getText().toString();
        String matricula = et_matricula.getText().toString();
        String nombreOficina = et_oficina.getText().toString();
        String dni = et_dni.getText().toString();

        if (!nombreOficina.isEmpty() && !fechaInicio.isEmpty() && !fechaFin.isEmpty() &&
                !matricula.isEmpty() && !codigo.isEmpty() && !dni.isEmpty()) {

            ContentValues registro = new ContentValues();
            registro.put("codigo", codigo);
            registro.put("fechaInicio", fechaInicio);
            registro.put("fechaFin", fechaFin);
            registro.put("matricula", matricula);
            registro.put("nombreOficina", nombreOficina);
            registro.put("dni", dni);

            //BaseDeDatos.insert("oficinas", null, registro);
            BaseDeDatos.execSQL("insert into reservas values('" + codigo + "','" + fechaInicio + "' ,'" + fechaFin + "','" + matricula + "'" +
                    ",'" + nombreOficina + "','" + dni + "')");
            BaseDeDatos.close();
            et_codigo.setText("");
            et_fecha_inicio.setText("");
            et_fecha_fin.setText("");
            et_matricula.setText("");
            et_oficina.setText("");
            et_dni.setText("");

            Toast.makeText(this, "Registro exitoso", Toast.LENGTH_SHORT).show();


        } else {
            Toast.makeText(this, "Debes rellenar todos los campos", Toast.LENGTH_LONG).show();
        }
    }

    public void BuscarReserva(View view) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracion", null, 1);
        SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();

        String codigo = et_codigo.getText().toString();

        if (!codigo.isEmpty()) {
            Cursor fila = BaseDeDatos.rawQuery
                    ("select fechaInicio, fechaFin, matricula, nombreOficina, dni from reservas where codigo ='" + codigo + "'", null);
            if (fila.moveToFirst()) {
                et_fecha_inicio.setText(fila.getString(0));
                et_fecha_fin.setText(fila.getString(1));
                et_matricula.setText(fila.getString(2));
                et_oficina.setText(fila.getString(3));
                et_dni.setText(fila.getString(4));

                BaseDeDatos.close();
            } else {
                Toast.makeText(this, "No existe el vehiculo", Toast.LENGTH_SHORT).show();
                BaseDeDatos.close();
            }
        } else {
            Toast.makeText(this, "Debes introducir la matricula del vehiculo", Toast.LENGTH_LONG).show();
        }
    }

    public void EliminarReserva(View view) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracion", null, 1);
        SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();

        String codigo = et_codigo.getText().toString();

        if (!codigo.isEmpty()) {
            //int cantidad = BaseDeDatos.delete("vehiculos", "matricula=" + "matricula", null);
            BaseDeDatos.execSQL("DELETE FROM reservas WHERE codigo = '" + codigo + "'");
            BaseDeDatos.close();

            et_codigo.setText("");
            et_fecha_inicio.setText("");
            et_fecha_fin.setText("");
            et_matricula.setText("");
            et_oficina.setText("");
            et_dni.setText("");
/*
            if(cantidad == 1){
                Toast.makeText(this, "Eliminado con exito", Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(this, "El vehículo no existe",Toast.LENGTH_SHORT).show();
            }

        }else {
            Toast.makeText(this, "Debes introducir la matrícula del vehículo", Toast.LENGTH_LONG).show();

        }*/
        }
    }

    public void ModificarReserva(View view) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracion", null, 1);
        SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();

        //variables donde almacenamos lo que el usuario ha modificado
        String codigo = et_codigo.getText().toString();
        String fechaInicio = et_fecha_inicio.getText().toString();
        String fechaFin = et_fecha_fin.getText().toString();
        String matricula = et_matricula.getText().toString();
        String nombreOficina = et_oficina.getText().toString();
        String dni = et_dni.getText().toString();
        //validamos que todos los campos esten completos

        if (!matricula.isEmpty() && !codigo.isEmpty() && !fechaInicio.isEmpty() &&
                !nombreOficina.isEmpty() && !fechaFin.isEmpty() && !dni.isEmpty()) {

            BaseDeDatos.execSQL("UPDATE reservas SET codigo= '" + codigo + "', fechaInicio='" + fechaInicio + "' " +
                    ",fechaFin = '" + fechaFin + "',matricula = '" + matricula + "'" +
                    ",nombreOficina = '" + nombreOficina + "', dni = '" + dni + "' " +
                    "WHERE codigo = '" + codigo + "'");
            /*
            //Guardamos dentro de este objeto registro los valores que el usuario ha escrito dentro de estos campos
            ContentValues registro = new ContentValues();
            registro.put("matricula", matricula);
            registro.put("categoria", categoria);
            registro.put("marca", marca);
            registro.put("modelo", modelo);
            registro.put("descripcion",descripcion);
            registro.put("precio",precio);
            registro.put("nombre",nombre);

            //El método para modificar(update) retorna valores de tipo entero
            int cantidad = BaseDeDatos.update("vehiculos", registro, "matricula=" + "matricula", null);
            //ya hemos terminado de usar la BD por lo tanto debo cerrarla
            */
            BaseDeDatos.close();
/*
            //Para avisar al usuario si se ha modificado correctamente
            //Si cantidad == 1 es que se ha modificado el producto
            if(cantidad == 1){
                Toast.makeText(this, "Modificado con exito", Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(this, "El vehiculo no existe",Toast.LENGTH_SHORT).show();
            }

        }else{
            Toast.makeText(this, "Debes rellenar todos los campos", Toast.LENGTH_LONG).show();
        */
        }
    }
}