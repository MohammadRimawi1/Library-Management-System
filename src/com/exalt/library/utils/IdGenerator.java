package com.exalt.library.utils;

/**
 * a class that generates a counter incrementally
 * @author Mohammad Rimawi
 */
public class IdGenerator {
    private static int count = 1; // Defines the counter that we will increment

    /**
     * A synchronized generator so we get no duplicates
     * @return - an int representing the value of the current counter
     */
    public static synchronized int generate() {
        return count++;
    }
}
