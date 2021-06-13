package com.example.mapaestaciones;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class AdminSQLiteOpenHelper extends SQLiteOpenHelper {
    public AdminSQLiteOpenHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public void onConfigure(SQLiteDatabase db) {
        super.onConfigure(db);
        db.setForeignKeyConstraintsEnabled(true);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("PRAGMA foreign_keys=ON;");

        db.execSQL("create table vehiculos (matricula string primary key, categoria char, marca text," +
                " modelo text, descripcion text, precio real, nombreOficina string references oficinas)");

        db.execSQL("create table oficinas (nombreOficina string primary key, latitud double, longitud double)");

        db.execSQL("create table usuarios (dni text primary key, usuario text, nombre text, apellidos text, " +
                "telefono text, email text, password text)");

        db.execSQL("create table reservas (codigo integer primary key, fechaInicio date, fechaFin date," +
                "matricula string references vehiculos, nombreOficina string references oficinas, dni string references usuarios)");


        db.execSQL("insert into oficinas values('Sevilla','37.3886303', '-5.9953403')");
        db.execSQL("insert into oficinas values('Madrid','40.4167047', '-3.7035825')");
        db.execSQL("insert into oficinas values('Barcelona','41.3828939', '2.1774322')");
        db.execSQL("insert into oficinas values('Bilbao','43.2630018', '-2.9350039')");
        db.execSQL("insert into oficinas values('Vigo','42.2376602', '-8.7247205')");

        db.execSQL("insert into vehiculos values('1111KKK','Suv', 'Audi', 'Q5', 'El más recomendado', '200', 'Sevilla')");
        db.execSQL("insert into vehiculos values('2222BBB','Drift', 'BMW', '320i', 'El más grande', '220', 'Sevilla')");
        db.execSQL("insert into vehiculos values('7777CHH','Sport', 'Hyundai', 'i30N', 'El más racing', '350', 'Sevilla')");
        db.execSQL("insert into vehiculos values('3333CCC','Turismo', 'Audi', 'A5', 'El más cómodo', '190', 'Madrid')");
        db.execSQL("insert into vehiculos values('5555CCC','Turismo', 'Seat', 'León', 'El más guapo', '115', 'Madrid')");
        db.execSQL("insert into vehiculos values('4444DDD','Turismo', 'Audi', 'A3', 'El más pequeño', '160', 'Barcelona')");
        db.execSQL("insert into vehiculos values('5555GGG','Furgoneta', 'Peugeot', '408', 'Perfecto para grandes mercancias', '120', 'Vigo')");
        db.execSQL("insert into vehiculos values('8888LLL','Furgoneta', 'Citroen', 'Berlingo', 'Perfecto para grandes mercancias', '90', 'Vigo')");
        db.execSQL("insert into vehiculos values('6666FFF','Furgoneta', 'Peugeot', '506', 'Perfecto para grandes familias', '140', 'Bilbao')");

        db.execSQL("insert into usuarios values('17458711M','josrammol','Jose Felipe','Ramos Molinero','695478113','jfeliperamos@gmail.com','1234')");
        db.execSQL("insert into usuarios values('96582147S','josmolcol','Jose Carlos','Molina Coloma','796326841','josmolcol@gmail.com','1234')");

        //db.execSQL("insert into reservas values(1,'2021-05-13', '2021-05-16', '1111AAA', 'Sevilla', '1234')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}