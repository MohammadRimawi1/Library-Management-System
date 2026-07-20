package com.exalt.library.dto;

/**
 * A record representing the Data Transfer Object for a reservation.
 * @author Mohammad Rimawi
 * @param borrowerId
 * @param itemId
 */
public record ReserveDTO(String borrowerId, String itemId) {}