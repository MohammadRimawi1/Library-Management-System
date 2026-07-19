package com.exalt.library.validation;

import com.exalt.library.controllers.dto.BorrowerDTO;

/**
 * a class for validating borrower fields
 * @author Mohammad Rimawi
 */
public class BorrowerValidator {

    /**
     * a private constructor to prevent instantiation
     */
    private BorrowerValidator() {}

    /**
     * a static method that validates
     * @param borrowerDTO
     */
    public static void validate(BorrowerDTO borrowerDTO) {
        if (!Validator.notBlank(borrowerDTO.name())) {
            throw new IllegalArgumentException("Name is required");
        }
        if (!Validator.size(borrowerDTO.name(), 2, 100)) {
            throw new IllegalArgumentException("Name must be between 2 and 100 characters");
        }
    }
}
