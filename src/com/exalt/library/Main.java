package com.exalt.library;

import com.exalt.library.models.Author;
import com.exalt.library.models.Book;
import com.exalt.library.models.Borrower;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Author author1 = new Author();
        author1.setName("Tahseen");
        author1.setNationality("Palestine");

        Author author2 = new Author();
        author1.setName("Kareem");
        author1.setNationality("Syria");

        Book book1 = new Book();
        book1.setTitle("Computer Networks");
        book1.setAuthor(author1);
        book1.setAvailable(true);

        Book book2 = new Book();
        book1.setTitle("Computer Principles");
        book1.setAuthor(author1);
        book1.setAvailable(true);

        Book book3 = new Book();
        book1.setTitle("Software Engineering");
        book1.setAuthor(author2);
        book1.setAvailable(true);

        Borrower borrower1 = new Borrower();
        borrower1.setName("Murse");

        Borrower borrower2 = new Borrower();
        borrower1.setName("Malik");

        Library lib = new Library();
        lib.setBooks(new ArrayList<>());
        lib.setBorrowers(new ArrayList<>());
        lib.setLoans(new ArrayList<>());

        lib.addBook(book1);
        lib.addBook(book2);
        lib.addBook(book3);

        lib.assignBorrower(borrower1);
        lib.assignBorrower(borrower2);

        lib.borrowBook(1, 1);
        lib.borrowBook(1, 2);
        lib.borrowBook(2, 3);
        System.out.println(lib.getLoans());
    }
}
