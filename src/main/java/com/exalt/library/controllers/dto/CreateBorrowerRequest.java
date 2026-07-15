package com.exalt.library.controllers.dto;

/**
 * a class representing the request for a borrower
 * @author Mohammad Rimawi
 */
public class CreateBorrowerRequest {
    private String name; // Represents the name of the borrower

//    ==== GETTER ====
    /**
     * a method for getting the name
     * @return
     */
    public String getName() { return name; }
//    ==== GETTER ====

//    ==== SETTER ====
    /**
     * a method for setting the name
     * @param name
     */
    public void setName(String name) { this.name = name; }
//    ==== SETTER ====
}