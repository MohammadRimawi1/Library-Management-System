package com.exalt.library.dto;

/**
 * A record representing the Data Transfer Object for a borrower.
 * @param name the name of the borrower
 * @author Mohammad Rimawi
 */
public record BorrowerDTO(
        String name,
        String phoneNumber
) {}