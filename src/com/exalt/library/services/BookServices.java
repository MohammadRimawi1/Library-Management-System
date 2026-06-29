package com.exalt.library.services;

import com.exalt.library.exceptions.BookNotFoundException;
import com.exalt.library.models.Book;
import com.exalt.library.operations.BookOperations;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * a class representing the services of the book
 * it implements the book operations
 * @author Mohammad Rimawi
 */
public class BookServices implements BookOperations {
    /**
     * A default constructor for instantiation
     */
    public BookServices() { }

    /**
     * addBook method is used to add a book of type Book to the books list
     * @param books
     * @param book
     */
    public void addBook(List<Book> books, Book book) {
        books.add(book); //This adds a book to the books list
    }

    /**
     * a method used to print all the books inside the books list
     * @param books
     */
    public void printAllBooks(List<Book> books) {
        books.stream() // this turns the books into a stream
                .forEach(book -> System.out.println(book)); //terminal operation for performing an action on each element of the stream
    }

    /**
     *  a method used to find a specific book based on its id
     * @param books
     * @param id
     * @return returns a Book if it exists
     * @throws BookNotFoundException if the book doesn't exist
     */
    public Book findBookById(List<Book> books, int id) {
        return books.stream() // this turns the books into a stream
                .filter(book -> book.getId() == id)// This filters the stream and gets what matches the condition
                .findFirst() // This returns an optional describing the first element of the stream
                .orElseThrow(() -> new BookNotFoundException("Book was not found!")); // This should throw an exception if there was no book found
    }

    /**
     * a method for checking if the book exists or not
     * @param books
     * @param id - represents the book id
     * @return - returns true or false based if it exists in the list or not
     */
    public boolean bookExists(List<Book> books, int id) {
        return books.stream() // this turns the books into a stream
                .anyMatch(book -> book.getId() == id); //check if any element in the stream matches the condition
    }

    /**
     * a method for checking all books if they have titles or not
     * @param books
     * @return - true if ALL books have titles, false if AT LEAST one doesn't have
     */
    public boolean allBooksHaveTitles(List<Book> books) {
        return books.stream() // this turns the books into a stream
                .noneMatch(book -> book.getTitle() == null || book.getTitle().equals("")); //TODO: This logic isn't enough for some cases, fix it
    }

    /**
     * a method for sorting the books based on alphabetical ascending order
     * @param books
     * @return a list of sorted books
     */
    public List<Book> sortBooksByTitle(List<Book> books) {
        return books.stream() // this turns the books into a stream
                .sorted(Comparator.comparing(Book::getTitle)) //create a new sorted stream
                .collect(Collectors.toList()); //terminal operation to accumulate elements from a stream into a mutable container
    }

    /**
     * a method for checking if all the books are available or not
     * @param books
     * @return true if all are available, false if AT LEAST one isn't
     */
    public boolean areAllBookAvailable(List<Book> books) {
        return books.stream() // this turns the books into a stream
                .allMatch(book -> book.isAvailable()); //check if all elements in the stream matches the condition
    }

    /**
     * a method that returns the sum of all books id's
     * @param books
     * @return the sum of all IDs
     */
    public int sumOfAllBooksIds(List<Book> books) {
        return books.stream() // this turns the books into a stream
                .map(book -> book.getId()) //returns a stream after applying the function
                .reduce(0, Integer::sum);//combine all elements of a stream into a single result, then sum the id's
    } //TODO: check if there is actual point of this method?

    /**
     * a method for checking how many books exists in the books list
     * @param books
     * @return - the size as long
     */
    public int listHowMuchBooksExists(List<Book> books) {
        return books.size(); //Gets the size of the list
    } //TODO: This is basically the count inside your books list, fix the name for something better
}
