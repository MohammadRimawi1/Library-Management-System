package com.exalt.library;

import com.exalt.library.models.Author;
import com.exalt.library.models.Book;
import com.exalt.library.models.Borrower;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
//        ===== AUTHOR =====
        Author authorOne = new Author( "Tahseen", "Palestine");
        Author authorTwo = new Author("Malik", "Jordan");
        Author authorThree = new Author("Amer", "Suadi Arabia");
        Author authorFour = new Author("Lieth", "Syria");
//        ===== AUTHOR =====

//        ==== BOOKS ====
        Book book1 = new Book("Physics", authorOne ,true);
        Book book2 = new Book("Calculas", authorTwo ,false);
        Book book3 = new Book("Software Engineer", authorFour ,true);
        Book book4 = new Book("",  authorThree,true);
//        ==== BOOKS ====

//        ==== BORROWERS ====
        Borrower borrower1 = new Borrower("Ahmad");
        Borrower borrower2 = new Borrower("Mohammad");
        Borrower borrower3 = new Borrower("Zaid");
//        ==== BORROWERS ====

//        ==== LIBRARY ====
        Library lib = new Library(new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        lib.addBook(book1);
        lib.addBook(book2);
        lib.addBook(book3);
        lib.addBook(book4);

        lib.addBook(new Book("Computer Networks", authorFour ,false));
        lib.addBook(new Book("Compiler Princples",  authorFour,true));

        lib.assignBorrower(borrower1);
        lib.assignBorrower(borrower2);
        lib.assignBorrower(borrower3);

        lib.borrowBook(2, 1);
        lib.borrowBook(3, 1);
        lib.borrowBook(1, 3);
//        ==== LIBRARY ====

//        ========== PRINTS ==========
        System.out.println("==== ALL BOOKS ====");
        lib.listAllBooks();

        System.out.println("==============");

        System.out.println("==== FINDING STUFF ====");
        System.out.println(lib.findBook(2));
        System.out.println(lib.findBorrower(2));
        System.out.println(lib.findLoan(2));

        System.out.println("==============");

        System.out.println("==== GET LOANS ====");
        System.out.println(lib.getLoans());

        System.out.println("==============");

        System.out.println("==== RETURN LOANS ====");
        System.out.println(lib.returnBook(3, 1));

        System.out.println("==============");

        System.out.println("==== ARE BOOKS WITH TITLES? ====");
        System.out.println(lib.allBooksHaveTitles());

        System.out.println("==============");

        System.out.println("==== ARE ALL BOOKS AVAILABLE? ====");
        System.out.println(lib.areALlBooksAvailable());

        System.out.println("==============");

        System.out.println("==== BOOK EXISTS? ====");
        System.out.println(lib.bookExists(2));

        System.out.println("==============");

        System.out.println("==== SORTED BOOKS BY TITLE ====");
        System.out.println(lib.sortBooks());

        System.out.println("==============");

        System.out.println("==== SUM OF ALL BOOKS ID's ====");
        System.out.println(lib.sumOfAllBooksId());

        System.out.println("==============");

        System.out.println("==== LIST HOW MUCH EXISTS ====");
        System.out.println("Borrowers: " + lib.listHowMuchBorrowersExists());
        System.out.println("Books: " + lib.listHowMuchBooksExists());
//        ========== PRINTS ==========
    }
}