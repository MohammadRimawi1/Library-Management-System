package com.exalt.library.controllers.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

/**
 * a class representing the request for a library item
 * @author Mohammad Rimawi
 */
public class CreateLibraryItemRequest {
    @NotBlank(message = "Type is required!")
    private String type; // Defines the type
    @NotBlank(message = "Title is required!")
    private String title; // Defines the title
    private Integer numOfCopies; // only used for physical items, Integer so it can be null
    @Valid
    private AuthorRequest author; // Represents the author request

//    ==== GETTERS ====
    /**
     * a method for getting the type
     * @return
     */
    public String getType() { return type; }

    /**
     * a method for getting the title
     * @return
     */
    public String getTitle() { return title; }

    /**
     * a method for getting the copies
     * @return
     */
    public Integer getNumOfCopies() { return numOfCopies; }

    /**
     * a method for getting the author
     * @return
     */
    public AuthorRequest getAuthor() { return author; }
//    ==== GETTERS ====

//    ==== SETTERS ====

    /**
     * a method for setting the type
     * @param type
     */
    public void setType(String type) { this.type = type; }

    /**
     * a method for setting the title
     * @param title
     */
    public void setTitle(String title) { this.title = title; }

    /**
     * a method for setting the copies
     * @param numOfCopies
     */
    public void setNumOfCopies(Integer numOfCopies) { this.numOfCopies = numOfCopies; }

    /**
     * a method for setting the author
     * @param author
     */
    public void setAuthor(AuthorRequest author) { this.author = author; }
//    ==== SETTERS ====
}