package com.bridgelabz.censusanalyserproject;

import com.bridgelabz.exception.MyCensusException;
import org.junit.Assert;
import org.junit.Test;

public class CensusAnalyserProblemTest {

    //CONSTANT
    private static final String STATE_CENSUS_DATA_PATH = "./src/test/resources/StateCensusData.csv";
    private static final String WRONG_STATE_CENSUS_DATA_PATH = "./src/test/resources/StateCensus.csv";
    private static final String WRONG_TYPE_STATE_CENSUS_DATA_PATH = "./src/test/resources/StateCensusData.cv";

    //OBJECT CREATION
    StateCensusAnalyser censusAnalyserProblem = new StateCensusAnalyser();

    @Test
    public void givenIndianCensusCsvFile_WhenProper_ShouldReturnCorrectRecord() throws MyCensusException {
        int numberOfRecord = censusAnalyserProblem.loadIndiaCensusData(STATE_CENSUS_DATA_PATH);
        Assert.assertEquals(29,numberOfRecord);
    }

    @Test
    public void givenIndianCensusCsvFile_WhenImproper_ShouldThrowException() throws MyCensusException {
     try {
         int numberOfRecord = censusAnalyserProblem.loadIndiaCensusData(WRONG_STATE_CENSUS_DATA_PATH);
         Assert.assertEquals(29,numberOfRecord);
     } catch (MyCensusException e) {
         Assert.assertEquals(MyCensusException.MyException_Type.FILE_NOT_FOUND,e.type);
     }
    }

    @Test
    public void givenIndianCensusCsvFile_WhenImproperType_ShouldThrowException() throws MyCensusException {
        try {
            int numberOfRecord = censusAnalyserProblem.loadIndiaCensusData(WRONG_TYPE_STATE_CENSUS_DATA_PATH);
            Assert.assertEquals(29,numberOfRecord);
        } catch (MyCensusException e) {
            Assert.assertEquals(MyCensusException.MyException_Type.NO_SUCH_TYPE,e.type);
        }
    }
}