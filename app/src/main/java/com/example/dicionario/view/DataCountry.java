package com.example.dicionario.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.dicionario.R;

public class DataCountry extends AppCompatActivity {
    TextView tvName, tvOfficialName, tvCapital, tvRegion, tvSubregion, tvArea;
    TextView tvPopulation, tvBorders, tvLanguage;
    ImageView flagCountry;
    String stName,stOfficialName,stCapital,stRegion,stSubregion,stArea,stPopulation,stBorders,stLanguage;
    String stFlagCountry;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_country);

        Bundle getItemSelected = getIntent().getExtras();
        /*con el siguiente if obtenemos la información del pais del recyclerview seleccionado*/
        if(getItemSelected!=null){
            stName=getItemSelected.getString("nameCountry");
            stOfficialName=getItemSelected.getString("officialNameCountry");
            stRegion=getItemSelected.getString("Region");
            stSubregion=getItemSelected.getString("Subregion");
            stArea=getItemSelected.getString("Area");
            stPopulation=getItemSelected.getString("Population");
            //stBorders=getItemSelected.getString("Borders");
            stFlagCountry=getItemSelected.getString("Flag");
        }

        /*realicionamos los textView con los campos del layout*/
        tvName =findViewById(R.id.textViewContentName);
        tvOfficialName =findViewById(R.id.textViewContentOfficial);
        tvRegion =findViewById(R.id.textViewContentRegion);
        tvSubregion =findViewById(R.id.textViewContentSubregion);
        tvArea =findViewById(R.id.textViewContentArea);
        tvPopulation =findViewById(R.id.textViewContentPopulation);
        tvBorders =findViewById(R.id.textViewContentBorders);
        flagCountry=findViewById(R.id.imageViewFlagCountry);
        /*Introducimos la informacion que deseamos mostrar en los textView*/
        tvName.setText(stName);
        tvOfficialName.setText(stOfficialName);
        tvRegion.setText(stRegion);
        tvSubregion.setText(stSubregion);
        tvArea.setText(stArea);
        tvPopulation.setText(stPopulation);
        //tvBorders.setText(stBorders);
        Glide.with(this).load(stFlagCountry).into(flagCountry);


    }
}