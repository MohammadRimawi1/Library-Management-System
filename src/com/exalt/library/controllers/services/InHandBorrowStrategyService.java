package com.exalt.library.controllers.services;

import com.exalt.library.controllers.strategies.BorrowStrategy;
import com.exalt.library.models.Borrower;
import com.exalt.library.models.Loan;
import com.exalt.library.models.libraryitems.LibraryItem;
import com.exalt.library.models.libraryitems.physicalitems.PhysicalItem;

import java.util.List;

/**
 * Strategy borrowing in hand
 * @author Mohammad Rimawi
 */
public class InHandBorrowStrategyService implements BorrowStrategy {

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

        PhysicalItem physicalItem = (PhysicalItem) libraryItem;
        int remaining = physicalItem.getNumOfCopies() - 1;
        if (remaining < 0) {
            throw new IllegalStateException("No copies left to borrow");
        }
        physicalItem.setNumOfCopies(remaining);
        if (remaining == 0) {
            physicalItem.setAvailable(false);
        }

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
     * a method for returning the item
     * @param libraryItem
     */
    @Override
    public void returnItem(LibraryItem libraryItem) {
        PhysicalItem physicalItem = (PhysicalItem) libraryItem;
        physicalItem.setNumOfCopies(physicalItem.getNumOfCopies() + 1);
        physicalItem.setAvailable(true);
    }
}
