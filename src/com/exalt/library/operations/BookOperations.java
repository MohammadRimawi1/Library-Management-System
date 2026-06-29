package com.exalt.library.operations;

import com.exalt.library.exceptions.BookNotFoundException;
import com.exalt.library.models.Book;

import java.util.List;

/**
 * an interface representing the operations for a book in a library
 * implemented inside BookServices
 * @author Mohammad Rimawi
 */
public interface BookOperations {
    //Fixed
    /**
     * addBook method is used to add a book of type Book to the books list
     * implemented inside BookServices
     * @param books
     * @param book
     */
     void addBook(List<Book> books, Book book);

    /**
     * a method used to print all the books inside the books list
     * implemented inside BookServices
     * @param books
     */
     void printAllBooks(List<Book> books);

    /**
     *  a method used to find a specific book based on its id
     *  implemented inside BookServices
     * @param books
     * @param id
     * @return returns a Book if it exists
     * @throws BookNotFoundException if the book doesn't exist
     */
     Book findBookById(List<Book> books, int id);

    /**
     * a method for checking if the book exists or not
     * implemented inside BookServices
     * @param books
     * @param id - represents the book id
     * @return - returns true or false based if it exists in the list or not
     */
     boolean bookExists(List<Book> books, int id);

    /**
     * a method for checking all books if they have titles or not
     * implemented inside BookServices
     * @param books
     * @return - true if ALL books have titles, false if AT LEAST one doesn't have
     */
     boolean allBooksHaveTitles(List<Book> books);

    /**
     * a method for sorting the books based on alphabetical ascending order
     * implemented inside BookServices
     * @param books
     * @return a list of sorted books
     */
     List<Book> sortBooksByTitle(List<Book> books);

    /**
     * a method for checking if all the books are available or not
     * implemented inside BookServices
     * @param books
     * @return true if all are available, false if AT LEAST one isn't
     */
     boolean areAllBookAvailable(List<Book> books);

    /**
     * a method that returns the sum of all books id's
     * implemented inside BookServices
     * @param books
     * @return the sum of all IDs
     */
     int sumOfAllBooksIds(List<Book> books); //Fixed --> Keep it for now for the sake of using streams

    /**
     * a method for checking how many books exists in the books list
     * implemented inside BookServices
     * @param books
     * @return - the size as long
     */
     int getBookCount(List<Book> books); //Fixed
}
