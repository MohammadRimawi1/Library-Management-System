package com.exalt.library.controllers;

import com.exalt.library.controllers.dto.LibraryItemDTO;
import com.exalt.library.models.Author;
import com.exalt.library.models.libraryitems.LibraryItem;
import com.exalt.library.models.libraryitems.physicalitems.PhysicalItem;
import com.exalt.library.services.LibraryItemServices;
import com.exalt.library.services.factory.LibraryItemFactory;
import com.exalt.library.util.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * a library item controller that gets a request from the client, does a specific job then returns the response
 * @author Mohammad Rimawi
 */
@RestController
@RequestMapping("/api/library-items")
public class LibraryItemController {
    private final LibraryItemServices libraryItemServices; // Defines the library item services

    /**
     * constructor injection
     * @param libraryItemServices
     */
    public LibraryItemController(LibraryItemServices libraryItemServices) {
        this.libraryItemServices = libraryItemServices;
    }

    /**
     * a method for fetching all the library items
     * exists on: /api/library-items
     * @return
     */
    @GetMapping
    public ResponseEntity<Map<String, Object>> findAll() {
        return ResponseEntity.ok(ApiResponse.success(200, libraryItemServices.getAllItems()));
    }

    /**
     * a method for fetching a specific library-item
     * exists on: /api/library-items/{id}
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> findById(@PathVariable String id) {
        LibraryItem libraryItem = libraryItemServices.findItemById(id);
        return ResponseEntity.ok(ApiResponse.success(200, libraryItem));
    }

    /**
     * a method for creating a library item
     * exists on: /api/library-items
     * @param libraryItemDTO
     * @return
     */
    @PostMapping
    public ResponseEntity<Map<String, Object>> create(@Valid @RequestBody LibraryItemDTO libraryItemDTO) {
        LibraryItem item = libraryItemServices.createItem(libraryItemDTO);

        return ResponseEntity.status(201).body(ApiResponse.success(201, item));
    }
}