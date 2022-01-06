package com.epam.exception;

public class BookLimitExceededException extends  Exception{
    public  BookLimitExceededException(String errorMessage){
        super(errorMessage);
    }
}
