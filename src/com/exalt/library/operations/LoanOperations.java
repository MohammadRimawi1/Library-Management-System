package com.exalt.library.operations;

import com.exalt.library.exceptions.BookUnavailableException;
import com.exalt.library.exceptions.LoanNotFoundException;
import com.exalt.library.models.Book;
import com.exalt.library.models.Borrower;
import com.exalt.library.models.Loan;

import java.util.List;

/**
 * a class representing the services for a loan in a library
 * @author Mohammad Rimawi
 */
public class LoanOperations {
    private final BookOperations bookOperations = new BookOperations(); // Provides book operations.
    private final BorrowerOperations borrowerOperations = new BorrowerOperations(); // Provides borrower operations

    /**
     * A default constructor for instantiation
     */
    public LoanOperations() { }

    /**
     * a method used to find a specific loan based on its id
     * @param loans
     * @param id - represents the loan id
     * @return - return a loan if it exists
     * @throws LoanNotFoundException if the loan wasn't found
     */
    public Loan findLoanById(List<Loan> loans, int id) {
        return loans.stream()
                .filter(loan -> loan.getId() == id)
                .findFirst()
                .orElseThrow(() -> new LoanNotFoundException("loan was not found!"));
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
        return loans.stream()
                .filter(loan -> (loan.isActive()) &&
                        (loan.getBorrower().getId() == borrowerId) &&
                        (loan.getBook().getId() == bookId))
                .findFirst()
                .orElseThrow(() -> new LoanNotFoundException("Loan doesn't exist"));
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
    public Loan borrowBook(List<Loan> loans, List<Book> books, List<Borrower> borrowers, int borrowerId, int bookId) {
        Book book = bookOperations.findBookById(books, bookId);

        if(book.isAvailable() == false) {
            throw new BookUnavailableException("Book isn't available");
        }

        Borrower borrower = borrowerOperations.findBorrowerById(borrowers, borrowerId);


        Loan loan = new Loan();
        loan.setBook(book);
        loan.setBorrower(borrower);
        loans.add(loan);
        book.setAvailable(false);

        return loan;
    }

    /**
     * a method which returns a borrowed book and closes its active loan
     * if he did - then close the loan
     * if he didn't - return false
     * @param loans
     * @param borrowerId
     * @param bookId
     * @return true if the loan was closed
     * @throws LoanNotFoundException if the loan is not found
     */
    public boolean returnBook(List<Loan> loans, int borrowerId, int bookId) {
        Loan loan = findActiveLoan(loans, borrowerId, bookId);
        loan.closeLoan();

        loans.remove(loan);
        return true;
    }

    /**
     * a method used to find a specific loan based on its id
     * @param loans
     * @param loanId - represents the loan id
     * @return - return a loan if it exists
     * @throws LoanNotFoundException if the loan wasn't found
     */
    public Loan findLoan(List<Loan> loans, int loanId) {
        return findLoanById(loans, loanId);
    }
}
