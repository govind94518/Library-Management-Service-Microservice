package com.epam.exception;

public class BookAlreadyIssuedException extends Exception {
    public BookAlreadyIssuedException(String errorMessage) {
        super(errorMessage);
    }
}
