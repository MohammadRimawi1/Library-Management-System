package com.exalt.library.exceptions;
//Fixed

/**
 * a class that throws an unchecked exception if there was no loan found
 * @author Mohammad Rimawi
 */
public class LoanNotFoundException extends RuntimeException {
    /**
     * a parameterized constructor to define the message that comes from the parent class
     * @param message
     */
    public LoanNotFoundException(String message) {
        super(message);
    }
}
