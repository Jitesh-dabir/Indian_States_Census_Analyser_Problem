package com.bridgelabz.censusanalyserproject;

import com.bridgelabz.exception.MyCensusException;
import org.junit.Assert;
import org.junit.Test;
import com.google.gson.Gson;

public class CensusAnalyserProblemTest {

    //CONSTANT
    private static final String STATE_CENSUS_DATA_PATH = "./src/test/resources/StateCensusData.csv";
    private static final String WRONG_STATE_CENSUS_DATA_PATH = "./src/test/resources/StateCensus.csv";
    private static final String WRONG_TYPE_STATE_CENSUS_DATA_PATH = "./src/test/resources/StateCensusData.cv";
    private static final String WRONG_DELIMITER_STATE_CENSUS_DATA_PATH = "./src/test/resources/WrongDelimiterStateCensusData.csv";
    private static final String WRONG_HEADER_STATE_CENSUS_DATA_PATH = "./src/test/resources/WrongHeaderStateCensusData.csv";
    private static final String INDIAN_STATE_CODE_INFORMATION_PATH = "./src/test/resources/StateCode.csv";
    private static final String WRONG_INDIAN_STATE_CODE_INFORMATION_PATH = "./src/test/resources/StatCode.csv";
    private static final String WRONG_TYPE_INDIAN_STATE_CODE_INFORMATION_PATH = "./src/test/resources/StateCode.cv";
    private static final String WRONG_DELIMITER_INDIAN_STATE_CODE_INFORMATION_PATH = "./src/test/resources/WrongDelimiterStateCodeData.csv";
    private static final String WRONG_HEADER_INDIAN_STATE_CODE_INFORMATION_PATH = "./src/test/resources/WrongHeaderStateCodeData.csv";
    private static final String US_CENSUS_DATA_PATH = "./src/test/resources/USCensusData.csv";
    private static final String INDIAN_STATE_CODE_FILE = "src/main/java/com/bridgelabz/censusanalyserproject/IndianStateCode";
    private static final String INDIAN_STATE_CENSUS_FILE = "src/main/java/com/bridgelabz/censusanalyserproject/IndiaCensusCSV.java";


    //OBJECT CREATION
    StateCensusAnalyser censusAnalyserProblem = new StateCensusAnalyser();

    @Test
    public void givenIndianCensusCsvFile_WhenProper_ShouldReturnCorrectRecord() throws MyCensusException {
        int numberOfRecord = censusAnalyserProblem.loadIndiaCensusData(STATE_CENSUS_DATA_PATH);
        Assert.assertEquals(29, numberOfRecord);
    }

    @Test
    public void givenIndianCensusCsvFile_WhenImproper_ShouldThrowException() {
        try {
            int numberOfRecord = censusAnalyserProblem.loadIndiaCensusData(STATE_CENSUS_DATA_PATH);
            Assert.assertEquals(29, numberOfRecord);
        } catch (MyCensusException e) {
            Assert.assertEquals(MyCensusException.MyException_Type.FILE_NOT_FOUND, e.type);
        }
    }

    @Test
    public void givenIndianCensusCsvFile_WhenImproperType_ShouldThrowException() {
        try {
            int numberOfRecord = censusAnalyserProblem.loadIndiaCensusData(STATE_CENSUS_DATA_PATH);
            Assert.assertEquals(29, numberOfRecord);
        } catch (MyCensusException e) {
            Assert.assertEquals(MyCensusException.MyException_Type.NO_SUCH_TYPE, e.type);
        }
    }

    @Test
    public void givenIndianCensusCsvFile_WhenImproperDelimiter_ShouldThrowException() {
        try {
            int numberOfRecord = censusAnalyserProblem.loadIndiaCensusData(STATE_CENSUS_DATA_PATH);
            Assert.assertEquals(29, numberOfRecord);
        } catch (MyCensusException e) {
            Assert.assertEquals(MyCensusException.MyException_Type.WRONG_DELIMITER_OR_HEADER, e.type);
        }
    }

