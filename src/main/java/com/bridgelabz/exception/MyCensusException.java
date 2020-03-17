package com.bridgelabz.exception;

public class MyCensusException  extends Exception{

        // REPRESENT A FIXED SET OF CONSTANTS
        public enum MyException_Type {
            FILE_NOT_FOUND,NO_SUCH_TYPE,WRONG_DELIMITER;
        }
        public MyException_Type type;

        public MyCensusException(MyException_Type type, String message) {
            super(message);
            this.type=type;
        }
    }