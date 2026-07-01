package com.exalt.library.controllers.operations;

import com.exalt.library.exceptions.ItemNotFoundException;
import com.exalt.library.models.Borrower;

import java.util.List;

/**
 * an interface representing the operations for a borrower in a library
 * @author Mohammad Rimawi
 */
public interface BorrowerOperations {
    //Fixed
    /**
     * assignBorrower method is used to assign a borrower of type Borrower to the borrowers list
     * implemented inside BorrowerServices
     * @param borrowers
     * @param borrower
     */
     void assignBorrower(List<Borrower> borrowers, Borrower borrower);

    /**
     * a method used to print all the borrowers inside the borrower list
     * implemented inside BorrowerServices
     * @param borrowers
     */
     void printAllBorrowers(List<Borrower> borrowers);

    /**
     * a method used to find a specific borrower based on his/her id
     * implemented inside BorrowerServices
     * @param borrowers
     * @param id
     * @return return a borrower if he/she exists
     * @throws ItemNotFoundException if the borrower doesn't exist
     */
     Borrower findBorrowerById(List<Borrower> borrowers, int id);

    /**
     * a method for checking if the borrower exists or not
     * implemented inside BorrowerServices
     * @param borrowers
     * @param id - represents the borrower id
     * @return - returns true or false based if it exists in the list or not
     */
     boolean borrowerExists(List<Borrower> borrowers, int id);

    /**
     * a method for checking how many borrowers exists in the borrowers list
     * implemented inside BorrowerServices
     * @param borrowers
     * @return - the size as long
     */
     int getBorrowerCount(List<Borrower> borrowers); //Fixed
}
