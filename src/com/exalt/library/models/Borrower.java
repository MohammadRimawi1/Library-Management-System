package com.exalt.library.models;
/**
 * A class representing the library borrower
 * Each borrower has a unique id, they also have names, and a list of borrowed books
 * @author Mohammad Rimawi
 */
public class Borrower {
    private final int id; // Defines the identity number for a borrower
    // #TODO: a counter to automatically assigns an id for the book, ITS THE JOB OF THE DB
    private static int count = 1; // Defines the counter that we will increment #TODO: Update Later
    private String name; // Defines the name of the borrower

    /**
     * A default constructor
     * automatically increments the counter for the id
     */
    public Borrower() {
        this.id = generate();
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

//    ==== SETTERS ====

    /**
     * a method for setting the name of the borrower
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }
//    ==== SETTERS ====

    /**
     * A synchronized generator so we get no duplicates
     * @return - an int representing the value of the current counter
     */
    public static synchronized int generate() {
        return count++;
    }

    @Override
    public String toString() {
        return "Borrower: {" +
                "id = " + id +
                ", name = '" + name + '\'' +
                '}';
    }
}
