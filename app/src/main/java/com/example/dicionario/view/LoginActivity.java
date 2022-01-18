package com.example.dicionario.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dicionario.R;
import com.example.dicionario.controller.Controller;
import com.example.dicionario.controller.SQLiteService;
import com.example.dicionario.fragments.SettingsFragment;
import com.example.dicionario.model.User;

public class LoginActivity extends AppCompatActivity {
    /*variables para guardar el contenido de los edittext de la interfaz gráfica*/
    TextView opcionRegister;
    Button login;
    private EditText editTextEmailAddress, editTextPassword;
    private SQLiteService sqLiteService;

    /*funcion que inicializa los editText creados anteriormente (usuario y contraseña)*/
    private void initComponets(){
        /*findViewById busca el objeto del activity login que tiene el id especificado y lo asigna a la variable*/
        editTextEmailAddress=findViewById(R.id.editTextEmailAddress);
        editTextPassword=findViewById(R.id.editTextPassword);
        login=findViewById(R.id.buttonLogin);

        sqLiteService = new SQLiteService(this,"",null,1);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginSession();
            }
        });


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        /*Llama a la funcion init componets para recoger los datos*/
        initComponets();
        opcionRegister = findViewById(R.id.textViewRegister);

        opcionRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(intent);
                /*hay un pequeño fallo al volver con el botón de back del movil cierra la aplicación directamente
                * en ves de volver a la pantalla de inicion. para volver a la pantalla de inicio tendríamos que
                * clicar en el boton cancel directamente.*/
                //finish();
            }
        });


    }
    /*metodoo para iniciar sesion en el cual se hace uso de la clase controller para
    * saber si los campos están vacion. En caso de que no lo esté busca en la base de datos ejecutando una sentencia sql
    * para encontrar o no el usuario que quiere iniciar sesion.
    * en caso de que no lo encuentre lanza un toast indicando que no se encuentra el usuario*/
    public void loginSession (){
        String email, password;
        int operacionRealizada;
        email=editTextEmailAddress.getText().toString();
        password=editTextPassword.getText().toString();
        Controller controller = new Controller();
        if(controller.controllerFieldLogin(email,password)){
               SQLiteDatabase sqLiteDatabase = openOrCreateDatabase("diccionario", MODE_PRIVATE, null);
               operacionRealizada=controller.searchUserLogin(email,password,sqLiteDatabase,sqLiteService);
               if (operacionRealizada == -1){
                   Intent intent = new Intent(getApplicationContext(), ViewUserActivity.class);
                   startActivity(intent);
                   finish();
               }
               else if (operacionRealizada==0){
                   Toast.makeText(this, "Este usuario no está registrado", Toast.LENGTH_SHORT).show();
               }
        }
        else {
            Toast.makeText(this,"Debe rellenar los campos", Toast.LENGTH_SHORT).show();
        }
    }
}