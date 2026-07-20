package com.exalt.library.models;

import java.time.LocalDate;

/**
 * a class representing the author of a book, defines his/her name and nationality
 * gives the author a unique id
 * @author Mohammad Rimawi
 */
public class Author {
    private String name; // represents the name of the author
    private String nationality; // represents the nationality of the author
    private LocalDate birthDate; // represents the birthdate of the author

    /**
     * A default constructor
     */
    public Author() { }

//    ==== GETTERS ====
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

    /**
     * a method for getting the birthdate
     * @return
     */
    public LocalDate getBirthDate() {
        return birthDate;
    }
    //    ==== GETTERS ====

//    ==== SETTERS ====

    /**
     * a method for setting the name of the author
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * a method for setting the nationality of the author
     * @param nationality
     */
    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    /**
     * a method for setting the birthdate
     * @param birthDate
     */
    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }
    //    ==== SETTERS ====

    @Override
    public String toString() {
        return "Author{" +
                "name='" + name + '\'' +
                ", nationality='" + nationality + '\'' +
                ", birthDate='" + birthDate + '\'' +
                '}';
    }
}
