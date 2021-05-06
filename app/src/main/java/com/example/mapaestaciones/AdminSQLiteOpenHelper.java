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

        //db.execSQL("create table oficinas(name string primary key, double lat, double long," +
        //        " foreign key (matricula) references vehiculos)");
        db.execSQL("create table oficinas(name string primary key, double lat, double long)");

        db.execSQL("create table usuarios(dni string primary key, nombre string, telefono integer," +
                "email string, pass string)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
