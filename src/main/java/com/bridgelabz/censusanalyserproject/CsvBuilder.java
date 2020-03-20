package com.bridgelabz.censusanalyserproject;

import com.bridgelabz.exception.MyCensusException;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import java.io.Reader;
import java.util.Iterator;

public class CsvBuilder implements IcsvBuilder  {

    //GENERIC METHOD TO GET CSV ITERATOR
        public <E> Iterator<E> getCSVFileIterator(Reader reader, Class<E> csvClass) throws MyCensusException {
                    CsvToBeanBuilder<E> csvToBeanBuilder = new CsvToBeanBuilder(reader);
                    csvToBeanBuilder.withType(csvClass);
                    csvToBeanBuilder.withIgnoreLeadingWhiteSpace(true);
                    CsvToBean<E> csvToBean = csvToBeanBuilder.build();
                    return csvToBean.iterator();
        }
}