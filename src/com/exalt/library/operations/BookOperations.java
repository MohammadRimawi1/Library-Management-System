package com.exalt.library.operations;

import com.exalt.library.exceptions.BookNotFoundException;
import com.exalt.library.models.Book;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * a class representing the services for a book in a library
 * @author Mohammad Rimawi
 */
public class BookOperations {

    /**
     * A default constructor for instantiation
     */
    public BookOperations() { }

    /**
     * addBook method is used to add a book of type Book to the books list
     * @param books
     * @param book
     */
    public void addBook(List<Book> books, Book book) {
        books.add(book);
    }

    /**
     * a method used to print all the books inside the books list
     * @param books
     */
    public void printAllBooks(List<Book> books) {
        books.stream()
                .forEach(book -> System.out.println(book));
    }

    /**
     *  a method used to find a specific book based on its id
     * @param books
     * @param id
     * @return returns a Book if it exists
     * @throws BookNotFoundException if the book doesn't exist
     */
    public Book findBookById(List<Book> books, int id) {
        return books.stream()
                .filter(book -> book.getId() == id)
                .findFirst()
                .orElseThrow(() -> new BookNotFoundException("Book was not found!"));
    }

    /**
     * a method for checking if the book exists or not
     * @param books
     * @param id - represents the book id
     * @return - returns true or false based if it exists in the list or not
     */
    public boolean bookExists(List<Book> books, int id) {
        return books.stream()
                .anyMatch(book -> book.getId() == id);
    }

    /**
     * a method for checking all books if they have titles or not
     * @param books
     * @return - true if ALL books have titles, false if AT LEAST one doesn't have
     */
    public boolean allBooksHaveTitles(List<Book> books) {
        return books.stream()
                .noneMatch(book -> book.getTitle() == null || book.getTitle().equals(""));
    }

    /**
     * a method for sorting the books based on alphabetical ascending order
     * @param books
     * @return a list of sorted books
     */
    public List<Book> sortBooksByTitle(List<Book> books) {
        return books.stream()
                .sorted(Comparator.comparing(Book::getTitle))
                .collect(Collectors.toList());
    }

    /**
     * a method for checking if all the books are available or not
     * @param books
     * @return true if all are available, false if AT LEAST one isn't
     */
    public boolean areAllBookAvailable(List<Book> books) {
        return books.stream()
                .allMatch(book -> book.isAvailable());
    }

    /**
     * a method that returns the sum of all books id's
     * @param books
     * @return the sum of all IDs
     */
    public int sumOfAllBooksIds(List<Book> books) {
        return books.stream()
                .map(book -> book.getId())
                .reduce(0, Integer::sum);
    }

    /**
     * a method for checking how many books exists in the books list
     * @param books
     * @return - the size as long
     */
    public int listHowMuchBooksExists(List<Book> books) {
        return books.size();
    }

    /**
     * a method used to find a specific book based on its id
     * @param books
     * @param bookId - represents the book id
     * @return - return a book if it exists
     * @throws BookNotFoundException if the book wasn't found
     */
    public Book findBook(List<Book> books, int bookId) {
        return findBookById(books, bookId);
    }
}
