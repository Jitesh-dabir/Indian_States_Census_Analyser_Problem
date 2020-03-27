package com.bridgelabz.censusanalyserproject;

import com.bridgelabz.exception.CSVBuilderException;
import com.bridgelabz.exception.MyCensusException;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class StateCensusAnalyser {

    //CONSTANT FOR REGEX PATTERN
    private static final String PATTERN_FOR_CSV_FILE = "^[a-zA-Z0-9./_@]*[.]+[c][s][v]$";

    List<CensusDAO> censusList = null;
    Map<String, CensusDAO> censusMap = null;

    public StateCensusAnalyser() {
        this.censusMap = new HashMap<>();
        this.censusList = new ArrayList<>();
    }

    //METHOD TO LOAD THE CSV FILE
    public int loadIndiaCensusData(String csvFilePath) throws MyCensusException {
        String extension = getFileExtension(csvFilePath);
        if (!Pattern.matches(PATTERN_FOR_CSV_FILE,extension))
            throw new MyCensusException(MyCensusException.MyException_Type.NO_SUCH_TYPE,"No such a type");
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))) {
            IcsvBuilder csvBuilder = CsvBuilderFactory.createCsvBuilder();
            Iterator<IndiaCensusCSV> stateCensusIterator = csvBuilder.getCSVFileIterator(reader,IndiaCensusCSV.class);
            while (stateCensusIterator.hasNext()) {
                CensusDAO censusDAO = new CensusDAO(stateCensusIterator.next());
                this.censusMap.put(censusDAO.state, censusDAO);
                censusList = censusMap.values().stream().collect(Collectors.toList());
            }
            return censusMap.size();
        }  catch (RuntimeException e) {
            throw new MyCensusException(MyCensusException.MyException_Type.WRONG_DELIMITER_OR_HEADER,
                    "Delimiter or header not found");
        } catch (NoSuchFileException e) {
            throw new MyCensusException(MyCensusException.MyException_Type.FILE_NOT_FOUND,
                    "File not found");
        } catch (CSVBuilderException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    //METHOD TO LOAD THE CSV FILE
    public int loadIndianStateCodeData(String csvFilePath) throws MyCensusException {
        //LOCAL VARIABLE
        String extension = getFileExtension(csvFilePath);
        if (!Pattern.matches(PATTERN_FOR_CSV_FILE,extension))
            throw new MyCensusException(MyCensusException.MyException_Type.NO_SUCH_TYPE,"No such a type");
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))) {
            IcsvBuilder csvBuilder = CsvBuilderFactory.createCsvBuilder();
            Iterator<IndianStateCode> stateCensusIterator = csvBuilder.getCSVFileIterator(reader,IndianStateCode.class);
            while (stateCensusIterator.hasNext()) {
                CensusDAO censusDAO = new CensusDAO(stateCensusIterator.next());
                this.censusMap.put(censusDAO.stateCode, censusDAO);
                censusList = censusMap.values().stream().collect(Collectors.toList());
            }
            return censusMap.size();
        } catch (RuntimeException e) {
            throw new MyCensusException(MyCensusException.MyException_Type.WRONG_DELIMITER_OR_HEADER,
                    "delimiter and header");
        } catch (NoSuchFileException e) {
            throw new MyCensusException(MyCensusException.MyException_Type.FILE_NOT_FOUND,
                    "File not found");
        } catch (CSVBuilderException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    //METHOD TO GET EXTENSION OF CSV FILE
    private static String getFileExtension(String file) {
        String extension = "";
        try {
            if (file != null) {
                extension = file.substring(file.lastIndexOf("."));
            }
        } catch (Exception e) {
            extension = "";
        }
        return extension;
    }

    //METHOD TO GET COUNT OF RECORDS
    private <E> int getCount(Iterator <E> iterator) {
        Iterable<E> iterable = () -> iterator;
        int recordCount= (int) StreamSupport.stream(iterable.spliterator(),false).count();
        return recordCount;
    }

    public String getSortedCensusStateData() throws MyCensusException {
        if (censusList == null || censusList.size() == 0) {
            throw new MyCensusException( MyCensusException.MyException_Type.NO_SUCH_CENSUS_DATA,"Census data not found");
        }
        Comparator<CensusDAO> censusComparator = Comparator.comparing(censusDAO -> censusDAO.state);
        this.sortCSVData(censusComparator);
        String sortedStateCensusJson = new Gson().toJson(censusList);
        return sortedStateCensusJson;
    }

    public String getSortedStateCodeData() throws MyCensusException {
        if (censusList == null || censusList.size() == 0) {
            throw new MyCensusException( MyCensusException.MyException_Type.NO_SUCH_CENSUS_DATA,"Census state code data not found");
        }
        Comparator<CensusDAO> stateCodeComparator = Comparator.comparing(censusDAO -> censusDAO.stateCode);
        this.sortCSVData(stateCodeComparator);
        String sortedStateCodeJson = new Gson().toJson(censusList);
        return sortedStateCodeJson;
    }

    //METHOD TO SORT STATE CENSUS DATA BY POPULATION
    public String getPopulationWiseSortedCensusData() throws MyCensusException {
        if (censusList == null || censusList.size() == 0) {
            throw new MyCensusException(MyCensusException.MyException_Type.NO_CENSUS_DATA, "No census data");
        }
        Comparator<CensusDAO> censusComparator = Comparator.comparing(censusDAO -> censusDAO.population);
        this.sortCSVData(censusComparator);
        Collections.reverse(censusList);
        String sortedStateCensusJson = new Gson().toJson(censusList);
        return sortedStateCensusJson;
    }

    //METHOD TO SORT STATE CENSUS DATA BY POPULATION DENSITY
    public String getPopulationDensityWiseSortedCensusData() throws MyCensusException {
        if (censusList == null || censusList.size() == 0) {
            throw new MyCensusException(MyCensusException.MyException_Type.NO_CENSUS_DATA, "No census data");
        }
        Comparator<CensusDAO> censusComparator = Comparator.comparing(censusDAO -> censusDAO.densityPerSqKm);
        this.sortCSVData(censusComparator);
        Collections.reverse(censusList);
        String sortedStateCensusJson = new Gson().toJson(censusList);
        return sortedStateCensusJson;
    }

    //METHOD TO SORT CSV DATA
    private void sortCSVData(Comparator<CensusDAO> csvComparator) {
        for (int i = 0; i < censusList.size() - 1; i++) {
            for (int j = 0; j < censusList.size() - i - 1; j++) {
                CensusDAO census1 = censusList.get(j);
                CensusDAO census2 = censusList.get(j + 1);
                if (csvComparator.compare(census1, census2) > 0) {
                    censusList.set(j, census2);
                    censusList.set(j + 1, census1);
                }
            }
        }
    }

    public static void main(String[] args)  {
        System.out.println("Welcome to Indian States Census Analyser Problem");
    }
}