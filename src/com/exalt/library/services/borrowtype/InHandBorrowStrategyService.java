package com.exalt.library.services.borrowtype;

import com.exalt.library.services.strategies.BorrowStrategy;
import com.exalt.library.services.strategies.Reservable;
import com.exalt.library.models.Borrower;
import com.exalt.library.models.Loan;
import com.exalt.library.models.libraryitems.LibraryItem;
import com.exalt.library.models.libraryitems.physicalitems.PhysicalItem;

import java.util.List;

/**
 * Strategy borrowing in hand
 * implements both BorrowStrategy and Reservable interfaces
 * @author Mohammad Rimawi
 */
public class InHandBorrowStrategyService implements BorrowStrategy, Reservable {

    /**
     * a method for reducing the available copies of a physical item by one,
     * and flipping availability off once none remain.
     * @param libraryItem
     */
    private void decrementCopy(LibraryItem libraryItem) {
        PhysicalItem physicalItem = (PhysicalItem) libraryItem;
        int remaining = physicalItem.getNumOfCopies() - 1;
        if (remaining < 0) {
            throw new IllegalStateException("No copies left to borrow");
        }
        physicalItem.setNumOfCopies(remaining);
        if (remaining == 0) {
            physicalItem.setAvailable(false);
        }
    }

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

        decrementCopy(libraryItem);

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

    /**
     * a method for holding a specific item and decrementing the num of copies for it
     * @param item
     */
    @Override
    public void holdItem(LibraryItem item) {
        decrementCopy(item);
    }
}
