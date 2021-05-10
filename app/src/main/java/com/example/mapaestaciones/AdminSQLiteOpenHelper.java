package com.example.mapaestaciones;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class AdminSQLiteOpenHelper extends SQLiteOpenHelper{
    public AdminSQLiteOpenHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table vehiculos(matricula int primary key, categoria char, marca text," +
                " modelo text, descripcion text, precio real)");

        db.execSQL("create table oficinas (nombre string primary key, latitud double, longitud double)");

        db.execSQL("create table if not exists usuarios(dni text primary key, usuario text, nombre text, apellidos text, " +
                "telefono text, email text, password text)");
        db.execSQL("insert into usuarios values('1234','jf','jf','jf','3','jf@j.com','jf')");
        db.execSQL("insert into usuarios values('1244','j','j','j','2','j@j.com','j')");

        db.execSQL("create table reservas(codigo integer primary key, fechaInicio date, fechaFin date)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
