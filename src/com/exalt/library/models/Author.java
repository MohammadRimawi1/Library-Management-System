package com.exalt.library.models;

/**
 * a class representing the author of a book, defines his/her name and nationality
 * gives the author a unique id
 * @author Mohammad Rimawi
 */
public class Author {
    private final int id; // Represents the id for an author
    private static int count = 1; // a counter to automatically assigns an id for the author
    private final String name; // represents the name of the author
    private String nationality; // represents the nationality of the author

    /**
     * A parameterized constructor that takes name and nationality attributes
     * automatically increments the counter for the id
     * @param name
     * @param nationality
     */
    public Author(String name, String nationality) {
        this.id = count;
        this.name = name;
        this.nationality = nationality;
        count++;
    }

//    ==== GETTERS ====
    /**
     * Get the author Id
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Get the name of the author
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Get the nationality of the author
     * @return the nationality
     */
    public String getNationality() {
        return nationality;
    }
//    ==== GETTERS ====

    @Override
    public String toString() {
        return "Author{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", nationality='" + nationality + '\'' +
                '}';
    }
}
