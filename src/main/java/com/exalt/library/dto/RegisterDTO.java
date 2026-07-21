package com.exalt.library.dto;

/**
 * A record representing the Data Transfer Object for a register.
 * @param name
 * @param email
 * @param password
 * @param registrationKey
 */
public record RegisterDTO(
        String name,
        String email,
        String password,
        String phoneNumber,
        String registrationKey // For librarian
) {}