package com.bridgelabz.censusanalyserproject;

import com.bridgelabz.exception.CSVBuilderException;
import com.bridgelabz.exception.MyCensusException;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public abstract class CensusAdapter {

    //CONSTANT FOR REGEX PATTERN
    private static final String PATTERN_FOR_CSV_FILE = "^[a-zA-Z0-9./_@]*[.]+[c][s][v]$";
    Map<String, CensusDAO> censusMap = new HashMap<>();

    public abstract Map<String, CensusDAO> loadCensusData(String... csvFilePath) throws MyCensusException;

    public <E> Map<String, CensusDAO> loadCensusData(Class<E> censusCsvClass,String... csvFilePath) throws MyCensusException {
        String extension = StateCensusAnalyser.getFileExtension(csvFilePath[0]);
        if (!Pattern.matches(PATTERN_FOR_CSV_FILE, extension))
            throw new MyCensusException(MyCensusException.MyException_Type.NO_SUCH_TYPE, "No such a type");
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath[0]))) {
            IcsvBuilder csvBuilder = CsvBuilderFactory.createCsvBuilder();
            Iterator<E> stateCensusIterator = csvBuilder.getCSVFileIterator(reader, censusCsvClass);
            Iterable<E> csvIterable = () -> stateCensusIterator;
            if (censusCsvClass.getName().contains("IndiaCensusCSV")) {
                StreamSupport.stream(csvIterable.spliterator(), false)
                        .map(IndiaCensusCSV.class::cast)
                        .forEach(censusCSV -> censusMap.put(censusCSV.getState(), new CensusDAO(censusCSV)));
                return censusMap;
            }
            if (censusCsvClass.getName().contains("USCensusCSV")) {
                StreamSupport.stream(csvIterable.spliterator(), false)
                        .map(USCensusCSV.class::cast)
                        .forEach(censusCSV -> censusMap.put(censusCSV.getState(), new CensusDAO(censusCSV)));
                return censusMap;
            }
        } catch (NoSuchFileException e) {
            throw new MyCensusException(MyCensusException.MyException_Type.FILE_NOT_FOUND, "File not found");
        } catch (IOException e) {
            throw new MyCensusException(MyCensusException.MyException_Type.CENSUS_FILE_PROBLEM, "File problem");
        } catch (CSVBuilderException e) {
            e.printStackTrace();
        } catch (RuntimeException e) {
            throw new MyCensusException(MyCensusException.MyException_Type.WRONG_DELIMITER_OR_HEADER, "Delimiter or header not found");
        }
        return censusMap;
    }
}
