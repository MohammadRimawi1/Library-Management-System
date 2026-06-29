package com.exalt.library.services;

import com.exalt.library.exceptions.BookUnavailableException;
import com.exalt.library.exceptions.LoanNotFoundException;
import com.exalt.library.models.Book;
import com.exalt.library.models.Borrower;
import com.exalt.library.models.Loan;
import com.exalt.library.operations.BookOperations;
import com.exalt.library.operations.BorrowerOperations;
import com.exalt.library.operations.LoanOperations;

import java.util.List;

/**
 * a class representing the services of the loan
 * it implements the loan operations
 * @author Mohammad Rimawi
 */
public class LoanServices implements LoanOperations {
    // TODO: What are these attributes for?? Do some documentation
    private BookOperations bookOperations;
    private BorrowerOperations borrowerOperations;

    /**
     * a default constructor for instantiation
     */
    public LoanServices() { }

//    ==== SETTERS ====

    /**
     * a method for setting the book operations
     * @param bookOperations
     */
    public void setBookOperations(BookOperations bookOperations) {
        this.bookOperations = bookOperations;
    }

    /**
     * a method for setting the borrower operations
     * @param borrowerOperations
     */
    public void setBorrowerOperations(BorrowerOperations borrowerOperations) {
        this.borrowerOperations = borrowerOperations;
    }
//    ==== SETTERS ====

    /**
     * a method used to find a specific loan based on its id
     * @param loans
     * @param id - represents the loan id
     * @return - return a loan if it exists
     * @throws LoanNotFoundException if the loan wasn't found
     */
    public Loan findLoanById(List<Loan> loans, int id) {
        return loans.stream() // this turns the loans into a stream
                .filter(loan -> loan.getId() == id)// This filters the stream and gets what matches the condition
                .findFirst() // This returns an optional describing the first element of the stream
                .orElseThrow(() -> new LoanNotFoundException("loan was not found!")); // This should throw an exception if there was loan found
    }

    /**
     * Finds an active loan matching the borrower and the books ids
     * @param loans
     * @param borrowerId
     * @param bookId
     * @return the active loan
     * @throws LoanNotFoundException if the loan doesn't exist
     */
    public Loan findActiveLoan(List<Loan> loans, int borrowerId, int bookId) {
        return loans.stream()// this turns the loans into a stream
                .filter(loan -> (loan.isActive()) &&
                        (loan.getBorrower().getId() == borrowerId) &&
                        (loan.getBook().getId() == bookId)) // This filters the stream and gets what matches the conditions
                .findFirst() // This returns an optional describing the first element of the stream
                .orElseThrow(() -> new LoanNotFoundException("Loan doesn't exist")); // This should throw an exception if there was no loan found
    }

    /**
     * a method for letting a borrower to loan a book
     * @param loans
     * @param books
     * @param borrowers
     * @param borrowerId
     * @param bookId
     * @return a loam
     * @throws BookUnavailableException if the book isn't available
     */
    public Loan borrowBook(List<Loan> loans, List<Book> books, List<Borrower> borrowers, int borrowerId, int bookId) { //TODO: Check if there is a way to reduce the amount of the parameters
        //TODO: This method is doing too much, split it into 2 methods
        Book book = bookOperations.findBookById(books, bookId);

        if(book.isAvailable() == false) { //TODO: update the condition and use the modern way
            throw new BookUnavailableException("Book isn't available");
        }

        Borrower borrower = borrowerOperations.findBorrowerById(borrowers, borrowerId);


        Loan loan = new Loan(); //Create a loan
        loan.setBook(book); //Set the book
        loan.setBorrower(borrower); // Set the borrower
        loans.add(loan); // add the loan to the loans list
        book.setAvailable(false); // Change the book availibity - its taken now

        return loan;
    }

    /**
     * a method to close a specific loan so the book becomes available again
     */
    public void closeLoan(Loan loan, Book book) {
        loan.setActive(false);
        book.setAvailable(true);
    }

    /**
     * a method which returns a borrowed book and closes its active loan
     * if he did - then close the loan
     * if he didn't - return false
     * @param loans
     * @param book
     * @param borrower
     * @return true if the loan was closed
     * @throws LoanNotFoundException if the loan is not found
     */
    public boolean returnBook(List<Loan> loans, Book book, Borrower borrower) {
        Loan loan = findActiveLoan(loans, borrower.getId(), book.getId()); //Find the active loan
        closeLoan(loan, book); // Close the current loan

        loans.remove(loan); //#TODO: Remember to delete this after adding a DB, so we can keep track of the loans history
        return true;
    }
}
