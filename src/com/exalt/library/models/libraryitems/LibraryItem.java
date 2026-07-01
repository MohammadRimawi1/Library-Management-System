package com.exalt.library.models.libraryitems;

import com.exalt.library.models.Author;

/**
 * A class that defines the items in a library
 * there is a story or a book
 * @author Mohammad Rimawi
 */
public abstract class LibraryItem {

    private final int id; // Defines the identity number for a book or a story
    // #TODO: a counter to automatically assigns an id for the book, ITS THE JOB OF THE DB
    private static int count = 1; // Defines the counter that we will increment #TODO: Update Later
    private String title; // Defines the title for a book/story
    private Author author; // Defines the author of a specific book/story
    private boolean isAvailable; // Defines if the book is available or not

    /**
     * A Default constructor
     * automatically increments the counter for the id and sets the availability to true
     */
    public LibraryItem() {
        this.id = generate();
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
     * a method for getting the value of the author
     * @return the author
     */
    public Author getAuthor() {
        return author;
    }

    /**
     * a method for getting the value of isAvailable
     * @return if they are available or not
     */
    public boolean isAvailable() {
        return isAvailable;
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
    private static synchronized int generate() {
        return count++;
    } //TODO: Delete this method when working with the DB, also, it violates SRP
}
