package com.example.dicionario.model;

public class User {

    /*constante de la clase la cual va a contener el nombre de la tabla en la base de datos SQLite*/
    public static final String NOMBRE_MODELO="usuarios";
    /*nombre del campo nombre de la tabla usuarios*/
    public static final String NAME ="Nombre";
    /*nombre del campo apellido de la tabla*/
    public static final String SURNAME ="Apellido";
    /*nombre del campo edad de la tabla*/
    public static final String AGE ="Edad";
    /*Esta constante contendrá el nombre del campo email la tabla */
    public static final String CAMPO_EMAIL="Email";
    /*Esta constante contendrá el nombre del campo contraseña de la tabla*/
    public static final String CAMPO_PASSWORD="Password";

    /*Variables donde se guardan los datos de un objeto de tipo usuario*/
    public String nombre="";
    public String apellido="";
    public String edad="";
    public String email="";
    public String password="";

    /*constructor de la clase user el cuál espera dos parametros que son el email y la password*/
    public User(String nombre, String apellido, String edad, String email, String password){
        this.nombre=nombre;
        this.apellido=apellido;
        this.edad=edad;
        this.email=email;
        this.password=password;
    }

}
