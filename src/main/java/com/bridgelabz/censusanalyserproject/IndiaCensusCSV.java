package com.bridgelabz.censusanalyserproject;

import com.opencsv.bean.CsvBindByName;

public class IndiaCensusCSV {

    //SETTER
    public void setState(String state) {
        this.state = state;
    }

    public void setPopulation(String population) {
        this.population = population;
    }

    public void setAreaInSqKm(String areaInSqKm) {
        this.areaInSqKm = areaInSqKm;
    }

    public void setDensityPerSqKm(String densityPerSqKm) {
        this.densityPerSqKm = densityPerSqKm;
    }

    //GETTER
    public String getState() {
        return state;
    }

    public String getPopulation() {
        return population;
    }

    public String getAreaInSqKm() {
        return areaInSqKm;
    }

    public String getDensityPerSqKm() {
        return densityPerSqKm;
    }

    @CsvBindByName(column = "State",required = true)
    private String state;

    @CsvBindByName(column = "Population",required = true)
    private String population;

    @CsvBindByName(column = "AreaInSqKm",required = true)
    private String areaInSqKm;

    @CsvBindByName(column = "DensityPerSqKm",required = true)
    private String densityPerSqKm;

    @Override
    public String toString() {
        return "com.bridgelabz.censusanalyserproject.IndiaCensusCSV {"+
                "State='" + state + '\'' +
                " ,Population='" + population + '\'' +
                " ,AreaInSqKm='" + areaInSqKm + '\'' +
                " ,DensityPerSqKm='" + densityPerSqKm + '\'' +
                '}';
    }
}