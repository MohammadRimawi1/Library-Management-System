package com.exalt.library.controllers.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

/**
 * a class representing the request for a reservation
 * @author Mohammad Rimawi
 */
public class ReserveRequest {
    @NotBlank(message = "Id is required")
    private String borrowerId; // defines The borrower ID
    @NotBlank(message = "Id is required")
    private String itemId; // defines the item ID

//    ==== GETTERS ====
    /**
     * a method for getting the borrower id
     * @return
     */
    public String getBorrowerId() { return borrowerId; }

    /**
     * a method for getting the item id
     * @return
     */
    public String getItemId() { return itemId; }
//    ==== GETTERS ====

//    ==== SETTERS ====

    /**
     * a method for setting the borrower id
     * @param borrowerId
     */
    public void setBorrowerId(String borrowerId) { this.borrowerId = borrowerId; }

    /**
     * a method for setting the item id
     * @param itemId
     */
    public void setItemId(String itemId) { this.itemId = itemId; }
//    ==== SETTERS ====
}