/**
 *
 */
package com.exalt.library;

import com.exalt.library.models.Book;
import com.exalt.library.models.Borrower;

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

    /**
     * A default constructor set to initialize an ArrayList for the books and borrowers
     */
    public Library() {
        this.books = new ArrayList<>();
        this.borrowers = new ArrayList<>();
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
     * @return - returns a Book if it exists otherwise null
     */
    public Book findBook(int id) {
        return books.stream()
                .filter(book -> book.getId() == id)
                .findFirst()
                .orElse(null);
    }

    /**
     * a method used to find a specific borrower based on his/her id
     * @param id - represents the borrower id
     * @return - return a borrower if he/she exists otherwise null
     */
    public Borrower findBorrower(int id) {
        return borrowers.stream()
                .filter(book -> book.getId() == id)
                .findFirst()
                .orElse(null);
    }

    /**
     * a method for checking if the book exists or not
     * @param id - represents the book id
     * @return - returns true or false based if it exists in the list or not
     */
    public boolean exists(int id) {
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
     * @return
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
                .noneMatch(book -> book.getTitle().equals(""));
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
     * a method that returns a borrower who borrowed books the most
     * @return
     */
    public Borrower topBorrower() {
        return borrowers.stream()
                .max(Comparator.comparingInt(Borrower::borrowedBooksIdSize))
                .orElse(null);
    }
    /**
     * a method that returns a borrower with the least borrows
     * @return
     */
    public Borrower leastBorrower() {
        return borrowers.stream()
                .min(Comparator.comparingInt(Borrower::borrowedBooksIdSize))
                .orElse(null);
    }

    /**
     * a method that returns the sum of all books id's
     * @return
     */
    public int sumOfAllBooksId() {
        return books.stream()
                .map(book -> book.getId())
                .reduce(0, Integer::sum);
    }
}
