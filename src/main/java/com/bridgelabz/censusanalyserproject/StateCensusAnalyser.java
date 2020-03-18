package com.bridgelabz.censusanalyserproject;

import com.bridgelabz.exception.MyCensusException;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.Iterator;

public class StateCensusAnalyser {

    //METHOD TO LOAD THE CSV FILE AND GET
    public int loadIndiaCensusData(String csvFilePath) throws MyCensusException {
        int recordCount=0;
        String extension = getFileExtension(csvFilePath);
        if (!extension.equals(".csv"))
            throw new MyCensusException(MyCensusException.MyException_Type.NO_SUCH_TYPE,"No such a type");

        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath)))
        {
            CsvToBean<IndiaCensusCSV> csvToBean = new CsvToBeanBuilder(reader)
                    .withType(IndiaCensusCSV.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();
             Iterator<IndiaCensusCSV> censusCSVIterator = csvToBean.iterator();
             while (censusCSVIterator.hasNext()) {

                 System.out.print(recordCount+++"  ");
                 IndiaCensusCSV censusCSV = censusCSVIterator.next();
                 System.out.print("state: "+censusCSV.getState()+", ");
                 System.out.print("population: "+censusCSV.getPopulation()+", ");
                 System.out.print("area: "+censusCSV.getAreaInSqKm()+", ");
                 System.out.print("density: "+censusCSV.getDensityPerSqKm()+", ");
                 System.out.println();
             }
         } catch (RuntimeException e) {
            throw new MyCensusException(MyCensusException.MyException_Type.WRONG_DELIMITER_OR_HEADER,"Delimiter or header not found");
        }catch (NoSuchFileException e) {
            throw new MyCensusException(MyCensusException.MyException_Type.FILE_NOT_FOUND,"File not found");
        } catch (IOException e) {
            e.printStackTrace();
    }
        return recordCount;
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

    public static void main(String[] args)  {
        System.out.println("Welcome to Indian States Census Analyser Problem");
    }
}