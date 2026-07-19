package com.exalt.library.controllers.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * A record representing the Data Transfer Object for a borrower.
 * @param name the name of the borrower
 * @author Mohammad Rimawi
 */
public record BorrowerDTO(String name) {}