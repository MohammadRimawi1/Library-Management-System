package com.exalt.library.controllers.dto;

/**
 * a class representing the request for a reservation
 * @author Mohammad Rimawi
 */
public class ReserveRequest {
    private int borrowerId; // defines The borrower ID
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