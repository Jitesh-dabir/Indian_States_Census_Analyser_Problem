package com.bridgelabz.dto;

import com.opencsv.bean.CsvBindByName;

public class IndiaCensusCSV {

    public IndiaCensusCSV() {
    }

    ;

    public IndiaCensusCSV(String state, int population, Double areaInSqKm, Double densityPerSqKm) {
        this.state = state;
        this.population = population;
        this.areaInSqKm = areaInSqKm;
        this.densityPerSqKm = densityPerSqKm;
    }

    //SETTER
    public void setState(String state) {
        this.state = state;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public void setAreaInSqKm(Double areaInSqKm) {
        this.areaInSqKm = areaInSqKm;
    }

    public void setDensityPerSqKm(Double densityPerSqKm) {
        this.densityPerSqKm = densityPerSqKm;
    }

    //GETTER
    public String getState() {
        return state;
    }

    public int getPopulation() {
        return population;
    }

    public Double getAreaInSqKm() {
        return areaInSqKm;
    }

    public Double getDensityPerSqKm() {
        return densityPerSqKm;
    }

    @CsvBindByName(column = "State", required = true)
    private String state;

    @CsvBindByName(column = "Population", required = true)
    private int population;

    @CsvBindByName(column = "AreaInSqKm", required = true)
    private Double areaInSqKm;

    @CsvBindByName(column = "DensityPerSqKm", required = true)
    private Double densityPerSqKm;

    @Override
    public String toString() {
        return "com.bridgelabz.dto.IndiaCensusCSV {" +
                "State='" + state + '\'' +
                " ,Population='" + population + '\'' +
                " ,AreaInSqKm='" + areaInSqKm + '\'' +
                " ,DensityPerSqKm='" + densityPerSqKm + '\'' +
                '}';
    }
}