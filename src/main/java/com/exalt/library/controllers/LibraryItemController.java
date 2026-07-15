package com.exalt.library.controllers;

import com.exalt.library.controllers.dto.CreateLibraryItemRequest;
import com.exalt.library.models.Author;
import com.exalt.library.models.SingletonLibrary;
import com.exalt.library.models.libraryitems.LibraryItem;
import com.exalt.library.models.libraryitems.physicalitems.PhysicalItem;
import com.exalt.library.services.LibraryItemServices;
import com.exalt.library.services.factory.LibraryItemFactory;
import com.exalt.library.util.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * a library item controller that gets a request from the client, does a specific job then returns the response
 * @author Mohammad Rimawi
 */
@RestController
@RequestMapping("/api/library-items")
public class LibraryItemController {
    private LibraryItemServices libraryItemServices = new LibraryItemServices(); // defines the library item services

    /**
     * a method for fetching all the library items
     * exists on: /api/library-items
     * @return
     */
    @GetMapping
    public ResponseEntity<Map<String, Object>> findAll() {
        List<LibraryItem> libraryItems = SingletonLibrary.getInstance().getLibraryItems();
        return ResponseEntity.ok(ApiResponse.success(200, libraryItems));
    }

    /**
     * a method for fetching a specific library-item
     * exists on: /api/library-items/{id}
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> findById(@PathVariable int id) {
        LibraryItem libraryItem = libraryItemServices.findItemById(SingletonLibrary.getInstance().getLibraryItems(), id);
        return ResponseEntity.ok(ApiResponse.success(200, libraryItem));
    }

    /**
     * a method for creating a library item
     * exists on: /api/library-items
     * @param request
     * @return
     */
    @PostMapping
    public ResponseEntity<Map<String, Object>> create(@RequestBody CreateLibraryItemRequest request) {
        if (request.getType() == null) {
            throw new IllegalArgumentException("Missing required field: type");
        }

        LibraryItem libraryItem = LibraryItemFactory.create(request.getType());
        libraryItem.setTitle(request.getTitle());

        if (libraryItem instanceof PhysicalItem physicalItem && request.getNumOfCopies() != null) {
            physicalItem.setNumOfCopies(request.getNumOfCopies());
        }

        if (request.getAuthor() != null) {
            Author author = new Author();
            author.setName(request.getAuthor().getName());
            author.setNationality(request.getAuthor().getNationality());
            libraryItem.setAuthor(author);
        }

        libraryItemServices.addItem(SingletonLibrary.getInstance().getLibraryItems(), libraryItem);

        return ResponseEntity.status(201).body(ApiResponse.success(201, libraryItem));
    }
}