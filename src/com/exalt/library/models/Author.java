package com.exalt.library.models;

/**
 * a class representing the author of a book, defines his/her name and nationality
 * gives the author a unique id
 * @author Mohammad Rimawi
 */
public class Author {
    private final int id; // Represents the id for an author
    // #TODO: a counter to automatically assigns an id for the book, ITS THE JOB OF THE DB
    private static int count = 1; // Defines the counter that we will increment #TODO: Update Later
    private String name; // represents the name of the author
    private String nationality; // represents the nationality of the author

    /**
     * A default constructor
     * automatically increments the counter for the id
     */
    public Author() {
        this.id = generate(); // TODO: To assign the ID value from the DB
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

//    ==== SETTERS ====

    /**
     * A synchronized generator so we get no duplicates
     * @return - an int representing the value of the current counter
     */
    public static synchronized int generate() {
        return count++;
    } //TODO: Delete this method when working with the DB, also, it violates SRP

    @Override
    public String toString() {
        return "Author{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", nationality='" + nationality + '\'' +
                '}';
    }
}
