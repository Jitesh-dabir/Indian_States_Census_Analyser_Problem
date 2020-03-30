package com.bridgelabz.censusanalyserproject;

import com.bridgelabz.exception.CSVBuilderException;

import java.io.Reader;
import java.util.Iterator;
import java.util.List;

public interface IcsvBuilder {
    public <E> Iterator<E> getCSVFileIterator(Reader reader, Class<E> csvClass) throws CSVBuilderException;

    public <E> List<E> getCSVFileList(Reader reader, Class<E> csvClass) throws CSVBuilderException;
}