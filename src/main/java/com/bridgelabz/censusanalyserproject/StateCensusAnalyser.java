package com.bridgelabz.censusanalyserproject;

import com.bridgelabz.exception.CSVBuilderException;
import com.bridgelabz.exception.MyCensusException;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.StreamSupport;

public class StateCensusAnalyser {

    //CONSTANT FOR REGEX PATTERN
    private static final String PATTERN_FOR_CSV_FILE = "^[a-zA-Z0-9./_@]*[.]+[c][s][v]$";

    List<IndiaCensusCSV> csvFileList = null;
    List<IndianStateCode> stateCodeList = null;

    //METHOD TO LOAD THE CSV FILE AND GET
    public int loadIndiaCensusData(String csvFilePath) throws MyCensusException {
        String extension = getFileExtension(csvFilePath);
        if (!Pattern.matches(PATTERN_FOR_CSV_FILE,extension))
            throw new MyCensusException(MyCensusException.MyException_Type.NO_SUCH_TYPE,"No such a type");
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath)))
        {
            IcsvBuilder csvBuilder = CsvBuilderFactory.createCsvBuilder();
            csvFileList = csvBuilder.getCSVFileList(reader, IndiaCensusCSV.class);
            return csvFileList.size();
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

    //METHOD TO LOAD THE CSV FILE AND GET
    public int loadIndianStateCodeData(String csvFilePath) throws MyCensusException {
        //LOCAL VARIABLE
        String extension = getFileExtension(csvFilePath);
        if (!Pattern.matches(PATTERN_FOR_CSV_FILE,extension))
            throw new MyCensusException(MyCensusException.MyException_Type.NO_SUCH_TYPE,"No such a type");
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath)))
        {
            IcsvBuilder csvBuilder = CsvBuilderFactory.createCsvBuilder();
            stateCodeList = csvBuilder.getCSVFileList(reader, IndianStateCode.class);
            return stateCodeList.size();
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
        if (csvFileList == null || csvFileList.size() == 0) {
            throw new MyCensusException( MyCensusException.MyException_Type.NO_SUCH_CENSUS_DATA,"Census data not found");
        }
        Comparator<IndiaCensusCSV> stateCodeComparator = Comparator.comparing(indiaCensusCSV -> indiaCensusCSV.getState());
        this.sort(stateCodeComparator, csvFileList);
        String sortedStateCodeJson = new Gson().toJson(csvFileList);
        return sortedStateCodeJson;
    }

    public String getSortedStateCodeData() throws MyCensusException {
        if (stateCodeList == null || stateCodeList.size() == 0) {
            throw new MyCensusException( MyCensusException.MyException_Type.NO_SUCH_CENSUS_DATA,"Census state code data not found");
        }
        Comparator<IndianStateCode> stateCodeComparator = Comparator.comparing(stateCode -> stateCode.getStateCode());
        this.sort(stateCodeComparator, stateCodeList);
        String sortedStateCodeJson = new Gson().toJson(stateCodeList);
        return sortedStateCodeJson;
    }

    //METHOD TO SORT CSV DATA
    private <T> void sort(Comparator<T> csvComparator, List<T> csvList) {
        for (int i = 0; i < csvList.size() - 1; i++) {
            for (int j = 0; j < csvList.size() - i - 1; j++) {
                T census1 = csvList.get(j);
                T census2 = csvList.get(j + 1);
                if (csvComparator.compare(census1, census2) > 0) {
                    csvList.set(j, census2);
                    csvList.set(j + 1, census1);
                }
            }
        }
    }

    public static void main(String[] args)  {
        System.out.println("Welcome to Indian States Census Analyser Problem");
    }
}