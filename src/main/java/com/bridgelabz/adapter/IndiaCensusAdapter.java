package com.bridgelabz.adapter;

import com.bridgelabz.censusanalyserproject.*;
import com.bridgelabz.dao.CensusDAO;
import com.bridgelabz.dto.IndiaCensusCSV;
import com.bridgelabz.dto.IndianStateCode;
import com.bridgelabz.exception.CSVBuilderException;
import com.bridgelabz.exception.MyCensusException;
import com.bridgelabz.service.StateCensusAnalyser;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.StreamSupport;

public class IndiaCensusAdapter extends CensusAdapter {

    //CONSTANT FOR REGEX PATTERN
    private static final String PATTERN_FOR_CSV_FILE = "^[a-zA-Z0-9./_@]*[.]+[c][s][v]$";

    @Override
    public Map<String, CensusDAO> loadCensusData(String... csvFilePath) throws MyCensusException {
        Map<String, CensusDAO> censusMap = super.loadCensusData(IndiaCensusCSV.class, csvFilePath[0]);
        if (csvFilePath.length == 1)
            return censusMap;
        return this.loadStateCodeCensusData(censusMap, csvFilePath[1]);
    }

    //FUNCTION TO LOAD US CENSUS DATA
    private <E> Map<String, CensusDAO> loadStateCodeCensusData(Map<String, CensusDAO> censusMap, String csvFilePath) throws MyCensusException {
        String extension = StateCensusAnalyser.getFileExtension(csvFilePath);
        if (!Pattern.matches(PATTERN_FOR_CSV_FILE, extension))
            throw new MyCensusException(MyCensusException.MyException_Type.NO_SUCH_TYPE, "No such a type");
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))) {
            IcsvBuilder csvBuilder = CsvBuilderFactory.createCsvBuilder();
            Iterator<IndianStateCode> stateCensusIterator = csvBuilder.getCSVFileIterator(reader, IndianStateCode.class);
            Iterable<IndianStateCode> csvIterable = () -> stateCensusIterator;
            StreamSupport.stream(csvIterable.spliterator(), false)
                    .filter(csvState -> censusMap.get(csvState.getState()) != null)
                    .forEach(csvState -> censusMap.get(csvState.getState()).stateCode = csvState.getStateCode());
            return censusMap;
        } catch (RuntimeException e) {
            throw new MyCensusException(MyCensusException.MyException_Type.WRONG_DELIMITER_OR_HEADER, "Incorrect delimiter or header.");
        } catch (NoSuchFileException e) {
            throw new MyCensusException(MyCensusException.MyException_Type.FILE_NOT_FOUND, "Incorrect file.");
        } catch (CSVBuilderException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.getStackTrace();
        }
        return null;
    }
}