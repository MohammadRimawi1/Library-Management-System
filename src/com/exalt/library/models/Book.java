package com.exalt.library.models;

/**
 * A class representing the library book
 * Each book has a unique id
 * Each book can have a title and be available or not
 * @author Mohammad Rimawi
 */
public class Book {
    private int id; // Defines the identity number for a book
    private static int count = 1; // a counter to automatically assigns an id for the book
    private String title; // Defines the title for a book
    private boolean isAvailable; // Defines if the book is available or not

    /**
     * A parameterized constructor that takes title and isAvailable attributes
     * automatically increments the counter for the id
     * @param title
     * @param isAvailable
     */
    public Book(String title, boolean isAvailable) {
        this.id = count;
        this.title = title;
        this.isAvailable = isAvailable;
        count++;
    }
//    ==== GETTERS ====

    /**
     * a method for getting the id value
     * @return
     */
    public int getId() {
        return id;
    }

    /**
     * a method for getting the title
     * @return
     */
    public String getTitle() {
        return title;
    }

    /**
     * a method for getting the value of isAvailable
     * @return
     */
    public boolean isAvailable() {
        return isAvailable;
    }
//    ==== GETTERS ====

//    ==== SETTERS ====

    /**
     * a method for setting the value of isAvailable
     * @param available
     */
    public void setAvailable(boolean available) {
        isAvailable = available;
    }
//    ==== SETTERS ====

    @Override
    public String toString() {
        return "Book: {ID = " + id + ", " +
                "title = '" + title +
                ", isAvailable = " + isAvailable +
                '}';
    }
}
