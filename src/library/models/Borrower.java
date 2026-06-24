package library.models;

import java.util.ArrayList;
import java.util.List;

/**
 * A class representing the library borrower
 * Each borrower has a unique id, they also have names, and a list of borrowed books
 * @author Mohammad Rimawi
 */
public class Borrower {
    private int id; // Defines the identity number for a borrower
    private static int count = 1; // a counter to automatically assigns an id for the borrower
    private String name; // Defines the name of the borrower
    private List<Integer> borrowedBooksId; // Stores the books id's in which the user has borrowed

    /**
     * A parameterized constructor that initializes the id, name, and a new arrayList for the borrowed books
     * automatically increments the id count
     * @param name
     */
    public Borrower(String name) {
        this.id = count;
        this.name = name;
        this.borrowedBooksId = new ArrayList<>();
        count++;
    }

//    ==== GETTERS ====

    /**
     * a method for getting the id value
     * @return
     */
    public int getId() {
        return id;
    }

    /**
     * a method for getting the title
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * a method for getting the list of the borrowed books id
     * @return
     */
    public List<Integer> getBorrowedBooksId() {
        return borrowedBooksId;
    }
//    ==== GETTERS ====

    /**
     * This method adds a bookId to the borrowed books list
     * @param bookId
     */
    public void borrowBook(int bookId) {
        borrowedBooksId.add(bookId);
    }

    /**
     * This method returns/removes a bookId from the borrowed books list
     * @param bookId
     */
    public void returnBook(int bookId) {
        borrowedBooksId.remove(bookId);
    }

    /**
     * a method for checking the size of the borrowed books id list
     * @return
     */
    public int borrowedBooksIdSize() {
        return borrowedBooksId.size();
    }

    @Override
    public String toString() {
        return "Borrower: {" +
                "id = " + id +
                ", name = '" + name + '\'' +
                ", borrowedBooksId = " + borrowedBooksId +
                '}';
    }
}
