package com.example.mapaestaciones;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Activity_Login extends AppCompatActivity {

    EditText user, pass;
    Button btnEntrar, btnRegistrate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__login);
        user=(EditText)findViewById(R.id.username);
        pass=(EditText)findViewById(R.id.password);
        btnEntrar=(Button)findViewById(R.id.btnEntrar);
        btnRegistrate=(Button)findViewById(R.id.btnRegistrate);
    }

    public void Registrate(View view){
        Intent i = new Intent(Activity_Login.this, Activity_Registrar.class);
        startActivity(i);
    }

    public void Entrar(View view){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,"administracion",null,1);
        SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();

        String usuario = user.getText().toString();
        String password = pass.getText().toString();

        if(!usuario.isEmpty() && !password.isEmpty()){
            Cursor fila = BaseDeDatos.rawQuery("select usuario, password from usuarios where usuario ='"+usuario+"'", null);
            if(fila.getCount()==1){
                Toast.makeText(this,"Login correcto", Toast.LENGTH_LONG).show();
                Intent i = new Intent(Activity_Login.this, MainActivity.class);
                startActivity(i);
            }else{
                Toast.makeText(this,"El usuario no existe o las credenciales no son correctas", Toast.LENGTH_LONG).show();
            }

        }
    }

}