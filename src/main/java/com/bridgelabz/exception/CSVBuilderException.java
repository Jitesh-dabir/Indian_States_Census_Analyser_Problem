package com.bridgelabz.exception;

public class CSVBuilderException extends Exception {

    public MyException_Type type;

    //ENUM CLASS
    public enum MyException_Type {
        UNABLE_TO_PARSE, NO_SUCH_FILE, NO_SUCH_FILE_TYPE, NO_SUCH_DELIMITER_OR_HEADER;
    }

    //CONSTRUCTOR
    public CSVBuilderException(MyException_Type type, String message) {
        super(message);
        this.type = type;
    }
}