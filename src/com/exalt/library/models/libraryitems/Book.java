package com.exalt.library.models.libraryitems;

/**
 * A class representing the library book
 * Each book has a unique id
 * Each book can have a title and be available or not
 * @author Mohammad Rimawi
 */
public class Book extends LibraryItem {

    /**
     * A Default constructor that calls the LibraryItem constructor
     */
    public Book() {
        super();
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + getId() +
                ", title='" + getTitle() + '\'' +
                ", author=" + getAuthor() +
                ", isAvailable=" + isAvailable() +
                '}';
    }
}
