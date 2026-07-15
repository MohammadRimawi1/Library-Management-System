package com.exalt.library.controllers.dto;

import jakarta.validation.constraints.NotBlank;

/**
 * a class representing the request for an author
 * @author Mohammad Rimawi
 */
public class AuthorRequest {
    @NotBlank(message = "Name is required!")
    private String name; // Defines the name of the author
    @NotBlank(message = "Nationality is required!")
    private String nationality; // Defines the nationality of the author

//    === GETTERS ====
    /**
     * a method for getting the name
     * @return
     */
    public String getName() { return name; }

    /**
     * a method for getting the nationality
     * @return
     */
    public String getNationality() { return nationality; }
//    === GETTERS ====

//    ==== SETTERS ====
    /**
     * a method for setting the name
     * @param name
     */
    public void setName(String name) { this.name = name; }

    /**
     * a method for setting the nationality
     * @param nationality
     */
    public void setNationality(String nationality) { this.nationality = nationality; }
//    ==== SETTERS ====
}