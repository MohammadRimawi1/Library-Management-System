package com.exalt.library.models.reservation;

/**
 * represents the current state of a reservation
 * @author Mohammad Rimawi
 */
public enum ReservationStatus {
    WAITING, // Means the item isn't available yet
    READY, // Means the item is ready to be reserved
    FULFILLED, // Means the item was reserved
    EXPIRED, // Means the item reservation is expired
    CANCELLED // Means the item reservation is cancelled
}
