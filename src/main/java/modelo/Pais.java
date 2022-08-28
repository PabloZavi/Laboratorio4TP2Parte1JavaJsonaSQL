package modelo;

import java.util.ArrayList;

public class Pais {
    private ArrayList<Integer> callingCodes;
    private String name;
    private String capital;
    private String region;
    private long population;
    private ArrayList<Double> latlng;

    public Pais() {
    }

    public Pais(ArrayList<Integer> callingCodes, String name, String capital, String region, long population, ArrayList<Double> latlng) {
        this.callingCodes = callingCodes;
        this.name = name;
        this.capital = capital;
        this.region = region;
        this.population = population;
        this.latlng = latlng;
    }

    public ArrayList<Integer> getCallingCodes() {
        return callingCodes;
    }

    public void setCallingCodes(ArrayList<Integer> codigoPais) {
        this.callingCodes = codigoPais;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public long getPopulation() {
        return population;
    }

    public void setPopulation(long population) {
        this.population = population;
    }

    public ArrayList<Double> getLatlng() {
        return latlng;
    }

    public void setLatlng(ArrayList<Double> latlng) {
        this.latlng = latlng;
    }
    

    
}