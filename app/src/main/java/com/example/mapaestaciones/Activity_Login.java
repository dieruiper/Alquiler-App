package com.example.mapaestaciones;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class Activity_Login extends AppCompatActivity {

    EditText user, pass;
    Button btnEntrar, btnRegistrate;
    ListView lv_usuarios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__login);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);

        user=(EditText)findViewById(R.id.username);
        pass=(EditText)findViewById(R.id.password);
        btnEntrar=(Button)findViewById(R.id.btnEntrar);
        btnRegistrate=(Button)findViewById(R.id.btnRegistrate);
        ArrayList<String> l = new ArrayList<String>();

        //SharedPreferences preferences = getSharedPreferences("credenciales", Context.MODE_PRIVATE);
    }

    public void Registrate(View view){
        Intent i = new Intent(Activity_Login.this, Activity_Registrar.class);
        startActivity(i);
        finish();
    }

    public void Entrar(View view){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,"administracion",null,1);
        SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();
        String usuario = user.getText().toString();
        String password = pass.getText().toString();
        Usuario userEntrante = new Usuario();

        if(!usuario.isEmpty() && !password.isEmpty()) {

            Cursor fila = BaseDeDatos.rawQuery("select * from usuarios where usuario ='"+usuario+"'", null);
            if(fila.moveToFirst()){
                do{
                    Usuario u = new Usuario(fila.getString(0),
                            fila.getString(1),
                            fila.getString(2),
                            fila.getString(3),
                            fila.getString(4),
                            fila.getString(5),
                            fila.getString(6));
                    userEntrante = u;
                }while(fila.moveToNext());
            }

            if (userEntrante.getPassword().equals(password)) {
                Toast.makeText(this, "Login correcto", Toast.LENGTH_LONG).show();
                guardarPreferencias(userEntrante);

                SharedPreferences sesion = getSharedPreferences("sesion",Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sesion.edit();
                editor.putBoolean("login", true);

                Intent i = new Intent(Activity_Login.this, MainActivity.class);
                startActivity(i);
                MainActivity.saveValuePreference(getApplicationContext(), false);
            } else {
                Toast.makeText(this, "El usuario no existe o las credenciales no son correctas", Toast.LENGTH_LONG).show();
            }

        }else{
            Toast.makeText(this, "ERROR: debe rellenar los campos", Toast.LENGTH_LONG).show();
        }
    }

    private void guardarPreferencias(Usuario u) {
        SharedPreferences preferences = getSharedPreferences("credenciales", Context.MODE_PRIVATE);
        SharedPreferences.Editor obj_editor = preferences.edit();
        obj_editor.putString("nombre",u.getNombre());
        obj_editor.putString("apellidos",u.getApellidos());
        obj_editor.putString("usuario",u.getUsuario());
        obj_editor.putString("dni",u.getDni());
        obj_editor.putString("telefono", u.getTelefono());
        obj_editor.putString("email",u.getEmail());
        obj_editor.commit();
    }

}

        //MUESTRA LA BASE DE DATOS
        /* en el onCreate()
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,"administracion",null,1);
        SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();

        Cursor fila = BaseDeDatos.rawQuery("select * from usuarios", null);

        if(fila.moveToFirst()){
            do{
                Usuario u = new Usuario(fila.getString(0),
                        fila.getString(1),
                        fila.getString(2),
                        fila.getString(3),
                        fila.getString(4),
                        fila.getString(5),
                        fila.getString(6));
                l.add(u.toString());
            }while(fila.moveToNext());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.list_item_usuarios,l);
        lv_usuarios.setAdapter(adapter);
        BaseDeDatos.close();
        */