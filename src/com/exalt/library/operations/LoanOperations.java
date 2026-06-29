package com.exalt.library.operations;

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

    /**
     * a method used to find a specific loan based on its id
     * implemented inside LoanServices
     * @param loans
     * @param id - represents the loan id
     * @return - return a loan if it exists
     * @throws LoanNotFoundException if the loan wasn't found
     */
    public Loan findLoanById(List<Loan> loans, int id);

    /**
     * Finds an active loan matching the borrower and the books ids
     * implemented inside LoanServices
     * @param loans
     * @param borrowerId
     * @param bookId
     * @return the active loan
     * @throws LoanNotFoundException if the loan doesn't exist
     */
    public Loan findActiveLoan(List<Loan> loans, int borrowerId, int bookId);

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
    public Loan borrowBook(List<Loan> loans, List<Book> books, List<Borrower> borrowers, int borrowerId, int bookId);

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
    public boolean returnBook(List<Loan> loans, Book book, Borrower borrower);
}
