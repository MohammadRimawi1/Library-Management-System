package com.exalt.library.dto;

/**
 * A record representing the Data Transfer Object for a Login.
 * @param email
 * @param password
 */
public record LoginDTO(
        String email,
        String password
) {}