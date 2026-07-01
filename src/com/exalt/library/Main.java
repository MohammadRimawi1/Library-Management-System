package com.exalt.library;

import com.exalt.library.controllers.services.LibraryItemServices;
import com.exalt.library.models.*;
import com.exalt.library.controllers.services.BorrowerServices;
import com.exalt.library.controllers.services.LoanServices;
import com.exalt.library.models.libraryitems.Book;
import com.exalt.library.models.libraryitems.LibraryItem;
import com.exalt.library.models.libraryitems.Story;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Author author1 = new Author();
        author1.setName("Tahseen");
        author1.setNationality("Palestine");

        Author author2 = new Author();
        author2.setName("Kareem");
        author2.setNationality("Syria");

        Author author3 = new Author();
        author3.setName("Whoever");
        author3.setNationality("Japan");

        LibraryItem book1 = new Book();
        book1.setTitle("Computer Networks");
        book1.setAuthor(author1);
        book1.setAvailable(true);

        LibraryItem book2 = new Book();
        book2.setTitle("Compiler Principles");
        book2.setAuthor(author1);
        book2.setAvailable(true);

        LibraryItem book3 = new Book();
        book3.setTitle("Software Engineering");
        book3.setAuthor(author2);
        book3.setAvailable(true);

        LibraryItem story1 = new Story();
        story1.setTitle("Demon Slayer");
        story1.setAuthor(author3);
        story1.setAvailable(true);

        LibraryItem story2 = new Story();
        story2.setTitle("Demon Slayer");
        story2.setAuthor(author3);
        story2.setAvailable(true);

        Borrower borrower1 = new Borrower();
        borrower1.setName("Murse");

        Borrower borrower2 = new Borrower();
        borrower2.setName("Malik");

        SingletonLibrary lib = SingletonLibrary.getInstance();
        lib.setLibraryItems(new ArrayList<>());
        lib.setBorrowers(new ArrayList<>());
        lib.setLoans(new ArrayList<>());

//        ======== BOOK SERVICE ========
        System.out.println("{ ========== BOOK SERVICE ========== }");
        LibraryItemServices libraryItemService = new LibraryItemServices();

        libraryItemService.addItem(lib.getLibraryItems(), book1);
        libraryItemService.addItem(lib.getLibraryItems(), book2);
        libraryItemService.addItem(lib.getLibraryItems(), book3);
        libraryItemService.addItem(lib.getLibraryItems(), story1);

        System.out.println("All Books:");
        libraryItemService.printAllItems(lib.getLibraryItems());

        System.out.println("===========");

        System.out.println("Find book");
        System.out.println(libraryItemService.findItemById(lib.getLibraryItems(), 2));

        System.out.println("===========");

        System.out.println("Book With id 3 exists?");
        System.out.println(libraryItemService.itemExists(lib.getLibraryItems(), 3));

        System.out.println("===========");

        System.out.println("Do all items have title?");
        System.out.println(libraryItemService.allItemsHaveTitles(lib.getLibraryItems()));
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
//
//        ======== LOAN SERVICE ========
        System.out.println("{ ========== LOAN SERVICE ========== }");
        LoanServices loanService = new LoanServices();

        loanService.setLibraryItemOperations(new LibraryItemServices());
        loanService.setBorrowerOperations(new BorrowerServices());

        System.out.println("Loan a book");
        loanService.borrowLibraryItem(lib.getLoans(), lib.getLibraryItems(), lib.getBorrowers(), 1, 1);
        loanService.borrowLibraryItem(lib.getLoans(), lib.getLibraryItems(), lib.getBorrowers(), 2, 4);

        System.out.println("===========");

        System.out.println("Get Loans");
        System.out.println(lib.getLoans());

        System.out.println("===========");

        System.out.println("Return Book 3");
        loanService.returnLibraryItem(lib.getLoans(), story1, borrower2);

        System.out.println("Get Loans Again");
        System.out.println(lib.getLoans());
//       { ======== LOAN SERVICE ======== }
    }
}
