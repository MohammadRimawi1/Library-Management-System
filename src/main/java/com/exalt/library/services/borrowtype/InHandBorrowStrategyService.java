package com.exalt.library.services.borrowtype;

import com.exalt.library.models.reservation.Reservation;
import com.exalt.library.models.reservation.ReservationStatus;
import com.exalt.library.repositories.LibraryItemRepository;
import com.exalt.library.services.strategies.BorrowStrategy;
import com.exalt.library.services.strategies.Reservable;
import com.exalt.library.models.libraryitems.LibraryItem;
import com.exalt.library.models.libraryitems.physicalitems.PhysicalItem;
import org.springframework.stereotype.Component;

/**
 * Strategy borrowing in hand
 * implements both BorrowStrategy and Reservable interfaces
 * @author Mohammad Rimawi
 */
@Component
public class InHandBorrowStrategyService implements BorrowStrategy, Reservable {
    private final LibraryItemRepository libraryItemRepository; // Defines the library item repository

    /**
     * Constructor injection
     * @param libraryItemRepository
     */
    public InHandBorrowStrategyService(LibraryItemRepository libraryItemRepository) {
        this.libraryItemRepository = libraryItemRepository;
    }

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
        libraryItemRepository.save(physicalItem);
    }

    /**
     * a method for activating an existing reservation - marks it active
     * and decrements the physical copy count
     * @param reservation
     * @return the activated reservation
     */
    @Override
    public Reservation activate(Reservation reservation) {
        LibraryItem item = reservation.getLibraryItem();

        if (item.isAvailable()) {
            decrementCopy(item);
        }

        reservation.setStatus(ReservationStatus.ACTIVE);

        return reservation;
    }


    /**
     * The borrowing strategy for a type of borrowing
     * @param reservation
     * @return the activated reservation
     */
    @Override
    public Reservation borrow(Reservation reservation) {
        return activate(reservation);
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
