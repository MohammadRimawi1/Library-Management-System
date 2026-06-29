package com.exalt.library.exceptions;

//Fixed

/**
 * a class that throws an unchecked exception if there was no book found
 * @author Mohammad Rimawi
 */
public class BookNotFoundException extends RuntimeException {
    /**
     * a parameterized constructor to define the message that comes from the parent class
     * @param message
     */
    public BookNotFoundException(String message) {
        super(message);
    }
}
