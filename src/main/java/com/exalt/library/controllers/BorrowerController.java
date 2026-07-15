package com.exalt.library.controllers;

import com.exalt.library.controllers.dto.CreateBorrowerRequest;
import com.exalt.library.models.Borrower;
import com.exalt.library.models.SingletonLibrary;
import com.exalt.library.services.BorrowerServices;
import com.exalt.library.util.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * a borrower controller that gets a request from the client, does a specific job then returns the response
 * @author Mohammad Rimawi
 */
@RestController
@RequestMapping("/api/borrowers")
public class BorrowerController {
    private final BorrowerServices borrowerServices; // Defines the borrower services
    private final SingletonLibrary library; // defines the singleton library

    /**
     * constructor injection
     * @param borrowerServices
     * @param library
     */
    public BorrowerController(BorrowerServices borrowerServices, SingletonLibrary library) {
        this.borrowerServices = borrowerServices;
        this.library = library;
    }

    /**
     * a method for fetching all the borrowers
     * exists on: /api/borrowers
     * @return
     */
    @GetMapping
    public ResponseEntity<Map<String, Object>> findAll() {
        List<Borrower> borrowers = library.getBorrowers();
        return ResponseEntity.ok(ApiResponse.success(200, borrowers));
    }

    /**
     * a method for fetching the borrowers count
     * exists on: /api/borrowers/count
     * @return
     */
    @GetMapping("/count")
    public ResponseEntity<Map<String, Object>> count() {
        int count = borrowerServices.getBorrowerCount(library.getBorrowers());
        return ResponseEntity.ok(ApiResponse.success(200, count));
    }

    /**
     * a method for fetching a specific borrower
     * /api/borrowers/{id}
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> findById(@PathVariable int id) {
        Borrower borrower = borrowerServices.findBorrowerById(library.getBorrowers(), id);
        return ResponseEntity.ok(ApiResponse.success(200, borrower));
    }

    /**
     * a method for creating a borrower
     * /api/borrowers
     * @param request
     * @return
     */
    @PostMapping
    public ResponseEntity<Map<String, Object>> create(@Valid @RequestBody CreateBorrowerRequest request) {
        Borrower borrower = new Borrower();
        borrower.setName(request.getName());
        borrowerServices.assignBorrower(library.getBorrowers(), borrower);

        return ResponseEntity.status(201).body(ApiResponse.success(201, borrower));
    }

}
