package com.exalt.library.controllers.strategies;

import com.exalt.library.models.Borrower;
import com.exalt.library.models.Loan;
import com.exalt.library.models.libraryitems.LibraryItem;

import java.util.List;

/**
 * Strategy borrowing in hand
 * @author Mohammad Rimawi
 */
public class InHandBorrowStrategy implements BorrowStrategy {

    /**
     * a method for creating a new loan object, and set its attributes.
     * @param loans
     * @param libraryItem
     * @param borrower
     * @return the created loan
     */
    public Loan createLoan(List<Loan> loans, LibraryItem libraryItem, Borrower borrower) {
        Loan loan = new Loan(); //Create a loan
        loan.setLibraryItem(libraryItem); //Set the libraryItem
        loan.setBorrower(borrower); // Set the borrower
        loans.add(loan); // add the loan to the loans list
        libraryItem.setAvailable(false); // Change the libraryItem availibity - its taken now

        return loan;
    }

    /**
     * The borrowing strategy for a type of borrowing
     * @param loans
     * @param libraryItem
     * @param borrower
     * @return created loan
     */
    @Override
    public Loan borrow(List<Loan> loans, LibraryItem libraryItem, Borrower borrower) {
        Loan loan = createLoan(loans, libraryItem, borrower);

        return loan;
    }
}
