package com.bridgelabz.exception;

public class CSVBuilderException extends Exception {

    public MyCensusException.MyException_Type type;

    //ENUM CLASS
     enum Exception_Type {
         UNABLE_TO_PARSE;
    }
    //CONSTRUCTOR
    public CSVBuilderException(MyCensusException.MyException_Type type,String message) {
        super(message);
        this.type = type;
    }
}