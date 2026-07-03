package com.exalt.library.models.reservation;

import com.exalt.library.models.Borrower;
import com.exalt.library.models.libraryitems.LibraryItem;

import java.util.Date;

/**
 * a class representing the reservation for an item
 * it defines who is the borrower going to reserve and what item will he/she reserve
 * it keeps track of the reservation life date
 * @author Mohammad Rimawi
 */
public class Reservation {
    private final int id; // Represents the id for an author
    // #TODO: a counter to automatically assigns an id for the book, ITS THE JOB OF THE DB
    private static int count = 1; // Defines the counter that we will increment #TODO: Update Later
    private LibraryItem libraryItem; // Defines the item that will be reserved by the borrower
    private Borrower borrower; // Defines the borrower that will reserve a specific item
    private Date startDate; // Defines the start time of the reservation
    private Date availableFrom; // Defines if the item is available so we can count the time of the reservation
    private Date endDate; // Defines when the reservation date ends
    private ReservationStatus status; // Defines the status of the reservation

    /**
     * A default constructor
     * automatically increments the counter for the id
     */
    public Reservation() {
        this.id = generate(); // TODO: To assign the ID value from the DB
        this.status = ReservationStatus.WAITING;
    }

//    ==== GETTERS ====
    /**
     * a method for getting the library item that will be reserved
     * @return
     */
    public LibraryItem getLibraryItem() {
        return libraryItem;
    }

    /**
     * a method for getting the borrower who wants to reserve an item
     * @return
     */
    public Borrower getBorrower() {
        return borrower;
    }

    /**
     * a method for getting the date in which the reservation started
     * @return
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * a method for getting the date in which the item is currently available
     * if its available, then the user's reservation has a specific time before it ends
     * @return
     */
    public Date getAvailableFrom() {
        return availableFrom;
    }

    /**
     * a method for getting the date in which the reservation ended
     * @return
     */
    public Date getEndDate() {
        return endDate;
    }
//    ==== GETTERS ====

//    ==== SETTERS ====
    /**
     * a method for setting the item that will be reserved
     * @param libraryItem
     */
    public void setLibraryItem(LibraryItem libraryItem) {
        this.libraryItem = libraryItem;
    }

    /**
     * a method for setting the borrower who wants to reserve a specific item
     * @param borrower
     */
    public void setBorrower(Borrower borrower) {
        this.borrower = borrower;
    }

    /**
     * a method for setting the date when the reservation start
     * @param startDate
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * a method for setting the date when the item is available for the reservation life-time to start
     * @param availableFrom
     */
    public void setAvailableFrom(Date availableFrom) {
        this.availableFrom = availableFrom;
        this.endDate = new Date(getAvailableFrom().getTime() + 3L * 24 * 60 * 60 * 1000); // 3 days
        this.status = ReservationStatus.READY;
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
        return "Reservation{" +
                "id=" + id +
                ", libraryItem=" + libraryItem +
                ", borrower=" + borrower +
                ", startDate=" + startDate +
                ", availableFrom=" + availableFrom +
                ", endDate=" + endDate +
                '}';
    }
}