    @Test
    public void givenIndianCensusCsvFile_WhenImproperHeader_ShouldThrowException() {
        try {
            int numberOfRecord = censusAnalyserProblem.loadIndiaCensusData(STATE_CENSUS_DATA_PATH);
            Assert.assertEquals(29, numberOfRecord);
        } catch (MyCensusException e) {
            Assert.assertEquals(MyCensusException.MyException_Type.WRONG_DELIMITER_OR_HEADER, e.type);
        }
    }

    @Test
    public void givenIndianStateCodeCsvFile_WhenProper_ShouldReturnCorrectRecordCount() throws MyCensusException {
        int numberOfRecord = censusAnalyserProblem.loadIndiaStateCodeData(INDIAN_STATE_CODE_INFORMATION_PATH);
        Assert.assertEquals(37, numberOfRecord);
    }

    @Test
    public void givenIndianStateCodeCsvFile_WhenImproper_ShouldThrowException() {
        try {
            int numberOfRecord = censusAnalyserProblem.loadIndiaStateCodeData(INDIAN_STATE_CODE_INFORMATION_PATH);
            Assert.assertEquals(37, numberOfRecord);
        } catch (MyCensusException e) {
            Assert.assertEquals(MyCensusException.MyException_Type.FILE_NOT_FOUND, e.type);
        }
    }

    @Test
    public void givenIndianStateCodeCsvFile_WhenImproperType_ShouldThrowException() {
        try {
            int numberOfRecord = censusAnalyserProblem.loadIndiaStateCodeData(INDIAN_STATE_CODE_INFORMATION_PATH);
            Assert.assertEquals(37, numberOfRecord);
        } catch (MyCensusException e) {
            Assert.assertEquals(MyCensusException.MyException_Type.NO_SUCH_TYPE, e.type);
        }
    }

    @Test
    public void givenIndianStateCodeCsvFile_WhenImproperDelimiter_ShouldThrowException() {
        try {
            int numberOfRecord = censusAnalyserProblem.loadIndiaStateCodeData(INDIAN_STATE_CODE_INFORMATION_PATH);
            Assert.assertEquals(37, numberOfRecord);
        } catch (MyCensusException e) {
            Assert.assertEquals(MyCensusException.MyException_Type.WRONG_DELIMITER_OR_HEADER, e.type);
        }
    }

    @Test
    public void givenIndianStateCodeCsvFile_WhenImproperHeader_ShouldThrowException() {
        try {
            int numberOfRecord = censusAnalyserProblem.loadIndiaStateCodeData(INDIAN_STATE_CODE_INFORMATION_PATH);
            Assert.assertEquals(37, numberOfRecord);
        } catch (MyCensusException e) {
            Assert.assertEquals(MyCensusException.MyException_Type.WRONG_DELIMITER_OR_HEADER, e.type);
        }
    }

