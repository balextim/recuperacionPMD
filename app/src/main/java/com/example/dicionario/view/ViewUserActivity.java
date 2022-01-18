package com.example.dicionario.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.dicionario.R;
import com.example.dicionario.controller.HTTPconnectionCountry;
import com.example.dicionario.model.Country;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;

public class ViewUserActivity extends AppCompatActivity {

    private RecyclerView itemView;
    private RecyclerAdapter recyclerAdapter;
    private ArrayList<Country> responseCountry = new ArrayList<Country>();
    private EditText searchBar;
    private Button buttonsearch,buttonAll, buttonSettings;
    private ProgressBar cargando;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_user);
        searchBar = findViewById(R.id.editTextSarchCountry);
        buttonsearch = findViewById(R.id.buttonSearch);
        buttonAll=findViewById(R.id.buttonAll);
        buttonSettings=findViewById(R.id.buttonSettings);
        cargando=findViewById(R.id.progressBar);
        itemView=(RecyclerView) findViewById(R.id.recyclerViewCountry);
        recyclerAdapter=new RecyclerAdapter(this, ViewUserActivity.this,responseCountry);
        LinearLayoutManager orientationList = new LinearLayoutManager(this);
        itemView.setAdapter(recyclerAdapter);
        itemView.setLayoutManager(orientationList);
            new asyncTask().execute("GETALL");
        buttonAll.setOnClickListener(new View.OnClickListener() {
            @Override
            /*boton para recargar de nuevo la lista de todos los países*/
            public void onClick(View view) {
                responseCountry.clear();
                searchBar.setText("");
                cargando.setVisibility(View.VISIBLE);
                new asyncTask().execute("GETALL");
            }
        });
        /*boton para buscar el país introducido por pantalla*/
        buttonsearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*limpiamos la lista para cuando recarguen no aparezca la busque anterior*/
                responseCountry.clear();
                /*escondemos el teclado para hacerlo más cómodo para el usuario*/
                InputMethodManager hideKeyboard = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                hideKeyboard.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                recyclerAdapter = new RecyclerAdapter(ViewUserActivity.this, ViewUserActivity.this, responseCountry);
                itemView.setAdapter(recyclerAdapter);
                cargando.setVisibility(View.VISIBLE);
                if (searchBar.getText().toString().isEmpty()){
                    cargando.setVisibility(View.GONE);
                    Toast.makeText(ViewUserActivity.this,"debe escribir un país", Toast.LENGTH_SHORT).show();
                    return;
                }
                else {
                    new asyncTask().execute("GET", searchBar.getText().toString());
                }
            }
        });

        buttonSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(), Settings.class);
                startActivity(intent);
            }
        });

    }

     public class asyncTask extends AsyncTask<String,Void,String> {

        @Override
        protected String doInBackground(String... strings) {
            String result = null;
            switch (strings[0]){
                case "GET":
                    result= HTTPconnectionCountry.getResquet(strings[1]);
                    break;
                case "GETALL":
                    cargando.setVisibility(View.VISIBLE);
                    result=HTTPconnectionCountry.getResquetAll();
                    break;
            }
            return result;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            cargando.setVisibility(View.GONE);
            try{
                if (s != null){
                    Log.d("D", "País encontrado" + s);
                    /*convertimos en un jsonArray lo que obtenemos de la peticion a la apii*/
                    JSONArray jsonArray = new JSONArray(s);
                    String nameCountry,officialNameCountry,capital,area,flagCountry,region,subRegion,border,language,population,currencies;
                    /*y con el sigguiente for obtenemos los objetos que haya en el array para luego
                    * obtener las propiedades que necesitemos.*/
                    for (int i =0; i<jsonArray.length();i++){
                            JSONObject countrycontent=jsonArray.getJSONObject(i);
                            JSONObject name = countrycontent.getJSONObject("name");
                            /*utilicé el metodo optString ya que con este no tengo necesidad de preguntar si
                            * el objeto tiene o no la propiedad solicitada.
                            * en caso de no tenerla retorna una cadena vacia y con esto evitamos que
                            * salte una exception.*/
                            nameCountry=name.optString("common");
                            officialNameCountry=name.optString("official");
                            //capital=countrycontent.optString("capital");
                            region=countrycontent.optString("region");
                            JSONObject flag = countrycontent.getJSONObject("flags");
                            flagCountry = flag.optString("png");
                            subRegion=countrycontent.optString("subregion");
                            area=countrycontent.optString("area");
                            population=countrycontent.optString("population");
                            Country country = new Country(flagCountry,nameCountry,officialNameCountry,region,subRegion,area,population,"");
                            /*añadimos a la lista el objeto creado anteriormente el cuál contiene la
                            * información extraida del json*/
                            responseCountry.add(country);
                            /*pasamos la lista al recycler adapter para adecuarlo a la plantilla*/
                            recyclerAdapter=new RecyclerAdapter(ViewUserActivity.this, ViewUserActivity.this,responseCountry);
                            /*por ultimo añadimos al recyclerview el adaptador.*/
                            itemView.setAdapter(recyclerAdapter);
                        }
                    }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

}