package com.bridgelabz.censusanalyserproject;

import com.bridgelabz.exception.MyCensusException;

import java.io.Reader;
import java.util.Iterator;

public interface IcsvBuilder {
    public <E> Iterator <E> getCSVFileIterator(Reader reader, Class<E> csvClass) throws MyCensusException;
}