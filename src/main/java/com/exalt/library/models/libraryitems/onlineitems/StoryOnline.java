package com.exalt.library.models.libraryitems.onlineitems;

/**
 * A class representing the online story that exists in the library as an online object
 * @author Mohammad Rimawi
 */
public class StoryOnline extends OnlineItem {
    /**
     * A Default constructor that calls the OnlineItem constructor
     */
    public StoryOnline() {
        super();
    }

    @Override
    public String toString() {
        return "Online Story {" +
                "id=" + getId() +
                ", title='" + getTitle() + '\'' +
                ", author=" + getAuthor() +
                ", isAvailable=" + isAvailable() +
                '}';
    }
}
