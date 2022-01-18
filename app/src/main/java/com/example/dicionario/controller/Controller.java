package com.example.dicionario.controller;

import static android.database.sqlite.SQLiteDatabase.openOrCreateDatabase;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.icu.text.CaseMap;


import com.example.dicionario.model.User;
import com.example.dicionario.view.RegisterActivity;

import java.util.Locale;

public class Controller {
    public Controller(){

    }

    public boolean controllerFieldsRegister(String name, String surname, String age, String email, String password){
        if (name.isEmpty() || surname.isEmpty() || age.isEmpty()|| email.isEmpty()|| password.isEmpty()){
            return false;
        }
        else {
            return true;
        }
    }

    public boolean controllerFieldLogin(String email, String password){
        if (email.isEmpty() || password.isEmpty()){
            return false;
        }
        else {
            return true;
        }
    }
    /*método para registrar usuasrios*/
    /*este método hace una llamada al metodo searchUser() para poder así dependiendo del resultado del mismo insertar un usuario
    * si la llamada al método devuelve un cero, quiere decir que no encontró ningún registro con los datos pasados por parámetro
    * y en concecuencia registra al usuario, y devuelve un cero a la clase Register activity donde fue llamado, para lanzar el respectivo
    * mensaje de usuario registrado.
    *
    * si por el contrario el metodo searchUser() devulve un -1 quiere decir que ya hay un usuario registrado con las credenciales
    * pasadas por parámetro. no realiza ninguna accion excepto devolver un menos uno, para que así sea lanzado el correspondiente mensaje de
    * error.*/
    public int registerUser(String name, String surname, String age, String email, String password, SQLiteDatabase sqLiteDatabase, SQLiteService sqLiteService){
        String query;
        int encontrado;
        encontrado=searchUserRegister(email, password, sqLiteDatabase, sqLiteService);
        if (encontrado==0) {
            query = "INSERT INTO " + User.NOMBRE_MODELO;
            query += "(" + User.NAME + ", " + User.SURNAME + ", " + User.AGE + ", " + User.CAMPO_EMAIL + ", " + User.CAMPO_PASSWORD + ") ";
            query += "VALUES ('" + name + "', '" + surname + "', '" + age + "', '" + email + "', '" + password + "');";
            sqLiteDatabase.execSQL(sqLiteService.CREAR_TUSERS);
            sqLiteDatabase.execSQL(query);
            return 0;
        }
        else {
            return -1;
        }
    }
    /*metodo para buscar un usuario*/
    /*el método utiliza una sentencia sql almacenada en un string llamado query el cual es ejecutado con uno
    * de los metodos del objeto sqliteDatabase. cuando se ejecuta guardamos en un cursor los registros de la tabla usuarios de
    * la base de datos, los compara con el email y la password pasados por parámetro
    * y si encuentra un registro con dichos datos devulece -1 para poder controlar el registro desde la actvity_Register.xml*/
    public int searchUserRegister(String email, String password, SQLiteDatabase sqLiteDatabase, SQLiteService sqLiteService){
        String query;
        sqLiteDatabase.execSQL(sqLiteService.CREAR_TUSERS);
        query="SELECT * FROM " + User.NOMBRE_MODELO;
        query+=" WHERE " + User.CAMPO_EMAIL + "='" + email + "';";
        Cursor register = sqLiteDatabase.rawQuery(query,null);
        if (register.getCount()==1){
            /*Si devulve -1 es porque encontró un registro con el usuario que se quiere registrar*/
            return -1;
        }
        else {
            /*Devuelve cero en caso de que no encuentre al usuario*/
            return 0;
        }
    }
    public int searchUserLogin(String email, String password, SQLiteDatabase sqLiteDatabase, SQLiteService sqLiteService){
        String query;
        sqLiteDatabase.execSQL(sqLiteService.CREAR_TUSERS);
        query="SELECT * FROM " + User.NOMBRE_MODELO;
        query+=" WHERE " + User.CAMPO_EMAIL + "='" + email + "' AND " + User.CAMPO_PASSWORD + "='" + password + "';";
        Cursor register = sqLiteDatabase.rawQuery(query,null);
        if (register.getCount()==1){
            /*Si devulve -1 es porque encontró un registro con el usuario que se quiere registrar*/
            return -1;
        }
        else {
            /*Devuelve cero en caso de que no encuentre al usuario*/
            return 0;
        }
    }
}
