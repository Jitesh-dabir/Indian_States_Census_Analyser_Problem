package com.bridgelabz.censusanalyserproject;

public class CsvBuilderFactory {
    public static IcsvBuilder createCsvBuilder() {
        return new com.bridgelabz.censusanalyserproject.CsvBuilder();
    }
}