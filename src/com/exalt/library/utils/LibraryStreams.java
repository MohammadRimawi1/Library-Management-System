package com.exalt.library.utils;

import com.exalt.library.exceptions.BookNotFoundException;
import com.exalt.library.exceptions.BorrowerNotFoundException;
import com.exalt.library.exceptions.LoanNotFoundException;
import com.exalt.library.models.Book;
import com.exalt.library.models.Borrower;
import com.exalt.library.models.Loan;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * A utility class that contains all the streams for the library class
 * @author Mohammad Rimawi
 */
public class LibraryStreams {

    /**
     * A private constructor to prevent instantiation
     */
    private LibraryStreams() { }

    /**
     *  a method used to find a specific book based on its id
     * @param books
     * @param id
     * @return returns a Book if it exists
     * @throws BookNotFoundException if the book doesn't exist
     */
    public static Book findBookById(List<Book> books, int id) {
        return books.stream()
                .filter(book -> book.getId() == id)
                .findFirst()
                .orElseThrow(() -> new BookNotFoundException("Book was not found!"));
    }

    /**
     * a method used to find a specific borrower based on his/her id
     * @param borrowers
     * @param id
     * @return return a borrower if he/she exists
     * @throws BookNotFoundException if the borrower doesn't exist
     */
    public static Borrower findBorrowerById(List<Borrower> borrowers, int id) {
        return borrowers.stream()
                .filter(borrower -> borrower.getId() == id)
                .findFirst()
                .orElseThrow(() -> new BorrowerNotFoundException("Borrower was not found!"));
    }

    /**
     * a method used to find a specific loan based on its id
     * @param loans
     * @param id - represents the loan id
     * @return - return a loan if it exists
     * @throws LoanNotFoundException if the loan wasn't found
     */
    public static Loan findLoanById(List<Loan> loans, int id) {
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
    public static Loan findActiveLoan(List<Loan> loans, int borrowerId, int bookId) {
        return loans.stream()
                .filter(loan -> (loan.isActive()) &&
                        (loan.getBorrower().getId() == borrowerId) &&
                        (loan.getBook().getId() == bookId))
                .findFirst()
                .orElseThrow(() -> new LoanNotFoundException("Loan doesn't exist"));
    }

    /**
     * a method for checking if the book exists or not
     * @param books
     * @param id - represents the book id
     * @return - returns true or false based if it exists in the list or not
     */
    public static boolean bookExists(List<Book> books, int id) {
        return books.stream()
                .anyMatch(book -> book.getId() == id);
    }

    /**
     * a method for sorting the books based on alphabetical ascending order
     * @param books
     * @return a list of sorted books
     */
    public static List<Book> sortBooksByTitle(List<Book> books) {
        return books.stream()
                .sorted(Comparator.comparing(Book::getTitle))
                .collect(Collectors.toList());
    }

    /**
     * a method for checking all books if they have titles or not
     * @param books
     * @return - true if ALL books have titles, false if AT LEAST one doesn't have
     */
    public static boolean allBooksHaveTitles(List<Book> books) {
        return books.stream()
                .noneMatch(book -> book.getTitle() == null || book.getTitle().equals(""));
    }

    /**
     * a method for checking if all the books are available or not
     * @param books
     * @return true if all are available, false if AT LEAST one isn't
     */
    public static boolean areAllBookAvailable(List<Book> books) {
        return books.stream()
                .allMatch(book -> book.isAvailable());
    }

    /**
     * a method that returns the sum of all books id's
     * @param books
     * @return the sum of all IDs
     */
    public static int sumOfAllBooksIds(List<Book> books) {
        return books.stream()
                .map(book -> book.getId())
                .reduce(0, Integer::sum);
    }

    /**
     * a method used to print all the books inside the books list
     * @param books
     */
    public static void prinAllBooks(List<Book> books) {
        books.stream()
                .forEach(book -> System.out.println(book));
    }
}