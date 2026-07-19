package com.exalt.library.controllers.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

/**
 * A record representing the Data Transfer Object for a reservation.
 * @author Mohammad Rimawi
 * @param borrowerId
 * @param itemId
 */
public record ReserveDTO(String borrowerId, String itemId) {}