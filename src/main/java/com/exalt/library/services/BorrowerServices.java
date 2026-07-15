package com.exalt.library.services;

import com.exalt.library.exceptions.ItemNotFoundException;
import com.exalt.library.exceptions.BorrowerNotFoundException;
import com.exalt.library.models.Borrower;
import com.exalt.library.services.operations.BorrowerOperations;

import java.util.List;

/**
 * a class representing the services of the borrower
 * it implements the borrower operations
 * @author Mohammad Rimawi
 */
public class BorrowerServices implements BorrowerOperations {
    /**
     * A default constructor for instantiation
     */
    public BorrowerServices() { }

    /**
     * assignBorrower method is used to assign a borrower of type Borrower to the borrowers list
     * @param borrowers
     * @param borrower
     */
    @Override
    public void assignBorrower(List<Borrower> borrowers, Borrower borrower) {
        borrowers.add(borrower); //This adds the borrower to the borrowers list
    }

    /**
     * a method used to print all the borrowers inside the borrower list
     * @param borrowers
     */
    @Override
    public void printAllBorrowers(List<Borrower> borrowers) {
        borrowers.stream() // this turns the borrowers into a stream
                .forEach(borrower -> System.out.println(borrower)); //terminal operation for performing an action on each element of the stream
    }

    /**
     * a method used to find a specific borrower based on his/her id
     * @param borrowers
     * @param id
     * @return return a borrower if he/she exists
     * @throws ItemNotFoundException if the borrower doesn't exist
     */
    @Override
    public Borrower findBorrowerById(List<Borrower> borrowers, int id) {
        return borrowers.stream() // this turns the borrowers into a stream
                .filter(borrower -> borrower.getId() == id) // This filters the stream and gets what matches the condition
                .findFirst() // This returns an optional describing the first element of the stream
                .orElseThrow(() -> new BorrowerNotFoundException("Borrower was not found!")); // This should throw an exception if there was no borrower found
    }

    /**
     * a method for checking if the borrower exists or not
     * @param borrowers
     * @param id - represents the borrower id
     * @return - returns true or false based if it exists in the list or not
     */
    @Override
    public boolean borrowerExists(List<Borrower> borrowers, int id) {
        return borrowers.stream() // this turns the borrowers into a stream
                .anyMatch(borrower -> borrower.getId() == id); //check if any element in the stream matches the condition
    }

    /**
     * a method for checking how many borrowers exists in the borrowers list
     * @param borrowers
     * @return - the size as long
     */
    @Override
    public int getBorrowerCount(List<Borrower> borrowers) {
        return borrowers.size(); //Gets the size of the list
    } //Fixed
}
