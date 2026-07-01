package com.exalt.library.models.libraryitems;

/**
 * A class representing the story
 * is another type that the library has other than the book
 * @author Mohammad Rimawi
 */
public class Story extends LibraryItem {

    /**
     * A Default constructor that calls the LibraryItem class constructor
     */
    public Story() {
        super();
    }

    @Override
    public String toString() {
        return "Story{" +
                "id=" + getId() +
                ", title='" + getTitle() + '\'' +
                ", author=" + getAuthor() +
                ", isAvailable=" + isAvailable() +
                '}';
    }
}
