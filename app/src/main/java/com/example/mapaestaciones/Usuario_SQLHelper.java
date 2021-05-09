package com.example.mapaestaciones;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class Usuario_SQLHelper {

    private static ArrayList<Usuario> lista;
    private static SQLiteDatabase sql;
    Context c;
    Usuario usuario;
    //ArrayList<Usuario> lista;
    //SQLiteDatabase sql;
    String bd = "BDUsuarios";
    String tabla = "create table usuarios(dni text primary key, usuario text, nombre text, apellidos text," +
            "telefono integer, email text, password text)";

    public Usuario_SQLHelper(Context c){
        this.c = c;
        sql = c.openOrCreateDatabase(bd, c.MODE_PRIVATE, null);
        sql.execSQL(tabla);
        usuario = new Usuario();
    }

    private static int buscar(String dni) {
        int count = 0;
        lista=selectUsuarios();
        for(Usuario u : lista){
            if(u.getDni().equals(dni)){
                count++;
            }
        }
        return count;
    }

    public static ArrayList<Usuario> selectUsuarios(){
        ArrayList<Usuario> lista = new ArrayList<>();
        lista.clear();
        Cursor cr=sql.rawQuery("select * from usuarios", null);
        if(cr!=null&&cr.moveToFirst()) {
            do {
                Usuario u = new Usuario();
                u.setDni(cr.getString(0));
                u.setUsuario(cr.getString(1));
                u.setNombre(cr.getString(2));
                u.setApellidos(cr.getString(3));
                //u.setTelefono(cr.getInt(4));
                u.setEmail(cr.getString(5));
                u.setPassword(cr.getString(6));
            } while (cr.moveToNext());
        }
        return lista;
    }

}
