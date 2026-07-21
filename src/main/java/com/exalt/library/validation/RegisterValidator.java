package com.exalt.library.validation;

import com.exalt.library.dto.RegisterDTO;
import com.exalt.library.models.users.Role;

/**
 * a class for validating registration fields
 * @author Mohammad Rimawi
 */
public class RegisterValidator {

    private RegisterValidator() {}

    /**
     * validates a registration request, given the already-resolved role
     * @param registerDTO
     * @param role
     */
    public static void validate(RegisterDTO registerDTO, Role role) {
        if (!Validator.notBlank(registerDTO.email())) {
            throw new IllegalArgumentException("Email is required");
        }
        if (!Validator.isValidEmail(registerDTO.email())) {
            throw new IllegalArgumentException("Please enter a valid email");
        }

        if (!Validator.notBlank(registerDTO.password())) {
            throw new IllegalArgumentException("Password is required");
        }
        if (!Validator.size(registerDTO.password(), 8, 64)) {
            throw new IllegalArgumentException("Password must be between 8 and 64 characters");
        }
        if (role == Role.BORROWER) {
            if (!Validator.notBlank(registerDTO.name())) {
                throw new IllegalArgumentException("Name is required");
            }
            if (!Validator.size(registerDTO.name(), 2, 100)) {
                throw new IllegalArgumentException("Name must be between 2 and 100 characters");
            }
        }
        if (!Validator.isValidPhoneNumber(registerDTO.phoneNumber())) {
            throw new IllegalArgumentException("Invalid phone number format. Use E.164 format (e.g., +1234567890)");
        }
    }
}