    @Test
    public void givenIndianCensusData_WhenSortedOnState_ShouldReturnFirstSortedResult() {
        try {
            censusAnalyserProblem.loadIndiaCensusData(STATE_CENSUS_DATA_PATH);
            String sortedCensusData = censusAnalyserProblem.getSortedCensusStateData();
            IndiaCensusCSV[] censusCSV = new Gson().fromJson(sortedCensusData, IndiaCensusCSV[].class);
            Assert.assertEquals("Andhra Pradesh",censusCSV[0].getState());
        } catch (MyCensusException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIndianCensusData_WhenSortedOnState_ShouldReturnLastSortedResult() {
        try {
            censusAnalyserProblem.loadIndiaCensusData(STATE_CENSUS_DATA_PATH);
            String sortedCensusData = censusAnalyserProblem.getSortedCensusStateData();
            IndiaCensusCSV[] censusCSV = new Gson().fromJson(sortedCensusData, IndiaCensusCSV[].class);
            Assert.assertEquals("West Bengal",censusCSV[28].getState());
        } catch (MyCensusException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIndianCensusData_WhenImproperFile_ShouldThrowException() {
        try {
            String sortedCensusData = censusAnalyserProblem.getSortedCensusStateData();
            IndiaCensusCSV[] censusCSV = new Gson().fromJson(sortedCensusData, IndiaCensusCSV[].class);
            Assert.assertEquals("West Bengal",censusCSV[28].getState());
        } catch (MyCensusException e) {
            Assert.assertEquals(MyCensusException.MyException_Type.NO_SUCH_CENSUS_DATA,e.type);
        }
    }

    @Test
    public void givenIndianStateCodeData_WhenSortedOnStateCode_ShouldReturnFirstSortedResult() {
        try {
            int numberOfRecord = censusAnalyserProblem.loadIndiaStateCodeData(INDIAN_STATE_CODE_INFORMATION_PATH);
            String sortedStateCodeData = censusAnalyserProblem.getSortedStateCodeData();
            IndianStateCode[] stateCSV = new Gson().fromJson(sortedStateCodeData,IndianStateCode[].class);
            Assert.assertEquals("AD",stateCSV[0].getStateCode());
        } catch (MyCensusException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIndianStateCodeData_WhenSortedOnStateCode_ShouldReturnLastSortedResult() {
        try {
            censusAnalyserProblem.loadIndiaStateCodeData(INDIAN_STATE_CODE_INFORMATION_PATH);
            String sortedStateCodeData = censusAnalyserProblem.getSortedStateCodeData();
            IndianStateCode[] stateCSV = new Gson().fromJson(sortedStateCodeData,IndianStateCode[].class);
            Assert.assertEquals("WB",stateCSV[36].getStateCode());
        } catch (MyCensusException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIndianStateCodeData_WhenImproperFile_ShouldThrowException() {
        try {
            censusAnalyserProblem.loadIndiaStateCodeData(INDIAN_STATE_CODE_INFORMATION_PATH);
            String sortedStateCodeData = censusAnalyserProblem.getSortedStateCodeData();
            IndianStateCode[] stateCSV = new Gson().fromJson(sortedStateCodeData,IndianStateCode[].class);
            Assert.assertEquals("WB",stateCSV[36].getStateCode());
        } catch (MyCensusException e) {
            Assert.assertEquals(MyCensusException.MyException_Type.NO_SUCH_CENSUS_DATA,e.type);
        }
    }

    @Test
    public void givenTheStateCensusData_WhenSortedOnPopulation_ShouldReturnSortedResult() {
        try {
            censusAnalyserProblem.loadIndiaCensusData(STATE_CENSUS_DATA_PATH);
            String sortedCensusData = censusAnalyserProblem.getPopulationWiseSortedCensusData();
            CensusDAO[] censusCSV = new Gson().fromJson(sortedCensusData, CensusDAO[].class);
            Assert.assertEquals(199812341,censusCSV[0].population);
        } catch (MyCensusException e) {
            e.getStackTrace();
        }
    }

    @Test
    public void givenTheStateCensusData_WhenSortedOnDensityPerSqKm_ShouldReturnSortedResult() {
        try {
            censusAnalyserProblem.loadIndiaCensusData(STATE_CENSUS_DATA_PATH);
            String sortedCensusData = censusAnalyserProblem.getPopulationDensityWiseSortedCensusData();
            CensusDAO[] censusCSV = new Gson().fromJson(sortedCensusData, CensusDAO[].class);
            Assert.assertEquals(1102,censusCSV[0].densityPerSqKm);
        } catch (MyCensusException e) {
            e.getStackTrace();
        }
    }

    @Test
    public void givenTheStateCensusData_WhenSortedOnAreaInPerSqKm_ShouldReturnSortedResult() {
        try {
            censusAnalyserProblem.loadIndiaCensusData(STATE_CENSUS_DATA_PATH);
            String sortedCensusData = censusAnalyserProblem.getAreaWiseSortedCensusData();
            CensusDAO[] csvStateCensuses = new Gson().fromJson(sortedCensusData, CensusDAO[].class);
            Assert.assertEquals(342239, csvStateCensuses[0].areaInSqKm);
        } catch (MyCensusException e) {
            e.getStackTrace();
        }
    }
}