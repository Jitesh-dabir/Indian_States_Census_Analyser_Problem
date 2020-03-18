package com.bridgelabz.censusanalyserproject;

import com.bridgelabz.exception.MyCensusException;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;

public class CSVStates {
    //METHOD TO LOAD THE CSV FILE AND GET
    public int loadIndianStateCodeData(String csvFilePath) throws MyCensusException {
        //LOCAL VARIABLE
        int recordCount=0;
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath)))
        {
            CsvToBean<IndianStateCode> csvToBean = new CsvToBeanBuilder(reader)
                    .withType(IndianStateCode.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();
            Iterator<IndianStateCode>statesCSVIterator = csvToBean.iterator();
            while (statesCSVIterator.hasNext()) {
                IndianStateCode censusCSV = statesCSVIterator.next();
                ++recordCount;
                System.out.print("SrNo: "+censusCSV.getSrNo()+", ");
                System.out.print("state: "+censusCSV.getState()+", ");
                System.out.print("Name: "+censusCSV.getName()+", ");
                System.out.print("TIN: "+censusCSV.getTin()+", ");
                System.out.print("StateCode: "+censusCSV.getStateCode()+", ");
                System.out.println();
            }
        } catch (FileNotFoundException e) {
            throw new MyCensusException(MyCensusException.MyException_Type.FILE_NOT_FOUND,"File not found");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return recordCount;
    }
}