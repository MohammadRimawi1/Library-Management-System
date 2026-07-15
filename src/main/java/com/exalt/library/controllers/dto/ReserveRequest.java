package com.exalt.library.controllers.dto;

import jakarta.validation.constraints.Min;

/**
 * a class representing the request for a reservation
 * @author Mohammad Rimawi
 */
public class ReserveRequest {
    @Min(value = 1, message = "Must be a valid ID!")
    private int borrowerId; // defines The borrower ID
    @Min(value = 1, message = "Must be a valid ID!")
    private int itemId; // defines the item ID

//    ==== GETTERS ====
    /**
     * a method for getting the borrower id
     * @return
     */
    public int getBorrowerId() { return borrowerId; }

    /**
     * a method for getting the item id
     * @return
     */
    public int getItemId() { return itemId; }
//    ==== GETTERS ====

//    ==== SETTERS ====

    /**
     * a method for setting the borrower id
     * @param borrowerId
     */
    public void setBorrowerId(int borrowerId) { this.borrowerId = borrowerId; }

    /**
     * a method for setting the item id
     * @param itemId
     */
    public void setItemId(int itemId) { this.itemId = itemId; }
//    ==== SETTERS ====
}