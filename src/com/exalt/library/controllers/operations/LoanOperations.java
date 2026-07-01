package com.exalt.library.controllers.operations;

import com.exalt.library.exceptions.BookUnavailableException;
import com.exalt.library.exceptions.LoanNotFoundException;
import com.exalt.library.models.Book;
import com.exalt.library.models.Borrower;
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
     * Finds an active loan matching the borrower and the books ids
     * implemented inside LoanServices
     * @param loans
     * @param borrowerId
     * @param bookId
     * @return the active loan
     * @throws LoanNotFoundException if the loan doesn't exist
     */
     Loan findActiveLoan(List<Loan> loans, int borrowerId, int bookId);

    /**
     * a method for checking if the book exists and if it is available
     * implemented inside LoanServices
     * @param books
     * @param bookId
     * @return a book if found
     * @throws com.exalt.library.exceptions.BookNotFoundException
     * @throws BookUnavailableException
     */
     Book checkForBook(List<Book> books, int bookId);

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
     * @param book
     * @param borrower
     * @return the created loan
     */
     Loan createLoan(List<Loan> loans, Book book, Borrower borrower);

    /**
     * a method for letting a borrower to loan a book
     * implemented inside LoanServices
     * @param loans
     * @param books
     * @param borrowers
     * @param borrowerId
     * @param bookId
     * @return a loam
     * @throws BookUnavailableException if the book isn't available
     */
     Loan borrowBook(List<Loan> loans, List<Book> books, List<Borrower> borrowers, int borrowerId, int bookId); //I'm keeping it

    /**
     * a method to close a specific loan so the book becomes available again
     */
     void closeLoan(Loan loan, Book book);

    /**
     * a method which returns a borrowed book and closes its active loan
     * if he did - then close the loan
     * if he didn't - return false
     * implemented inside LoanServices
     * @param loans
     * @param book
     * @param borrower
     * @return true if the loan was closed
     * @throws LoanNotFoundException if the loan is not found
     */
     boolean returnBook(List<Loan> loans, Book book, Borrower borrower);
}
