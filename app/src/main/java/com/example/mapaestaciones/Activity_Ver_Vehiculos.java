package com.example.mapaestaciones;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Activity_Ver_Vehiculos extends AppCompatActivity {

    private EditText et_matricula,et_categoria, et_marca, et_modelo,et_descripcion, et_precio;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__ver__vehiculos);
        et_matricula = (EditText)findViewById(R.id.int_matricula);
        et_categoria = (EditText)findViewById(R.id.txt_categoria);
        et_marca = (EditText)findViewById(R.id.txt_marca);
        et_modelo = (EditText)findViewById(R.id.txt_modelo);
        et_descripcion = (EditText)findViewById(R.id.txt_descripcion);
        et_precio = (EditText)findViewById(R.id.real_precio);
    }

    //Método para dar de alta los productos
    public void Registrar(View view){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,"administracion",null,1);
        SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();

        String matricula = et_matricula.getText().toString();
        String categoria = et_categoria.getText().toString();
        String marca = et_marca.getText().toString();
        String modelo = et_modelo.getText().toString();
        String descripcion = et_descripcion.getText().toString();
        String precio = et_precio.getText().toString();

        if(!matricula.isEmpty() && !categoria.isEmpty() && !marca.isEmpty()&& !descripcion.isEmpty()
                && !modelo.isEmpty() && !descripcion.isEmpty() && !precio.isEmpty()){

            ContentValues registro = new ContentValues();
            registro.put("matricula", matricula);
            registro.put("categoria", categoria);
            registro.put("marca", marca);
            registro.put("modelo", modelo);
            registro.put("descripcion",descripcion);
            registro.put("precio",precio);

            BaseDeDatos.insert("vehiculos", null, registro);

            BaseDeDatos.close();
            et_matricula.setText("");
            et_categoria.setText("");
            et_marca.setText("");
            et_modelo.setText("");
            et_descripcion.setText("");
            et_precio.setText("");

            Toast.makeText(this, "Registro exitoso", Toast.LENGTH_SHORT).show();


        }else{
            Toast.makeText(this, "Debes rellenar todos los campos", Toast.LENGTH_LONG).show();
        }
    }

    //Método para consultar
    public void Buscar(View view){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracion", null, 1);
        SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();

        String matricula = et_matricula.getText().toString();
        String descripcion = et_descripcion.getText().toString();

        if(!matricula.isEmpty()){
            Cursor fila = BaseDeDatos.rawQuery
                    ("select categoria, marca, modelo, descripcion, precio from vehiculos where matricula =" + matricula, null);
            if(fila.moveToFirst()){
                et_categoria.setText(fila.getString(0));
                et_marca.setText(fila.getString(1));
                et_modelo.setText(fila.getString(2));
                et_descripcion.setText(fila.getString(3));
                et_precio.setText(fila.getString(4));
                BaseDeDatos.close();
            }else{
                Toast.makeText(this, "No existe el vehiculo", Toast.LENGTH_SHORT).show();
                BaseDeDatos.close();
            }
        }else{
            Toast.makeText(this, "Debes introducir la matricula del vehiculo", Toast.LENGTH_LONG).show();
        }
    }

    //Método para eliminar un producto o articulo
    public void Eliminar(View view){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracion", null, 1);
        SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();

        String matricula = et_matricula.getText().toString();

        if(!matricula.isEmpty()){
            int cantidad = BaseDeDatos.delete("vehiculos", "matricula=" + matricula, null);
            BaseDeDatos.close();

            et_matricula.setText("");
            et_categoria.setText("");
            et_marca.setText("");
            et_modelo.setText("");
            et_descripcion.setText("");
            et_precio.setText("");

            if(cantidad == 1){
                Toast.makeText(this, "Eliminado con exito", Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(this, "El vehículo no existe",Toast.LENGTH_SHORT).show();
            }

        }else {
            Toast.makeText(this, "Debes introducir la matrícula del vehículo", Toast.LENGTH_LONG).show();

        }
    }

    public void Modificar(View view){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracion", null, 1);
        SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();

        //variables donde almacenamos lo que el usuario ha modificado
        String matricula = et_matricula.getText().toString();
        String categoria = et_categoria.getText().toString();
        String marca = et_marca.getText().toString();
        String modelo = et_modelo.getText().toString();
        String descripcion = et_descripcion.getText().toString();
        String precio = et_precio.getText().toString();

        //validamos que todos los campos esten completos
        if(!matricula.isEmpty() && !categoria.isEmpty() && !marca.isEmpty()&& !descripcion.isEmpty()
                && !modelo.isEmpty() && !descripcion.isEmpty() && !precio.isEmpty()){

            //Guardamos dentro de este objeto registro los valores que el usuario ha escrito dentro de estos campos
            ContentValues registro = new ContentValues();
            registro.put("matricula", matricula);
            registro.put("categoria", categoria);
            registro.put("marca", marca);
            registro.put("modelo", modelo);
            registro.put("descripcion",descripcion);
            registro.put("precio",precio);

            //El método para modificar(update) retorna valores de tipo entero
            int cantidad = BaseDeDatos.update("vehiculos", registro, "matricula=" + matricula, null);
            //ya hemos terminado de usar la BD por lo tanto debo cerrarla
            BaseDeDatos.close();

            //Para avisar al usuario si se ha modificado correctamente
            //Si cantidad == 1 es que se ha modificado el producto
            if(cantidad == 1){
                Toast.makeText(this, "Modificado con exito", Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(this, "El vehiculo no existe",Toast.LENGTH_SHORT).show();
            }

        }else{
            Toast.makeText(this, "Debes rellenar todos los campos", Toast.LENGTH_LONG).show();
        }
    }


}