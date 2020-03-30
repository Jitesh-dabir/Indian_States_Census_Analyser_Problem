package com.bridgelabz.dao;

import com.bridgelabz.dto.IndiaCensusCSV;
import com.bridgelabz.dto.IndianStateCode;
import com.bridgelabz.dto.USCensusCSV;

public class CensusDAO {
    public int population;
    public double areaInSqKm;
    public double densityPerSqKm;
    public int tin;
    public int srNo;
    public String name;
    public String state;
    public String stateCode;


    public CensusDAO(IndiaCensusCSV csvStateCensus) {
        this.state = csvStateCensus.getState();
        this.population = csvStateCensus.getPopulation();
        this.areaInSqKm = csvStateCensus.getAreaInSqKm();
        this.densityPerSqKm = csvStateCensus.getDensityPerSqKm();
    }

    public CensusDAO(IndianStateCode csvStateCode) {
        this.srNo = csvStateCode.getSrNo();
        this.state = csvStateCode.getState();
        this.name = csvStateCode.getName();
        this.tin = csvStateCode.getTin();
        this.stateCode = csvStateCode.getStateCode();
    }

    public CensusDAO(USCensusCSV usCensusCSV) {
        state = usCensusCSV.getState();
        areaInSqKm = usCensusCSV.getTotalArea();
        densityPerSqKm = usCensusCSV.getDensityPerSqKm();
        population = usCensusCSV.getPopulation();
    }
}