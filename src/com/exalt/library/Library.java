/**
 *
 */
package com.exalt.library;

import com.exalt.library.exceptions.BookNotFoundException;
import com.exalt.library.exceptions.BookUnavailableException;
import com.exalt.library.exceptions.BorrowerNotFoundException;
import com.exalt.library.exceptions.LoanNotFoundException;
import com.exalt.library.models.Book;
import com.exalt.library.models.Borrower;
import com.exalt.library.models.Loan;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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
     * A parameterized constructor that takes the books and the borrowers attributes
     * @param books
     * @param borrowers
     * @param loans
     */
    public Library(List<Book> books, List<Borrower> borrowers, List<Loan> loans) {
        this.books = books;
        this.borrowers = borrowers;
        this.loans = loans;
    }

    /**
     * addBook method is used to add a book of type Book to the books list
     * @param book
     */
    public void addBook(Book book) {
        books.add(book);
    }

    /**
     * assignBorrower method is used to assign a borrower of type Borrower to the borrowers list
     * @param borrower
     */
    public void assignBorrower(Borrower borrower) {
        borrowers.add(borrower);
    }

    /**
     * a method used to print all the books inside the books list
     */
    public void listAllBooks() {
        books.stream()
                .map(Book::toString)
                .forEach(System.out::println);
    }

    /**
     * a method used to find a specific book based on its id
     * @param id - represents the book id
     * @return - returns a Book if it exists
     * @throws BookNotFoundException if the book doesn't exist
     */
    public Book findBook(int id) {
        return books.stream()
                .filter(book -> book.getId() == id)
                .findFirst()
                .orElseThrow(() -> new BookNotFoundException("Book was not Found!"));
    }

    /**
     * a method used to find a specific borrower based on his/her id
     * @param id - represents the borrower id
     * @return - return a borrower if he/she exists
     * @throws BookNotFoundException if the borrower doesn't exist
     */
    public Borrower findBorrower(int id) {
        return borrowers.stream()
                .filter(borrower -> borrower.getId() == id)
                .findFirst()
                .orElseThrow(() -> new BorrowerNotFoundException("Borrower was not Found"));
    }

    /**
     * a method for letting a borrower to loan a book
     * @param borrowerId
     * @param bookId
     * @return a loam
     * @throws BookUnavailableException if the book isn't available
     */
    public Loan borrowBook(int borrowerId, int bookId) {
        Book book = findBook(bookId);

        Borrower borrower = findBorrower(borrowerId);

        book.setAvailable(false);
        Loan loan = new Loan(book, borrower);
        loans.add(loan);

        return loan;
    }

    /**
     * a method which returns a borrowed book and closes its active loan
     * if he did - then close the loan
     * if he didn't - return false
     * @param borrowerId
     * @param bookId
     * @return true if the loan was closed
     * @throws LoanNotFoundException if the loan is not found
     */
    public boolean returnBook(int borrowerId, int bookId) {
        return loans.stream()
                .filter(loan -> loan.isActive())
                .filter(loan -> loan.getBorrower().getId() == borrowerId)
                .filter(loan -> loan.getBook().getId() == bookId)
                .findFirst()
                .map(loan -> {
                    loan.closeLoan();
                    return true;
                })
                .orElseThrow(() -> new LoanNotFoundException("Loan doesn't exist"));
    }

    /**
     * a method for returning all the loans
     * @return a list of loans
     */
    public List<Loan> getLoans() {
        return loans;
    }

    /**
     * a method used to find a specific loan based on its id
     * @param loanId - represents the loan id
     * @return - return a loan if it exists
     * @throws LoanNotFoundException if the loan wasn't found
     */
    public Loan findLoan(int loanId) {
        return loans.stream()
                .filter(loan -> loan.getId() == loanId)
                .findFirst()
                .orElseThrow(() -> new LoanNotFoundException("Loan was not Found"));
    }

    /**
     * a method for checking if the book exists or not
     * @param id - represents the book id
     * @return - returns true or false based if it exists in the list or not
     */
    public boolean bookExists(int id) {
        return books.stream()
                .anyMatch(book -> book.getId() == id);
    }

    /**
     * a method for checking how many books exists in the books list
     * @return - the size as long
     */
    public int listHowMuchBooksExists() {
        return books.size();
    }
    /**
     * a method for checking how many borrowers exists in the borrowers list
     * @return - the size as long
     */
    public int listHowMuchBorrowersExists() {
        return borrowers.size();
    }

    /**
     * a method for sorting the books based on alphabetical ascending order
     * @return a list of sorted books
     */
    public List<Book> sortBooks() {
        return books.stream()
                .sorted(Comparator.comparing(Book::getTitle))
                .collect(Collectors.toList());
    }

    /**
     * a method for checking all books if they have titles or not
     * @return - true if ALL books have titles, false if AT LEAST one doesn't have
     */
    public boolean allBooksHaveTitles() {
        return books.stream()
                .noneMatch(book -> book.getTitle().equals("") && book.getTitle() == null);
    }

    /**
     * a method for checking if all the books are available or not
     * @return - true if all are available, false if AT LEAST one isn't
     */
    public boolean areALlBooksAvailable() {
        return books.stream()
                .allMatch(book -> book.isAvailable());
    }

    /**
     * a method that returns the sum of all books id's
     * @return the sum of all IDs
     */
    public int sumOfAllBooksId() {
        return books.stream()
                .map(book -> book.getId())
                .reduce(0, Integer::sum);
    }
}
