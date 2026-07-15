package com.exalt.library.models;

import jakarta.validation.constraints.NotBlank;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * A class representing the library borrower
 * Each borrower has a unique id, they also have names
 * @author Mohammad Rimawi
 */
@Document(collection = "borrowers")
public class Borrower {
    @Id
    private String id; // Defines the identity number for a borrower
    @NotBlank(message = "Name is required!")
    private String name; // Defines the name of the borrower

    /**
     * A default constructor
     */
    public Borrower() {

    }

//    ==== GETTERS ====
    /**
     * a method for getting the id value
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * a method for getting the title
     * @return the name
     */
    public String getName() {
        return name;
    }
//    ==== GETTERS ====

//    ==== SETTERS ====

    /**
     * a method for setting the name of the borrower
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }
//    ==== SETTERS ====

    @Override
    public String toString() {
        return "Borrower: {" +
                "id = " + id +
                ", name = '" + name + '\'' +
                '}';
    }
}
