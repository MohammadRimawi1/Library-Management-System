/**
 *
 */
package com.exalt.library;

import com.exalt.library.models.Book;
import com.exalt.library.models.Borrower;
import com.exalt.library.models.Loan;

import java.util.List;

/**
 * a class representing the library itself
 * Contains a list of books and borrowers
 * @author Mohammad Rimawi
 */
public class Library {
    private List<Book> books; // Defines a list containing books
    private List<Borrower> borrowers; // Defines a list containing the borrowers
    private List<Loan> loans; // Defines a list containing the loans

    /**
     * A Default constructor
     */
    public Library() { }

//    ==== GETTERS ====
    /**
     * a method for getting the books
     * @return
     */
    public List<Book> getBooks() {
        return books;
    }

    /**
     * a method for returning the borrowers
     * @return
     */
    public List<Borrower> getBorrowers() {
        return borrowers;
    }

    /**
     * a method for returning all the loans
     * @return a list of loans
     */
    public List<Loan> getLoans() {
        return loans;
    }

//    ==== GETTERS ====

//    ==== SETTERS ====

    /**
     * a method for setting the books
     * @param books
     */
    public void setBooks(List<Book> books) {
        this.books = books;
    }

    /**
     * a method for setting the borrowers
     * @param borrowers
     */
    public void setBorrowers(List<Borrower> borrowers) {
        this.borrowers = borrowers;
    }

    /**
     * a method for setting the loans
     * @param loans
     */
    public void setLoans(List<Loan> loans) {
        this.loans = loans;
    }

//    ==== SETTERS ====
}
