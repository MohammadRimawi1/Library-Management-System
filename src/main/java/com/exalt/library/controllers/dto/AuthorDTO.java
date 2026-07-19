package com.exalt.library.controllers.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * A record representing the Data Transfer Object for an author.
 * @param name the name of the author
 * @param nationality the nationality of the author
 * @author Mohammad Rimawi
 */
public record AuthorDTO(String name, String nationality) {}