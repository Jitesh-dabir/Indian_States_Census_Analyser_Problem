package com.bridgelabz.dao;

import com.bridgelabz.dto.IndiaCensusCSV;
import com.bridgelabz.dto.IndianStateCode;
import com.bridgelabz.dto.USCensusCSV;
import com.bridgelabz.service.StateCensusAnalyser;

import java.util.Comparator;

public class CensusDAO {
    public int population;
    public double areaInSqKm;
    public double densityPerSqKm;
    public int tin;
    public int srNo;
    public String name;
    public String state;

    public int getPopulation() {
        return population;
    }

    public double getAreaInSqKm() {
        return areaInSqKm;
    }

    public double getDensityPerSqKm() {
        return densityPerSqKm;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public void setAreaInSqKm(double areaInSqKm) {
        this.areaInSqKm = areaInSqKm;
    }

    public void setDensityPerSqKm(double densityPerSqKm) {
        this.densityPerSqKm = densityPerSqKm;
    }

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

    public static Comparator<CensusDAO> getSortComparator(StateCensusAnalyser.SORTING_MODE mode) {
        if (mode.equals(StateCensusAnalyser.SORTING_MODE.STATE))
            return Comparator.comparing(census -> census.state);
        if (mode.equals(StateCensusAnalyser.SORTING_MODE.POPULATION))
            return Comparator.comparing(CensusDAO::getPopulation).reversed();
        if (mode.equals(StateCensusAnalyser.SORTING_MODE.DENSITY))
            return Comparator.comparing(CensusDAO::getDensityPerSqKm).reversed();
        if (mode.equals(StateCensusAnalyser.SORTING_MODE.AREA))
            return Comparator.comparing(CensusDAO::getAreaInSqKm).reversed();
        if (mode.equals(StateCensusAnalyser.SORTING_MODE.STATECODE))
            return Comparator.comparing(census -> census.stateCode);
        return null;
    }

    public Object getCensusDTO(StateCensusAnalyser.COUNTRY country) {
        if (country.equals(StateCensusAnalyser.COUNTRY.INDIA))
            return new IndiaCensusCSV(state, stateCode, population, areaInSqKm, densityPerSqKm);
        return new USCensusCSV(stateCode, state, population, areaInSqKm, densityPerSqKm);
    }

}