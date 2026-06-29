package com.exalt.library.models;

/**
 * a class representing a loan to which the borrower can borrow a book
 * it usees the books, borrowers
 * @author Mohammad Rimawi
 */
public class Loan {
    private final int id; // Defines the identity number for a loan
    // #TODO: a counter to automatically assigns an id for the book, ITS THE JOB OF THE DB
    private static int count = 1; // Defines the counter that we will increment #TODO: Update Later
    private Book book; // Represents the book that will be borrowed
    private Borrower borrower; // represents the borrower who is going to borrow a book
    private boolean isActive; // represents if the loan is active or not

    /**
     * A default constructor
     * automatically increments the counter for the id
     */
    public Loan() {
        this.id = generate(); // TODO: To assign the ID value from the DB
        isActive = true;
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

//    ==== SETTERS ====

    /**
     * a method for setting the book for the loan
     * @param book
     */
    public void setBook(Book book) {
        this.book = book;
    }

    /**
     * a method for setting the borrower for the loan
     * @param borrower
     */
    public void setBorrower(Borrower borrower) {
        this.borrower = borrower;
    }

    /**
     * a method for setting the activity of a loan
     * @param active
     */
    public void setActive(boolean active) {
        isActive = active;
    }

    //    ==== SETTERS ====

    /**
     * A synchronized generator so we get no duplicates
     * @return - an int representing the value of the current counter
     */
    public static synchronized int generate() {
        return count++;
    } //TODO: Delete this method when working with the DB, also, it violates SRP

    @Override
    public String toString() {
        return "Loan{" +
                "id=" + id + "\n" +
                ", book=" + book +
                ", borrower=" + borrower +
                '}' + "\n";
    }
}
