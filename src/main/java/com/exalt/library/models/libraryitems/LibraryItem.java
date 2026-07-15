package com.exalt.library.models.libraryitems;

import com.exalt.library.models.Author;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

/**
 * A class that defines the items in a library
 * there is a story or a book
 * @author Mohammad Rimawi
 */
@Document(collection = "library_items")
public abstract class LibraryItem {

    @Id
    private String id; // Defines the identity number for a book or a story
    private String title; // Defines the title for a book/story
    private Author author; // Defines the author of a specific book/story
    private boolean isAvailable; // Defines if the book is available or not

    /**
     * A Default constructor
     * sets the availability to true
     */
    public LibraryItem() {
        this.isAvailable = true;
    }


    //    ==== GETTERS ====
    /**
     * a method for getting the id value
     * @return the id
     */
    public String getId() {
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
}
