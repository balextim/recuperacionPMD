package com.example.dicionario.model;

import java.util.ArrayList;
/*esta clase junto con la clase ownBotResponse tienen la estructura par recibir la información
*   que proporciona la api */
public class Country {
    private String flag, country,officialName,capital,region, subregion,area,population,border,language;

    public Country(){

    }
    public Country(String flag, String country, String officialName, String region, String subregion, String area, String population, String border) {
        this.flag = flag;
        this.country = country;
        this.officialName=officialName;
        this.region = region;
        this.subregion = subregion;
        this.area=area;
        this.population = population;
        this.border = border;

    }

    public String getFlag() {
        return flag;
    }

    public String getCountry() {
        return country;
    }

    public String getCapital() {
        return capital;
    }

    public String getRegion() {
        return region;
    }

    public String getSubregion() {
        return subregion;
    }

    public String getPopulation() {
        return population;
    }

    public String getBorder() {
        return border;
    }

    public String getLanguage() {
        return language;
    }
    public String getOfficialName() {
        return officialName;
    }

    public String getArea() {
        return area;
    }

}
