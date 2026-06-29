package com.exalt.library.exceptions;
//Fixed

/**
 * a class that throws an unchecked exception if the book was unavailable
 * @author Mohammad Rimawi
 */
public class BookUnavailableException extends RuntimeException {
    /**
     * a parameterized constructor to define the message that comes from the parent class
     * @param message
     */
    public BookUnavailableException(String message) {
        super(message);
    }
}
