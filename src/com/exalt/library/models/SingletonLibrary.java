package com.exalt.library.models;

import java.util.List;

/**
 * a class representing the library itself
 * Contains a list of books, borrowers, and loans
 * @author Mohammad Rimawi
 */
public class SingletonLibrary {
    private static volatile SingletonLibrary instance; // Defines the single shared instance of the library
    private List<Book> books; // Defines a list containing books
    private List<Borrower> borrowers; // Defines a list containing the borrowers
    private List<Loan> loans; // Defines a list containing the loans

    /**
     * A Default constructor
     */
    private SingletonLibrary() { }

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

    /**
     * a method for getting the instance of the library
     * if there is an existing one, then you can't create another following the singleton design pattern principle
     * it is multi-thread safe
     * @return a single instance from the SingletonLibrary
     */
    public static SingletonLibrary getInstance() {
        SingletonLibrary result = instance;
        if(result == null) {
            synchronized (SingletonLibrary.class) {
                result = instance;
                if (result == null) {
                    instance = result = new SingletonLibrary();
                }
            }
        }
        return result;
    }
}
