package com.bridgelabz.censusanalyserproject;

import org.junit.Assert;
import org.junit.Test;

public class CensusAnalyserProblemTest {

    //CONSTANT
    private static final String STATE_CENSUS_DATA_PATH = "./src/test/resources/StateCensusData.csv";

    //OBJECT CREATION
    StateCensusAnalyser censusAnalyserProblem = new StateCensusAnalyser();

    @Test
    public void givenIndianCensusCsvFile_WhenProper_ShouldReturnCorrectREcord() {
        int numberOfRecord = censusAnalyserProblem.loadIndiaCensusData(STATE_CENSUS_DATA_PATH);
        Assert.assertEquals(29,numberOfRecord);
    }
}
