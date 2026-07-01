package com.exalt.library.exceptions;
//Fixed

/**
 * a class that throws an unchecked exception if the item was unavailable
 * @author Mohammad Rimawi
 */
public class ItemUnavailableException extends RuntimeException {
    /**
     * a parameterized constructor to define the message that comes from the parent class
     * @param message
     */
    public ItemUnavailableException(String message) {
        super(message);
    }
}
