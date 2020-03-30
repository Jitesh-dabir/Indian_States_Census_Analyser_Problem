package com.bridgelabz.exception;

public class MyCensusException extends Exception {

    // REPRESENT A FIXED SET OF CONSTANTS
    public enum MyException_Type {
        FILE_NOT_FOUND, NO_SUCH_TYPE, NO_CENSUS_DATA, WRONG_DELIMITER_OR_HEADER, NO_SUCH_CENSUS_DATA, NO_SUCH_COUNTRY, CENSUS_FILE_PROBLEM;
    }

    public MyException_Type type;

    public MyCensusException(MyException_Type type, String message) {
        super(message);
        this.type = type;
    }
}