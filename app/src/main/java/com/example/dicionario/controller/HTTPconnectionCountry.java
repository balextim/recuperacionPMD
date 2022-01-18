package com.example.dicionario.controller;

import android.widget.Toast;

import com.example.dicionario.view.ViewUserActivity;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HTTPconnectionCountry {
    /*url base con la que se ralizara la peticion a la api*/
    private static final String  URLBASE ="https://restcountries.com/v3.1/name/";

    /*definimos el método para las peticiones get con el que consultaremos la información a la api*/
    public static String getResquet(String urlWord) {
        HttpURLConnection http=null;
        String content = null;
        try {
            /*se forma la url con el endpoint para la peticion*/
            URL url = new URL(URLBASE + urlWord);
            http = (HttpURLConnection)url.openConnection();
            http.setRequestProperty("Content-Type", "application/json");
            http.setRequestProperty("Accept", "application/json");


            if (http.getResponseCode() == HttpURLConnection.HTTP_OK){
                /*se pasa la respuesta de la peticion a string*/
                StringBuilder contectSb = new StringBuilder();
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(http.getInputStream()));
                String line;
                while ((line = reader.readLine()) != null){
                    contectSb.append(line);
                }

                content=contectSb.toString();
                reader.close();

            }


        }catch (Exception e){
            e.printStackTrace();
        }
        finally {
            /*desconectamos la conexion*/
            if (http != null ){
                http.disconnect();
            }
        }
        return content;
    }
    public static String getResquetAll() {
        String urlbase="https://restcountries.com/v3.1/all";
        HttpURLConnection http=null;
        String content = null;
        try {
            /*se forma la url con el endpoint para la peticion*/
            URL url = new URL("https://restcountries.com/v3.1/all");
            http = (HttpURLConnection)url.openConnection();
            http.setRequestProperty("Content-Type", "application/json");
            http.setRequestProperty("Accept", "application/json");


            if (http.getResponseCode() == HttpURLConnection.HTTP_OK){
                /*se pasa la respuesta de la peticion a string*/
                StringBuilder contectSb = new StringBuilder();
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(http.getInputStream()));
                String line;
                while ((line = reader.readLine()) != null){
                    contectSb.append(line);
                    System.out.println(line);
                }

                content=contectSb.toString();
                reader.close();

            }


        }catch (Exception e){
            e.printStackTrace();
        }
        finally {
            /*desconectamos la conexion*/
            if (http != null ){
                http.disconnect();
            }
        }
        return content;
    }
}
