package com.exalt.library.controllers;

import com.exalt.library.controllers.dto.CreateBorrowerRequest;
import com.exalt.library.models.Borrower;
import com.exalt.library.models.SingletonLibrary;
import com.exalt.library.services.BorrowerServices;
import com.exalt.library.util.ApiResponse;
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
    private BorrowerServices borrowerServices = new BorrowerServices(); // Defines the services that will be used inside this controller

    /**
     * a method for fetching all the borrowers
     * exists on: /api/borrowers
     * @return
     */
    @GetMapping
    public ResponseEntity<Map<String, Object>> findAll() {
        List<Borrower> borrowers = SingletonLibrary.getInstance().getBorrowers();
        return ResponseEntity.ok(ApiResponse.success(200, borrowers));
    }

    /**
     * a method for fetching the borrowers count
     * exists on: /api/borrowers/count
     * @return
     */
    @GetMapping("/count")
    public ResponseEntity<Map<String, Object>> count() {
        int count = borrowerServices.getBorrowerCount(SingletonLibrary.getInstance().getBorrowers());
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
        Borrower borrower = borrowerServices.findBorrowerById(SingletonLibrary.getInstance().getBorrowers(), id);
        return ResponseEntity.ok(ApiResponse.success(200, borrower));
    }

    /**
     * a method for creating a borrower
     * /api/borrowers
     * @param request
     * @return
     */
    @PostMapping
    public ResponseEntity<Map<String, Object>> create(@RequestBody CreateBorrowerRequest request) {
        Borrower borrower = new Borrower();
        borrower.setName(request.getName());
        borrowerServices.assignBorrower(SingletonLibrary.getInstance().getBorrowers(), borrower);

        return ResponseEntity.status(201).body(ApiResponse.success(201, borrower));
    }

}
