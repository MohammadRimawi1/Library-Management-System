package com.exalt.library.controllers;

import com.exalt.library.dto.BorrowerDTO;
import com.exalt.library.models.Borrower;
import com.exalt.library.services.BorrowerServices;
import com.exalt.library.util.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * a borrower controller that gets a request from the client, does a specific job then returns the response
 * @author Mohammad Rimawi
 */
@RestController
@RequestMapping("/api/borrowers")
public class BorrowerController {
    private final BorrowerServices borrowerServices; // Defines the borrower services

    /**
     * constructor injection
     * @param borrowerServices
     */
    public BorrowerController(BorrowerServices borrowerServices) {
        this.borrowerServices = borrowerServices;
    }

    /**
     * a method for fetching all the borrowers
     * exists on: /api/borrowers
     * @return
     */
    @GetMapping
    public ResponseEntity<Map<String, Object>> findAll() {
        return ResponseEntity.ok(ApiResponse.success(200, borrowerServices.getAllBorrowers()));
    }

    /**
     * a method for fetching the borrowers count
     * exists on: /api/borrowers/count
     * @return
     */
    @GetMapping("/count")
    public ResponseEntity<Map<String, Object>> count() {
        int count = borrowerServices.getBorrowerCount();
        return ResponseEntity.ok(ApiResponse.success(200, count));
    }

    /**
     * a method for fetching a specific borrower
     * /api/borrowers/{id}
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> findById(@PathVariable String id) {
        Borrower borrower = borrowerServices.findBorrowerById(id);
        return ResponseEntity.ok(ApiResponse.success(200, borrower));
    }

    /**
     * a method for creating a borrower
     * /api/borrowers
     * @param borrowerDTO
     * @return
     */
    @PostMapping
    public ResponseEntity<Map<String, Object>> create(@Valid @RequestBody BorrowerDTO borrowerDTO) {
        Borrower borrower = borrowerServices.createBorrower(borrowerDTO);

        return ResponseEntity.status(201).body(ApiResponse.success(201, borrower));
    }

}
