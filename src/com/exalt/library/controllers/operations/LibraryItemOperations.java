package com.exalt.library.controllers.operations;

import com.exalt.library.exceptions.ItemNotFoundException;
import com.exalt.library.models.libraryitems.LibraryItem;

import java.util.List;

/**
 * an interface representing the operations for a Library Item in a library
 * implemented inside LibraryItemServices
 * @author Mohammad Rimawi
 */
public interface LibraryItemOperations {
    /**
     * a method is used to add an item of type LibraryItem to the books list
     * implemented inside LibraryItemServices
     * @param items
     * @param item
     */
    void addItem(List<LibraryItem> items, LibraryItem item);

    /**
     * a method used to print all the LibraryItems inside the items list
     * implemented inside LibraryItemsServices
     * @param items
     */
    void printAllItems(List<LibraryItem> items);

    /**
     *  a method used to find a specific item based on its id
     *  implemented inside LibraryItemsServices
     * @param items
     * @param id
     * @return returns a Book if it exists
     * @throws ItemNotFoundException if the item doesn't exist
     */
    LibraryItem findItemById(List<LibraryItem> items, int id);

    /**
     * a method for checking if the item exists or not
     * implemented inside LibraryItemsServices
     * @param items
     * @param id - represents the book id
     * @return - returns true or false based if it exists in the list or not
     */
    boolean itemExists(List<LibraryItem> items, int id);

    /**
     * a method for checking all items if they have titles or not
     * implemented inside LibraryItemsServices
     * @param items
     * @return - true if ALL items have titles, false if AT LEAST one doesn't have
     */
    boolean allItemsHaveTitles(List<LibraryItem> items);

    /**
     * a method for sorting the items based on alphabetical ascending order
     * implemented inside LibraryItemsServices
     * @param items
     * @return a list of sorted items
     */
    List<LibraryItem> sortItemsByTitle(List<LibraryItem> items);

    /**
     * a method for checking if all the items are available or not
     * implemented inside LibraryItemsServices
     * @param items
     * @return true if all are available, false if AT LEAST one isn't
     */
    boolean areAllItemsAvailable(List<LibraryItem> items);

    /**
     * a method that returns the sum of all items id's
     * implemented inside LibraryItemsServices
     * @param items
     * @return the sum of all IDs
     */
    int sumOfAllItemsIds(List<LibraryItem> items);

    /**
     * a method for checking how many items exists in the items list
     * implemented inside LibraryItemsServices
     * @param items
     * @return - the size as long
     */
    int getItemCount(List<LibraryItem> items);
}
