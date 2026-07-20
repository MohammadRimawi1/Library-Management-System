package com.exalt.library.dto;

public record RegisterDTO(
        String name,
        String email,
        String password,
        String registrationKey // For librarian
) {}