package com.exalt.library.controllers;

import com.exalt.library.services.BorrowerServices;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * a borrower controller that gets a request from the client, does a specific job then returns the response
 * @author Mohammad Rimawi
 */
@RestController
@RequestMapping("/api/borrowers")
public class BorrowerController {
    private BorrowerServices borrowerServices = new BorrowerServices(); // Defines the services that will be used inside this controller

}
