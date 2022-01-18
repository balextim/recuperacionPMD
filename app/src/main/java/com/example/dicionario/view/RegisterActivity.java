package com.example.dicionario.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dicionario.R;
import com.example.dicionario.controller.Controller;
import com.example.dicionario.controller.SQLiteService;

public class RegisterActivity extends AppCompatActivity {
    Button buttonRegister;
    Button buttonCancel;
    TextView rEditTextName, rEditTextSurname, rEditTextAge, rEditTextEmail , rEditTextPassword;
    SQLiteService sqLiteService=new SQLiteService(this,"",null, 1);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        buttonRegister=findViewById(R.id.buttonRegister);
        rEditTextName=findViewById(R.id.rEditTextName);
        rEditTextSurname=findViewById(R.id.rEditTextSurname);
        rEditTextAge=findViewById(R.id.rEditTextEdad);
        rEditTextEmail=findViewById(R.id.rEditTextEmailAddress);
        rEditTextPassword=findViewById(R.id.rEditTextPassword);

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerUser(view);

            }
        });
        buttonCancel=findViewById(R.id.buttonCancel);
        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    /*este método crea un objeto de tipo controller el cual utiliza uno de sus método para buscar
    * en la base de datos el nombre de usuario que se le pasa por parámetro
    * en caso de que lo encuentre lanza su respectivo error.
    * En caso de que no lo encuentre registra al usuario en la base de datos.*/
    public void registerUser(View view){
        String name, surname, age, email, password, query;
        int operacionRealizada;
        name=rEditTextName.getText().toString();
        surname=rEditTextSurname.getText().toString();
        age=rEditTextAge.getText().toString();
        email=rEditTextEmail.getText().toString();
        password=rEditTextPassword.getText().toString();
        /*Objeto de la clase controller con el cual controlamos que los campos estén todos rellenos*/
        Controller controller = new Controller();
        boolean bcontroller = controller.controllerFieldsRegister(name,surname,age,email,password);
        if (bcontroller){
            SQLiteDatabase sqliteDataBase = openOrCreateDatabase("diccionario",MODE_PRIVATE, null);
            operacionRealizada=controller.registerUser(name,surname,age,email,password,sqliteDataBase, sqLiteService);
            if (operacionRealizada==0) {

                Toast.makeText(this, "Registered User", Toast.LENGTH_LONG).show();
                this.finish();
            }
            else if(operacionRealizada==-1){
                Toast.makeText(this, "El usuario ya existe", Toast.LENGTH_LONG).show();
            }
        }
        else {
            Toast.makeText(this,"Debe rellanar todos los datos",Toast.LENGTH_SHORT).show();
        }
    }
}