package com.exalt.library.models;

/**
 * A class representing the library book
 * Each book has a unique id
 * Each book can have a title and be available or not
 * @author Mohammad Rimawi
 */
public class Book {
    private int id; // Defines the identity number for a book
    // #TODO: a counter to automatically assigns an id for the book, ITS THE JOB OF THE DB
    private static int count = 1; // Defines the counter that we will increment #TODO: Update Later
    private String title; // Defines the title for a book
    private Author author; // Defines the author of a specific book
    private boolean isAvailable; // Defines if the book is available or not

    /**
     * A Default constructor
     * automatically increments the counter for the id and sets the availability to true
     */
    public Book() {
        this.id = generate(); // TODO: To assign the ID value from the DB
        this.isAvailable = true;
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
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * a method for getting the value of isAvailable
     * @return if they are available or not
     */
    public boolean isAvailable() {
        return isAvailable;
    }

    /**
     * a method for getting the value of the author
     * @return the author
     */
    public Author getAuthor() {
        return author;
    }
    //    ==== GETTERS ====

//    ==== SETTERS ====
    /**
     * a method for setting the title
     * @param title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * a method for setting the author
     * @param author
     */
    public void setAuthor(Author author) {
        this.author = author;
    }

    /**
     * a method for setting the value of isAvailable
     * @param available
     */
    public void setAvailable(boolean available) {
        isAvailable = available;
    }
//    ==== SETTERS ====

    /**
     * A synchronized generator so we get no duplicates
     * @return - an int representing the value of the current counter
     */
    public static synchronized int generate() {
        return count++;
    } //TODO: Delete this method when working with the DB, also, it violates SRP

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author=" + author +
                ", isAvailable=" + isAvailable +
                '}';
    }
}
