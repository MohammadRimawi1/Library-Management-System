package com.exalt.library.controllers.services;

import com.exalt.library.controllers.operations.LibraryItemOperations;
import com.exalt.library.exceptions.ItemNotFoundException;
import com.exalt.library.models.libraryitems.LibraryItem;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * a class representing the services of the items
 * it implements the Library Item operations
 * @author Mohammad Rimawi
 */
public class LibraryItemServices implements LibraryItemOperations {

    /**
     * A default constructor for instantiation
     */
    public LibraryItemServices() { }

    /**
     * a method is used to add an item of type LibraryItem to the books list
     * @param items
     * @param item
     */
    @Override
    public void addItem(List<LibraryItem> items, LibraryItem item) {
        items.add(item); //This adds a book to the books list
    }

    /**
     * a method used to print all the LibraryItems inside the items list
     * @param items
     */
    @Override
    public void printAllItems(List<LibraryItem> items) {
        items.stream() // this turns the items into a stream
                .forEach(item -> System.out.println(item)); //terminal operation for performing an action on each element of the stream
    }

    /**
     *  a method used to find a specific item based on its id
     *  implemented inside LibraryItems
     * @param items
     * @param id
     * @return returns an item if it exists
     * @throws ItemNotFoundException if the item doesn't exist
     */
    @Override
    public LibraryItem findItemById(List<LibraryItem> items, int id) {
        return items.stream() // this turns the items into a stream
                .filter(item -> item.getId() == id)// This filters the stream and gets what matches the condition
                .findFirst() // This returns an optional describing the first element of the stream
                .orElseThrow(() -> new ItemNotFoundException("Item was not found!")); // This should throw an exception if there was no item found
    }

    /**
     * a method for checking if the item exists or not
     * implemented inside LibraryItems
     * @param items
     * @param id - represents the item id
     * @return - returns true or false based if it exists in the list or not
     */
    @Override
    public boolean itemExists(List<LibraryItem> items, int id) {
        return items.stream() // this turns the items into a stream
                .anyMatch(item -> item.getId() == id); //check if any element in the stream matches the condition
    }

    /**
     * a method for checking all items if they have titles or not
     * implemented inside LibraryItems
     * @param items
     * @return - true if ALL items have titles, false if AT LEAST one doesn't have
     */
    @Override
    public boolean allItemsHaveTitles(List<LibraryItem> items) {
        return items.stream() // this turns the items into a stream
                .noneMatch(item -> item == null || item.getTitle() == null || item.getTitle().trim().isEmpty());
    }

    /**
     * a method for sorting the items based on alphabetical ascending order
     * implemented inside LibraryItems
     * @param items
     * @return a list of sorted items
     */
    @Override
    public List<LibraryItem> sortItemsByTitle(List<LibraryItem> items) {
        return items.stream() // this turns the items into a stream
                .sorted(Comparator.comparing(LibraryItem::getTitle)) //create a new sorted stream
                .collect(Collectors.toList()); //terminal operation to accumulate elements from a stream into a mutable container
    }

    /**
     * a method for checking if all the items are available or not
     * implemented inside LibraryItems
     * @param items
     * @return true if all are available, false if AT LEAST one isn't
     */
    @Override
    public boolean areAllItemsAvailable(List<LibraryItem> items) {
        return items.stream() // this turns the items into a stream
                .allMatch(item -> item.isAvailable()); //check if all elements in the stream matches the condition
    }

    /**
     * a method that returns the sum of all items id's
     * implemented inside LibraryItems
     * @param items
     * @return the sum of all IDs
     */
    @Override
    public int sumOfAllItemsIds(List<LibraryItem> items) {
        return items.stream() // this turns the items into a stream
                .map(item -> item.getId()) //returns a stream after applying the function
                .reduce(0, Integer::sum);//combine all elements of a stream into a single result, then sum the id's
    }

    /**
     * a method for checking how many items exists in the items list
     * implemented inside LibraryItems
     * @param items
     * @return - the size as long
     */
    @Override
    public int getItemCount(List<LibraryItem> items) {
        return items.size();
    }
}
