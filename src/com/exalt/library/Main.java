package com.exalt.library;

import com.exalt.library.models.Author;
import com.exalt.library.models.Book;
import com.exalt.library.models.Borrower;
import com.exalt.library.services.BookServices;
import com.exalt.library.services.BorrowerServices;
import com.exalt.library.services.LoanServices;

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
        book2.setTitle("Compiler Principles");
        book2.setAuthor(author1);
        book2.setAvailable(true);

        Book book3 = new Book();
        book3.setTitle("Software Engineering");
        book3.setAuthor(author2);
        book3.setAvailable(true);

        Book book4 = new Book();
        book4.setTitle("Image Processing");
        book4.setAuthor(author2);
        book4.setAvailable(true);

        Borrower borrower1 = new Borrower();
        borrower1.setName("Murse");

        Borrower borrower2 = new Borrower();
        borrower2.setName("Malik");

        SingletonLibrary lib = SingletonLibrary.getInstance();
        lib.setBooks(new ArrayList<>());
        lib.setBorrowers(new ArrayList<>());
        lib.setLoans(new ArrayList<>());

//        ======== BOOK SERVICE ========
        System.out.println("{ ========== BOOK SERVICE ========== }");
        BookServices bookService = new BookServices();

        bookService.addBook(lib.getBooks(), book1);
        bookService.addBook(lib.getBooks(), book2);
        bookService.addBook(lib.getBooks(), book3);
        bookService.addBook(lib.getBooks(), book4);

        System.out.println("All Books:");
        bookService.printAllBooks(lib.getBooks());

        System.out.println("===========");

        System.out.println("Find book");
        System.out.println(bookService.findBookById(lib.getBooks(), 2));

        System.out.println("===========");

        System.out.println("Book With id 3 exists?");
        System.out.println(bookService.bookExists(lib.getBooks(), 3));

        System.out.println("===========");

        System.out.println("Do all books have title?");
        System.out.println(bookService.allBooksHaveTitles(lib.getBooks()));
//       { ======== BOOK SERVICE ======== }
//
//        ======== BORROWER SERVICE ========
        System.out.println("{ ========== BORROWER SERVICE ========== }");
        BorrowerServices borrowerService = new BorrowerServices();

        borrowerService.assignBorrower(lib.getBorrowers(), borrower1);
        borrowerService.assignBorrower(lib.getBorrowers(), borrower2);

        System.out.println("All Borrowers:");
        borrowerService.printAllBorrowers(lib.getBorrowers());

        System.out.println("===========");

        System.out.println("Find borrower");
        System.out.println(borrowerService.findBorrowerById(lib.getBorrowers(), 2));

        System.out.println("===========");

        System.out.println("Borrower With id 3 exists?");
        System.out.println(borrowerService.borrowerExists(lib.getBorrowers(), 3));
//       { ======== BORROWER SERVICE ======== }

//        ======== LOAN SERVICE ========
        System.out.println("{ ========== LOAN SERVICE ========== }");
        LoanServices loanService = new LoanServices();

        loanService.setBookOperations(new BookServices());
        loanService.setBorrowerOperations(new BorrowerServices());

        System.out.println("Loan a book");
        loanService.borrowBook(lib.getLoans(), lib.getBooks(), lib.getBorrowers(), 1, 1);
        loanService.borrowBook(lib.getLoans(), lib.getBooks(), lib.getBorrowers(), 2, 3);

        System.out.println("===========");

        System.out.println("Get Loans");
        System.out.println(lib.getLoans());

        System.out.println("===========");

        System.out.println("Return Book 3");
        loanService.returnBook(lib.getLoans(), book3, borrower2);

        System.out.println("Get Loans Again");
        System.out.println(lib.getLoans());
//       { ======== LOAN SERVICE ======== }
    }
}
