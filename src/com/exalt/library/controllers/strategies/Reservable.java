package com.exalt.library.controllers.strategies;

import com.exalt.library.models.libraryitems.LibraryItem;

/**
 * an interface which had a hold item method
 * @author Mohammad Rimawi
 */
public interface Reservable {
    /**
     * a method for holding a specific item and decrementing the num of copies for it
     * @param libraryItem
     */
    void holdItem(LibraryItem libraryItem);
}
