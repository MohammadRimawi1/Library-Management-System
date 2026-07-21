package com.exalt.library.validation;

import com.exalt.library.dto.LoginDTO;

/**
 * a class for validating login fields
 * @author Mohammad Rimawi
 */
public class LoginValidator {

    private LoginValidator() {}

    /**
     * a static method that validates
     * @param loginDTO
     */
    public static void validate(LoginDTO loginDTO) {
        if (!Validator.notBlank(loginDTO.email())) {
            throw new IllegalArgumentException("Email is required");
        }
        if (!Validator.notBlank(loginDTO.password())) {
            throw new IllegalArgumentException("Password is required");
        }
    }
}