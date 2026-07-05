package com.exalt.library.controllers.services.borrowtype;

import com.exalt.library.controllers.strategies.BorrowStrategy;
import com.exalt.library.models.Borrower;
import com.exalt.library.models.Loan;
import com.exalt.library.models.libraryitems.LibraryItem;

import java.util.List;

/**
 * strategy borrowing online
 * @author Mohammad Rimawi
 */
public class OnlineBorrowStrategyService implements BorrowStrategy {
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

    /**
     * a method for returning an item
     * @param libraryItem
     */
    @Override
    public void returnItem(LibraryItem libraryItem) {
        // Always available
    }
}
