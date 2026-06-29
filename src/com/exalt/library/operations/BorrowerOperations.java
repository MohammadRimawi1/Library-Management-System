package com.exalt.library.operations;

import com.exalt.library.exceptions.BookNotFoundException;
import com.exalt.library.exceptions.BorrowerNotFoundException;
import com.exalt.library.models.Borrower;

import java.util.List;

/**
 * an interface representing the operations for a borrower in a library
 * @author Mohammad Rimawi
 */
public interface BorrowerOperations {

    /**
     * assignBorrower method is used to assign a borrower of type Borrower to the borrowers list
     * implemented inside BorrowerServices
     * @param borrowers
     * @param borrower
     */
    public void assignBorrower(List<Borrower> borrowers, Borrower borrower);

    /**
     * a method used to print all the borrowers inside the borrower list
     * implemented inside BorrowerServices
     * @param borrowers
     */
    public void printAllBorrowers(List<Borrower> borrowers);

    /**
     * a method used to find a specific borrower based on his/her id
     * implemented inside BorrowerServices
     * @param borrowers
     * @param id
     * @return return a borrower if he/she exists
     * @throws BookNotFoundException if the borrower doesn't exist
     */
    public Borrower findBorrowerById(List<Borrower> borrowers, int id);

    /**
     * a method for checking if the borrower exists or not
     * implemented inside BorrowerServices
     * @param borrowers
     * @param id - represents the borrower id
     * @return - returns true or false based if it exists in the list or not
     */
    public boolean borrowerExists(List<Borrower> borrowers, int id);

    /**
     * a method for checking how many borrowers exists in the borrowers list
     * implemented inside BorrowerServices
     * @param borrowers
     * @return - the size as long
     */
    public int listHowMuchBorrowersExists(List<Borrower> borrowers);
}
