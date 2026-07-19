package com.exalt.library.validation;

import com.exalt.library.controllers.dto.AuthorDTO;

/**
 * a class for validating author fields
 * @author Mohammad Rimawi
 */
public class AuthorValidator {

    /**
     * a private constructor to prevent instantiation
     */
    private AuthorValidator() {}

    /**
     * a static method that validates
     * @param authorDTO
     */
    public static void validate(AuthorDTO authorDTO) {
        if (!Validator.notBlank(authorDTO.name())) {
            throw new IllegalArgumentException("Name is required");
        }
        if (!Validator.size(authorDTO.name(), 2, 100)) {
            throw new IllegalArgumentException("Name must be between 2 and 100 characters");
        }
        if(!Validator.notBlank(authorDTO.nationality())) {
            throw new IllegalArgumentException("Nationality is required");
        }
        if(!Validator.size(authorDTO.nationality(), 2, 100)) {
            throw new IllegalArgumentException("Nationality must be between 2 and 100 characters");
        }
    }
}
