package com.exalt.library;

import com.exalt.library.models.Author;
import com.exalt.library.models.Book;
import com.exalt.library.models.Borrower;
import com.exalt.library.operations.BookOperations;
import com.exalt.library.operations.BorrowerOperations;
import com.exalt.library.operations.LoanOperations;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Author author1 = new Author();
        author1.setName("Tahseen");
        author1.setNationality("Palestine");

        Author author2 = new Author();
        author2.setName("Kareem");
        author2.setNationality("Syria");

        Book book1 = new Book();
        book1.setTitle("Computer Networks");
        book1.setAuthor(author1);
        book1.setAvailable(true);

        Book book2 = new Book();
        book2.setTitle("Computer Principles");
        book2.setAuthor(author1);
        book2.setAvailable(true);

        Book book3 = new Book();
        book3.setTitle("Software Engineering");
        book3.setAuthor(author2);
        book3.setAvailable(true);

        Borrower borrower1 = new Borrower();
        borrower1.setName("Murse");

        Borrower borrower2 = new Borrower();
        borrower2.setName("Malik");

        Library lib = new Library();
        lib.setBooks(new ArrayList<>());
        lib.setBorrowers(new ArrayList<>());
        lib.setLoans(new ArrayList<>());

//        ======== BOOK SERVICE ========
        System.out.println("{ ========== BOOK SERVICE ========== }");
        BookOperations bookOperations = new BookOperations();

        bookOperations.addBook(lib.getBooks(), book1);
        bookOperations.addBook(lib.getBooks(), book2);
        bookOperations.addBook(lib.getBooks(), book3);

        System.out.println("All Books:");
        bookOperations.printAllBooks(lib.getBooks());

        System.out.println("===========");

        System.out.println("Find book");
        System.out.println(bookOperations.findBookById(lib.getBooks(), 2));

        System.out.println("===========");

        System.out.println("Book With id 3 exists?");
        System.out.println(bookOperations.bookExists(lib.getBooks(), 3));
//       { ======== BOOK SERVICE ======== }

//        ======== BORROWER SERVICE ========
        System.out.println("{ ========== BORROWER SERVICE ========== }");
        BorrowerOperations borrowerOperations = new BorrowerOperations();

        borrowerOperations.assignBorrower(lib.getBorrowers(), borrower1);
        borrowerOperations.assignBorrower(lib.getBorrowers(), borrower2);

        System.out.println("All Borrowers:");
        borrowerOperations.printAllBorrowers(lib.getBorrowers());

        System.out.println("===========");

        System.out.println("Find borrower");
        System.out.println(borrowerOperations.findBorrowerById(lib.getBorrowers(), 2));

        System.out.println("===========");

        System.out.println("Borrower With id 3 exists?");
        System.out.println(borrowerOperations.borrowerExists(lib.getBorrowers(), 3));
//       { ======== BORROWER SERVICE ======== }

//        ======== LOAN SERVICE ========
        System.out.println("{ ========== LOAN SERVICE ========== }");
        LoanOperations loanOperations = new LoanOperations();

        System.out.println("Loan a book");
        loanOperations.borrowBook(lib.getLoans(), lib.getBooks(), lib.getBorrowers(), 1, 1);

        System.out.println("===========");

        System.out.println("Get Loans");
        System.out.println(lib.getLoans());

//       { ======== LOAN SERVICE ======== }
    }
}
