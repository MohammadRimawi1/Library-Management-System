package com.exalt.library.dto;

/**
 * A record representing the Data Transfer Object for a LibraryItem.
 * @param type the type of the library item
 * @param title the title of the library item
 * @param numOfCopies the number of copies (only for physical items)
 * @param author the author information
 * @author Mohammad Rimawi
 */
public record LibraryItemDTO(
        String type,
        String title,
        Integer numOfCopies,
        String description,
        String language,
        AuthorDTO author
) {}