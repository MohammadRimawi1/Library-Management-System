package com.exalt.library.services;

import com.exalt.library.repositories.LibraryItemRepository;
import com.exalt.library.services.operations.LibraryItemOperations;
import com.exalt.library.exceptions.ItemNotFoundException;
import com.exalt.library.models.libraryitems.LibraryItem;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * a class representing the services of the items
 * it implements the Library Item operations
 * @author Mohammad Rimawi
 */
@Service
public class LibraryItemServices implements LibraryItemOperations {

    private final LibraryItemRepository libraryItemRepository; // Defines the libraryItems repository
    /**
     * Constructor injection
     */
    public LibraryItemServices(LibraryItemRepository libraryItemRepository) {
        this.libraryItemRepository = libraryItemRepository;
    }

    /**
     * a method for returning all the library items
     * @return
     */
    @Override
    public List<LibraryItem> getAllItems() {
        return libraryItemRepository.findAll();
    }

    /**
     * a method is used to add an item of type LibraryItem to the items repository
     * @param item
     */
    @Override
    public void addItem(LibraryItem item) {
        libraryItemRepository.save(item);
    }

    /**
     * a method used to print all the LibraryItems inside the items repository
     */
    @Override
    public List<LibraryItem> printAllItems() {
        return libraryItemRepository.findAll();
    }

    /**
     *  a method used to find a specific item based on its id
     * @param id
     * @return returns an item if it exists
     * @throws ItemNotFoundException if the item doesn't exist
     */
    @Override
    public LibraryItem findItemById(String id) {
        return libraryItemRepository.findById(id)
                .orElseThrow(() -> new ItemNotFoundException("Item was not found"));
    }

    /**
     * a method for checking if the item exists or not
     * @param id - represents the item id
     * @return - returns true or false based if it exists in the list or not
     */
    @Override
    public boolean itemExists(String id) {
        return libraryItemRepository.existsById(id);
    }

    /**
     * a method for checking all items if they have titles or not
     * @return - true if ALL items have titles, false if AT LEAST one doesn't have
     */
    @Override
    public boolean allItemsHaveTitles() {
        List<LibraryItem> items = libraryItemRepository.findAll();
        return items.stream() // this turns the items into a stream
                .noneMatch(item -> item == null || item.getTitle() == null || item.getTitle().trim().isEmpty());
    }

    /**
     * a method for sorting the items based on alphabetical ascending order
     * @return a list of sorted items
     */
    @Override
    public List<LibraryItem> sortItemsByTitle() {
        List<LibraryItem> items = libraryItemRepository.findAll();
        return items.stream() // this turns the items into a stream
                .sorted(Comparator.comparing(LibraryItem::getTitle)) //create a new sorted stream
                .collect(Collectors.toList()); //terminal operation to accumulate elements from a stream into a mutable container
    }

    /**
     * a method for checking if all the items are available or not
     * @return true if all are available, false if AT LEAST one isn't
     */
    @Override
    public boolean areAllItemsAvailable() {
        List<LibraryItem> items = libraryItemRepository.findAll();
        return items.stream() // this turns the items into a stream
                .allMatch(item -> item.isAvailable()); //check if all elements in the stream matches the condition
    }

    /**
     * a method for checking how many items exists in the items list
     * @return - the size as long
     */
    @Override
    public int getItemCount() {
        return (int) libraryItemRepository.count();
    }
}
