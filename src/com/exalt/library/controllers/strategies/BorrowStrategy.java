package com.exalt.library.controllers.strategies;

import com.exalt.library.models.Borrower;
import com.exalt.library.models.Loan;
import com.exalt.library.models.libraryitems.LibraryItem;

import java.util.List;

/**
 * a strategy interface representing the different ways library items can be borrowed
 * @author Mohammad Rimawi
 */
public interface BorrowStrategy {

    /**
     * a method for creating a new loan object, and set its attributes.
     * Implemented in c
     * @param loans
     * @param libraryItem
     * @param borrower
     * @return the created loan
     */
    Loan createLoan(List<Loan> loans, LibraryItem libraryItem, Borrower borrower);

    /**
     * The borrowing strategy for a type of borrowing
     * Implemented in The borrowing strategy for a type of borrowing
     * @param loans
     * @param libraryItem
     * @param borrower
     * @return created loan
     */
    Loan borrow(List<Loan> loans, LibraryItem libraryItem, Borrower borrower);
}
