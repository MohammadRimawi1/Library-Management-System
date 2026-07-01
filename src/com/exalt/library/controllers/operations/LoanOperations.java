package com.exalt.library.controllers.operations;

import com.exalt.library.exceptions.ItemUnavailableException;
import com.exalt.library.exceptions.ItemNotFoundException;
import com.exalt.library.exceptions.LoanNotFoundException;
import com.exalt.library.models.Borrower;
import com.exalt.library.models.libraryitems.LibraryItem;
import com.exalt.library.models.Loan;

import java.util.List;

/**
 * an interface representing the operations for a loan in a library
 * @author Mohammad Rimawi
 */
public interface LoanOperations {
    //Fixed
    /**
     * a method used to find a specific loan based on its id
     * implemented inside LoanServices
     * @param loans
     * @param id - represents the loan id
     * @return - return a loan if it exists
     * @throws LoanNotFoundException if the loan wasn't found
     */
     Loan findLoanById(List<Loan> loans, int id);

    /**
     * Finds an active loan matching the borrower and the library items ids
     * implemented inside LoanServices
     * @param loans
     * @param borrowerId
     * @param itemId
     * @return the active loan
     * @throws LoanNotFoundException if the loan doesn't exist
     */
     Loan findActiveLoan(List<Loan> loans, int borrowerId, int itemId);

    /**
     * a method for checking if the item exists and if it is available
     * implemented inside LoanServices
     * @param items
     * @param itemId
     * @return an item if found
     * @throws ItemNotFoundException
     * @throws ItemUnavailableException
     */
     LibraryItem checkForLibraryItem(List<LibraryItem> items, int itemId);

    /**
     * a method for checking for the borrower if he exists or not
     * implemented inside LoanServices
     * @param borrowers
     * @param borrowerId
     * @return borrower if found
     * @throws com.exalt.library.exceptions.BorrowerNotFoundException if not found
     */
     Borrower checkForBorrower(List<Borrower> borrowers, int borrowerId);

    /**
     * a method for creating a new loan object, and set its attributes.
     * implemented inside LoanServices
     * @param loans
     * @param item
     * @param borrower
     * @return the created loan
     */
     Loan createLoan(List<Loan> loans, LibraryItem item, Borrower borrower);

    /**
     * a method for letting a borrower to loan an item
     * implemented inside LoanServices
     * @param loans
     * @param items
     * @param borrowers
     * @param borrowerId
     * @param itemId
     * @return a loam
     * @throws ItemUnavailableException if the item isn't available
     */
     Loan borrowLibraryItem(List<Loan> loans, List<LibraryItem> items, List<Borrower> borrowers, int borrowerId, int itemId); //I'm keeping it

    /**
     * a method to close a specific loan so the item becomes available again
     */
     void closeLoan(Loan loan, LibraryItem item);

    /**
     * a method which returns a borrowed item and closes its active loan
     * if he did - then close the loan
     * if he didn't - return false
     * implemented inside LoanServices
     * @param loans
     * @param item
     * @param borrower
     * @return true if the loan was closed
     * @throws LoanNotFoundException if the loan is not found
     */
     boolean returnLibraryItem(List<Loan> loans, LibraryItem item, Borrower borrower);
}
