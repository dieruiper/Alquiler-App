package com.example.mapaestaciones;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MiPerfil extends AppCompatActivity {

    TextView tv_usuario,tv_nombreusuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mi_perfil);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);

        tv_usuario=(TextView)findViewById(R.id.tv_usuario);
        tv_nombreusuario=(TextView)findViewById(R.id.nombreusuario);
        SharedPreferences preferences = getSharedPreferences("credenciales", Context.MODE_PRIVATE);
        tv_usuario.setText(preferences.getString("usuario", "AAAAAA"));
        tv_nombreusuario.setText(preferences.getString("nombre", "AquiEsElNombre"));

        /*
        String nombreusuario =tv_nombreusuario.getText().toString();

        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracion", null, 1);
        SQLiteDatabase BaseDeDatos = admin.getReadableDatabase();

        Cursor fila = BaseDeDatos.rawQuery("select * from usuarios where nombre ='"+nombreusuario+"'", null);
        if(fila.moveToFirst()){
            do{
                  nombreusuario=fila.getString(0);
            }while(fila.moveToNext());
        }



         */

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