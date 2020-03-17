package com.bridgelabz.censusanalyserproject;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;

public class StateCensusAnalyser {

    //METHOD TO LOAD THE CSV FILE AND GET
    public int loadIndiaCensusData(String csvFilePath)  {
        int recordCount=0;
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
                 System.out.print("state:"+censusCSV.getState()+", ");
                 System.out.print("population"+censusCSV.getPopulation()+", ");
                 System.out.print("area"+censusCSV.getAreaInSqKm()+", ");
                 System.out.print("density"+censusCSV.getDensityPerSqKm()+", ");
                 System.out.println();
             }
         } catch (IOException e) {
        e.printStackTrace();
    }
        return recordCount;
    }

    public static void main(String[] args)  {
        System.out.println("Welcome to Indian States Census Analyser Problem");
    }
}



