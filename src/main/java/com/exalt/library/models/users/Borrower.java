package com.exalt.library.models.users;

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
    private String name; // Defines the name of the borrower
    private String phoneNumber; // Defines the phone number of the borrower

    /**
     * A default constructor
     */
    public Borrower() { }

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

    /**
     * a method for getting the phone number
     * @return the phone number
     */
    public String getPhoneNumber() {
        return phoneNumber;
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

    /**
     * a method for setting the phone number of the borrower
     * @param phoneNumber
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    //    ==== SETTERS ====

    @Override
    public String toString() {
        return "Borrower{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
