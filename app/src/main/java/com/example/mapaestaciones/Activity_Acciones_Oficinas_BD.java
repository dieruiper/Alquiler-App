package com.example.mapaestaciones;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Activity_Acciones_Oficinas_BD extends AppCompatActivity {
    private EditText et_nombre, et_lat, et_long,et_matricula;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__acciones__oficinas__b_d);
        et_nombre = (EditText)findViewById(R.id.txt_nombreOfi);
        et_lat = (EditText)findViewById(R.id.real_lat);
        et_long = (EditText)findViewById(R.id.real_long);
        et_matricula = (EditText)findViewById(R.id.txt_matricula);

    }

    //Para registrar una oficina
    public void RegistrarOficina(View view){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,"administracion",null,1);
        SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();

        String nombre = et_nombre.getText().toString();
        String latitud = et_lat.getText().toString();
        String longitud = et_long.getText().toString();
        String matricula = et_matricula.getText().toString();


        if(!nombre.isEmpty() && !latitud.isEmpty() && !longitud.isEmpty()  &&!matricula.isEmpty()){

            ContentValues registro = new ContentValues();
            registro.put("nombre", nombre);
            registro.put("latitud", latitud);
            registro.put("longitud", longitud);
            registro.put("matricula", matricula);


            BaseDeDatos.insert("oficinas", null, registro);

            BaseDeDatos.close();
            et_nombre.setText("");
            et_lat.setText("");
            et_long.setText("");
            et_matricula.setText("");


            Toast.makeText(this, "Registro exitoso", Toast.LENGTH_SHORT).show();


        }else{
            Toast.makeText(this, "Debes rellenar todos los campos", Toast.LENGTH_LONG).show();
        }
    }
    //Método para consultar
    public void BuscarOficina(View view){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracion", null, 1);
        SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();

        String nombre = et_nombre.getText().toString();

        if(!nombre.isEmpty()){
            Cursor fila = BaseDeDatos.rawQuery
                    ("select latitud, longitud, matricula  from oficinas where nombre ='" + nombre + "'", null);
            if(fila.moveToFirst()){
                et_lat.setText(fila.getString(0));
                et_long.setText(fila.getString(1));
                et_matricula.setText(fila.getString(2));

                BaseDeDatos.close();
            }else{
                Toast.makeText(this, "No existe la oficina", Toast.LENGTH_SHORT).show();
                BaseDeDatos.close();
            }
        }else{
            Toast.makeText(this, "Debes introducir el nombre de la oficina", Toast.LENGTH_LONG).show();
        }
    }

    public void ModificarOficina(View view){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracion", null, 1);
        SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();

        //variables donde almacenamos lo que el usuario ha modificado
        String nombre = et_nombre.getText().toString();
        String latitud = et_lat.getText().toString();
        String longitud = et_long.getText().toString();
        //String matricula = et_matricula.getText().toString();


        //validamos que todos los campos esten completos
        if(!nombre.isEmpty() && !latitud.isEmpty() && !longitud.isEmpty()){

            //Guardamos dentro de este objeto registro los valores que el usuario ha escrito dentro de estos campos
            ContentValues registro = new ContentValues();
            registro.put("nombre", nombre);
            registro.put("latitud", latitud);
            registro.put("longitud", longitud);
            //registro.put("matricula", matricula);


            //El método para modificar(update) retorna valores de tipo entero
            int cantidad = BaseDeDatos.update("oficinas", registro, "nombre="  + "nombre" , null);
            //ya hemos terminado de usar la BD por lo tanto debo cerrarla
            BaseDeDatos.close();

            //Para avisar al usuario si se ha modificado correctamente
            //Si cantidad == 1 es que se ha modificado el producto
            if(cantidad == 1){
                Toast.makeText(this, "Modificada con exito", Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(this, "La Oficina no existe",Toast.LENGTH_SHORT).show();
            }

        }else{
            Toast.makeText(this, "Debes rellenar todos los campos", Toast.LENGTH_LONG).show();
        }
    }

    public void Eliminar(View view){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracion", null, 1);
        SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();
        //Esto no le aparece a JOSECA
        String nombre = et_nombre.getText().toString();

        if(!nombre.isEmpty()){
            int cantidad = BaseDeDatos.delete("oficinas", "nombre=" + '"' + nombre + '"', null);
            BaseDeDatos.close();

            et_nombre.setText("");
            et_lat.setText("");
            et_long.setText("");

            if(cantidad == 1){
                Toast.makeText(this, "Eliminado con exito", Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(this, "El vehículo no existe",Toast.LENGTH_SHORT).show();
            }

        }else {
            Toast.makeText(this, "Debes introducir la matrícula del vehículo", Toast.LENGTH_LONG).show();

        }
    }


}