package com.exalt.library.services;

import com.exalt.library.exceptions.BookNotFoundException;
import com.exalt.library.exceptions.BorrowerNotFoundException;
import com.exalt.library.models.Borrower;
import com.exalt.library.operations.BorrowerOperations;

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
    public void assignBorrower(List<Borrower> borrowers, Borrower borrower) {
        borrowers.add(borrower);
    }

    /**
     * a method used to print all the borrowers inside the borrower list
     * @param borrowers
     */
    public void printAllBorrowers(List<Borrower> borrowers) {
        borrowers.stream()
                .forEach(borrower -> System.out.println(borrower));
    }

    /**
     * a method used to find a specific borrower based on his/her id
     * @param borrowers
     * @param id
     * @return return a borrower if he/she exists
     * @throws BookNotFoundException if the borrower doesn't exist
     */
    public Borrower findBorrowerById(List<Borrower> borrowers, int id) {
        return borrowers.stream()
                .filter(borrower -> borrower.getId() == id)
                .findFirst()
                .orElseThrow(() -> new BorrowerNotFoundException("Borrower was not found!"));
    }

    /**
     * a method for checking if the borrower exists or not
     * @param borrowers
     * @param id - represents the borrower id
     * @return - returns true or false based if it exists in the list or not
     */
    public boolean borrowerExists(List<Borrower> borrowers, int id) {
        return borrowers.stream()
                .anyMatch(borrower -> borrower.getId() == id);
    }

    /**
     * a method for checking how many borrowers exists in the borrowers list
     * @param borrowers
     * @return - the size as long
     */
    public int listHowMuchBorrowersExists(List<Borrower> borrowers) {
        return borrowers.size();
    }
}
