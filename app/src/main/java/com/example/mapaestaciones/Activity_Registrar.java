package com.example.mapaestaciones;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Activity_Registrar extends AppCompatActivity {

    EditText et_usuario, et_password, et_nombre, et_apellidos, et_telefono, et_email, et_dni;
    Button btn_registrar_usuario, btn_cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__registrar);

        et_usuario=(EditText)findViewById(R.id.username);
        et_nombre=(EditText)findViewById(R.id.RegNombre);
        et_apellidos=(EditText)findViewById(R.id.RegApellidos);
        et_telefono=(EditText)findViewById(R.id.RegTelefono);
        et_email=(EditText)findViewById(R.id.RegEmail);
        et_password=(EditText)findViewById(R.id.password);
        et_dni=(EditText)findViewById(R.id.RegDni);

        btn_cancel=(Button)findViewById(R.id.btnRegistrate);
        btn_registrar_usuario=(Button)findViewById(R.id.btnEntrar);

    }

    public void RegistrarUsuario(View view){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,"administracion",null,1);
        SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();

        String dni = et_dni.getText().toString();
        String usuario = et_usuario.getText().toString();
        String nombre = et_nombre.getText().toString();
        String apellidos = et_apellidos.getText().toString();
        String telefono = et_telefono.getText().toString();
        String email = et_email.getText().toString();
        String password = et_password.getText().toString();

        if(!usuario.isEmpty() && !nombre.isEmpty() && !apellidos.isEmpty()&& !telefono.isEmpty()
                && !email.isEmpty() && !password.isEmpty() && !dni.isEmpty()){

            ContentValues registro = new ContentValues();
            registro.put("dni", dni);
            registro.put("usuario", usuario);
            registro.put("nombre", nombre);
            registro.put("apellidos", apellidos);
            registro.put("telefono", telefono);
            registro.put("email", email);
            registro.put("password", password);

            BaseDeDatos.insert("usuarios", null, registro);
            BaseDeDatos.close();

            et_dni.setText("");
            et_usuario.setText("");
            et_nombre.setText("");
            et_apellidos.setText("");
            et_telefono.setText("");
            et_email.setText("");
            et_password.setText("");

            Toast.makeText(this,"Registro correcto", Toast.LENGTH_LONG).show();
            Intent i = new Intent(Activity_Registrar.this, Activity_Login.class);
            startActivity(i);
            finish();

        }else {
            Toast.makeText(this,"ERROR: No puede haber campos vacíos", Toast.LENGTH_LONG).show();
        }
    }

    public void Cancelar(View view){
        Intent i = new Intent(Activity_Registrar.this, Activity_Login.class);
        startActivity(i);
        finish();
    }

}