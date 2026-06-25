package com.exalt.library.models;

import java.util.ArrayList;
import java.util.List;

/**
 * A class representing the library borrower
 * Each borrower has a unique id, they also have names, and a list of borrowed books
 * @author Mohammad Rimawi
 */
public class Borrower {
    private final int id; // Defines the identity number for a borrower
    private static int count = 1; // a counter to automatically assigns an id for the borrower
    private String name; // Defines the name of the borrower

    /**
     * A parameterized constructor that takes the name
     * automatically increments the counter for the id
     * @param name
     */
    public Borrower(String name) {
        this.id = count;
        this.name = name;
        count++;
    }

//    ==== GETTERS ====

    /**
     * a method for getting the id value
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * a method for getting the title
     * @return the name
     */
    public String getName() {
        return name;
    }
//    ==== GETTERS ====


    @Override
    public String toString() {
        return "Borrower: {" +
                "id = " + id +
                ", name = '" + name + '\'' +
                '}';
    }
}
