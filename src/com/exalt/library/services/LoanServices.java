package com.exalt.library.services;

import com.exalt.library.services.operations.LibraryItemOperations;
import com.exalt.library.services.strategies.BorrowStrategy;
import com.exalt.library.services.factory.BorrowStrategyFactory;
import com.exalt.library.services.strategies.Reservable;
import com.exalt.library.exceptions.ItemUnavailableException;
import com.exalt.library.exceptions.ItemNotFoundException;
import com.exalt.library.exceptions.LoanNotFoundException;
import com.exalt.library.models.Borrower;
import com.exalt.library.models.libraryitems.LibraryItem;
import com.exalt.library.models.Loan;
import com.exalt.library.services.operations.BorrowerOperations;
import com.exalt.library.services.operations.LoanOperations;
import com.exalt.library.models.reservation.Reservation;

import java.util.Date;
import java.util.List;

/**
 * a class representing the services of the loan
 * it implements the loan operations
 * @author Mohammad Rimawi
 */
public class LoanServices implements LoanOperations {
    private LibraryItemOperations libraryItemOperations; // defines the item operation dependency
    private BorrowerOperations borrowerOperations; // defines the book operation dependency
    private BorrowStrategyFactory borrowStrategyFactory; // defines the borrow strategy factory
    private ReservationServices reservationServices; // defines the reservation services

    /**
     * a default constructor for instantiation
     */
    public LoanServices() { }

//    ==== SETTERS ====
    /**
     * a method for setting the book operations
     * @param libraryItemOperations
     */
    public void setLibraryItemOperations(LibraryItemOperations libraryItemOperations) {
        this.libraryItemOperations = libraryItemOperations;
    }

    /**
     * a method for setting the borrower operations
     * @param borrowerOperations
     */
    public void setBorrowerOperations(BorrowerOperations borrowerOperations) {
        this.borrowerOperations = borrowerOperations;
    }

    /**
     * a method for setting the borrower strategy factory
     * @param borrowStrategyFactory
     */
    public void setBorrowStrategyFactory(BorrowStrategyFactory borrowStrategyFactory) {
        this.borrowStrategyFactory = borrowStrategyFactory;
    }

    /**
     * a method for setting the reservation services
     * @param reservationServices
     */
    public void setReservationServices(ReservationServices reservationServices) {
        this.reservationServices = reservationServices;
    }
    //    ==== SETTERS ====

    /**
     * a method used to find a specific loan based on its id
     * @param loans
     * @param id - represents the loan id
     * @return - return a loan if it exists
     * @throws LoanNotFoundException if the loan wasn't found
     */
    @Override
    public Loan findLoanById(List<Loan> loans, int id) {
        return loans.stream() // this turns the loans into a stream
                .filter(loan -> loan.getId() == id)// This filters the stream and gets what matches the condition
                .findFirst() // This returns an optional describing the first element of the stream
                .orElseThrow(() -> new LoanNotFoundException("loan was not found!")); // This should throw an exception if there was loan found
    }

    /**
     * Finds an active loan matching the borrower and the books ids
     * @param loans
     * @param borrowerId
     * @param itemId
     * @return the active loan
     * @throws LoanNotFoundException if the loan doesn't exist
     */
    @Override
    public Loan findActiveLoan(List<Loan> loans, int borrowerId, int itemId) {
        return loans.stream()// this turns the loans into a stream
                .filter(loan -> (loan.isActive()) &&
                        (loan.getBorrower().getId() == borrowerId) &&
                        (loan.getLibraryItem().getId() == itemId)) // This filters the stream and gets what matches the conditions
                .findFirst() // This returns an optional describing the first element of the stream
                .orElseThrow(() -> new LoanNotFoundException("Loan doesn't exist")); // This should throw an exception if there was no loan found
    }

    /**
     * a method for checking if the library item exists and if it is available
     * @param items
     * @param itemId
     * @return a library item if found
     * @throws ItemNotFoundException
     * @throws ItemUnavailableException
     */
    @Override
    public LibraryItem checkForLibraryItem(List<LibraryItem> items, int itemId) {
        LibraryItem libraryItem = libraryItemOperations.findItemById(items, itemId);

        if(!libraryItem.isAvailable()) {
            throw new ItemUnavailableException("LibraryItem isn't available");
        }

        return libraryItem;
    }

    /**
     * a method for checking for the borrower if he exists or not
     * @param borrowers
     * @param borrowerId
     * @return borrower if found
     * @throws com.exalt.library.exceptions.BorrowerNotFoundException if not found
     */
    @Override
    public Borrower checkForBorrower(List<Borrower> borrowers, int borrowerId) {
        return borrowerOperations.findBorrowerById(borrowers, borrowerId);
    }

    /**
     * a method for letting a borrower to loan a libraryItem
     * @param loans
     * @param libraryItems
     * @param borrowers
     * @param borrowerId
     * @param itemId
     * @return a loam
     * @throws ItemUnavailableException if the libraryItem isn't available
     */
    @Override
    public Loan borrowLibraryItem(List<Loan> loans, List<LibraryItem> libraryItems, List<Borrower> borrowers, int borrowerId, int itemId) { //I'm keeping it
        LibraryItem libraryItem = checkForLibraryItem(libraryItems, itemId);
        Borrower borrower = checkForBorrower(borrowers, borrowerId);

        BorrowStrategy borrowStrategy = borrowStrategyFactory.resolve(libraryItem);
        return borrowStrategy.borrow(loans, libraryItem, borrower);
    }

    /**
     * a method to close a specific loan so the libraryItem becomes available again
     * @param loan
     * @param libraryItem
     * @param reservations
     */
    @Override
    public void closeLoan(Loan loan, LibraryItem libraryItem, List<Reservation> reservations) {
        loan.setActive(false);
        borrowStrategyFactory.resolve(libraryItem).returnItem(libraryItem);

        Reservation next = reservationServices.findNextWaitingReservation(reservations, libraryItem);
        if (next != null) {
            BorrowStrategy strategy = borrowStrategyFactory.resolve(libraryItem);
            if (strategy instanceof Reservable reservable) {
                reservable.holdItem(libraryItem);
            }
            next.setAvailableFrom(new Date());
        }
    }

    /**
     * a method which returns a borrowed libraryItem and closes its active loan
     * if he did - then close the loan
     * if he didn't - return false
     * @param loans
     * @param libraryItem
     * @param borrower
     * @param reservations
     * @return true if the loan was closed
     * @throws LoanNotFoundException if the loan is not found
     */
    @Override
    public boolean returnLibraryItem(List<Loan> loans, LibraryItem libraryItem, Borrower borrower, List<Reservation> reservations) {
        Loan loan = findActiveLoan(loans, borrower.getId(), libraryItem.getId()); //Find the active loan
        closeLoan(loan, libraryItem, reservations); // Close the current loan

        loans.remove(loan); //#TODO: Remember to delete this after adding a DB, so we can keep track of the loans history
        return true;
    }
}
