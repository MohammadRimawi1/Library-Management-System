package com.exalt.library.models;

/**
 * a class representing a loan to which the borrower can borrow a book
 * it usees the books, borrowers
 * @author Mohammad Rimawi
 */
public class Loan {
    private final int id; // Defines the identity number for a loan
    private static int count = 1; // a counter to automatically assigns an id for the loan
    private Book book; // Represents the book that will be borrowed
    private Borrower borrower; // represents the borrower who is going to borrow a book
    private boolean isActive; // represents if the loan is active or not

    /**
     * A parameterized constructor that takes a book and a borrower attributes
     * automatically increments the counter for the id
     * @param book
     * @param borrower
     */
    public Loan(Book book, Borrower borrower) {
        this.id = count;
        this.book = book;
        this.borrower = borrower;
        isActive = true;
        count++;
    }

//    ==== GETTERS ====
    /**
     * a method for getting the id value
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * a method for getting the book
     * @return the book
     */
    public Book getBook() {
        return book;
    }

    /**
     * a method for getting the borrower
     * @return the borrower
     */
    public Borrower getBorrower() {
        return borrower;
    }

    /**
     * a method for getting if the loan is active or not
     * @return if they are active or not
     */
    public boolean isActive() {
        return isActive;
    }

    //    ==== GETTERS ====

    /**
     * a method to close a specific loan so the book becomes available again
     */
    public void closeLoan() {
        isActive = false;
        book.setAvailable(true);
    }

    @Override
    public String toString() {
        return "Loan{" +
                "id=" + id +
                ", book=" + book +
                ", borrower=" + borrower +
                '}';
    }
}
