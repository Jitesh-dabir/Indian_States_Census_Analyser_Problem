package com.bridgelabz.censusanalyserproject;

import com.bridgelabz.exception.MyCensusException;
import org.junit.Assert;
import org.junit.Test;

public class CensusAnalyserProblemTest {

    //CONSTANT
    private static final String STATE_CENSUS_DATA_PATH = "./src/test/resources/StateCensusData.csv";
    private static final String WRONG_STATE_CENSUS_DATA_PATH = "./src/test/resources/StateCensusData.cs";

    //OBJECT CREATION
    StateCensusAnalyser censusAnalyserProblem = new StateCensusAnalyser();

    @Test
    public void givenIndianCensusCsvFile_WhenProper_ShouldReturnCorrectRecord() throws MyCensusException {
        int numberOfRecord = censusAnalyserProblem.loadIndiaCensusData(STATE_CENSUS_DATA_PATH);
        Assert.assertEquals(29,numberOfRecord);
    }

    @Test
    public void givenIndianCensusCsvFile_WhenImproper_ShouldReturnCorrectRecord() throws MyCensusException {
     try {
         int numberOfRecord = censusAnalyserProblem.loadIndiaCensusData(WRONG_STATE_CENSUS_DATA_PATH);
         Assert.assertEquals(29,numberOfRecord);
     } catch (MyCensusException e) {
         Assert.assertEquals(MyCensusException.MyException_Type.FILE_NOT_FOUND,e.type);
     }
    }
}