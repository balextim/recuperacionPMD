package com.example.dicionario.controller;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.dicionario.model.User;

/*clase que realizará la conexion de la aplicación con SQLite*/
public class SQLiteService extends SQLiteOpenHelper {
    /*constante que tendrá  una consulta en el sqlite para ejecutar (Crear tabla)*/
    public final String CREAR_TUSERS="CREATE TABLE IF NOT EXISTS " + User.NOMBRE_MODELO + "(" + User.NAME + " TEXT, " + User.SURNAME + " TEXT, " + User.AGE + " TEXT, " + User.CAMPO_EMAIL + " TEXT, " + User.CAMPO_PASSWORD + " TEXT);";
    /*CONSTANTE que contiene la sentencia a ejecutar si se quiere eliminar la tabla usuarios*/
    private final String ELIMINAR_TUSER="DROP TABLE IF EXISTS " + User.NOMBRE_MODELO + ";";
    public SQLiteService(@Nullable Context context, @Nullable String nameDB, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, "dicionario", factory, version);
    }

    @Override
    /*método que realiza la conexion con la base de datos la cuál pasamos por parámetro
    * y se ejecuta cuando realizamos una instancia de esta clase*/
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        /*ejecuta la sentencia sql que nos creará la tabla usurarios*/
        sqLiteDatabase.execSQL(CREAR_TUSERS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        /*en caso de que haya una actualización en la tabla primero eliminaremos la tabla y luego la crearemos de nuevo*/
        sqLiteDatabase.execSQL(this.ELIMINAR_TUSER);
        sqLiteDatabase.execSQL(this.CREAR_TUSERS);

    }
}
