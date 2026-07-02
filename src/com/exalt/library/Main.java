package com.exalt.library;

import com.exalt.library.controllers.services.*;
import com.exalt.library.controllers.strategies.BorrowStrategyFactory;
import com.exalt.library.models.*;
import com.exalt.library.models.libraryitems.onlineitems.BookOnline;
import com.exalt.library.models.libraryitems.physicalitems.BookPhysical;

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

        BookPhysical physicalBook1 = new BookPhysical();
        physicalBook1.setTitle("Physical Book1");
        physicalBook1.setAuthor(author1);
        physicalBook1.setNumOfCopies(3);

        BookPhysical physicalBook2 = new BookPhysical();
        physicalBook2.setTitle("Physical Book2");
        physicalBook2.setAuthor(author1);
        physicalBook2.setNumOfCopies(1);

        BookOnline onlineBook1 = new BookOnline();
        onlineBook1.setTitle("Online Book1");
        onlineBook1.setAuthor(author2);

        BookOnline onlineBook2 = new BookOnline();
        onlineBook1.setTitle("Online Book2");
        onlineBook1.setAuthor(author2);

        Borrower borrower1 = new Borrower();
        borrower1.setName("Ahmad");

        Borrower borrower2 = new Borrower();
        borrower2.setName("Murse");

//        =================================================
        SingletonLibrary lib = SingletonLibrary.getInstance();
        lib.setLibraryItems(new ArrayList<>());
        lib.setBorrowers(new ArrayList<>());
        lib.setLoans(new ArrayList<>());

//        ===============================================================
        LibraryItemServices libServ = new LibraryItemServices();
        libServ.addItem(lib.getLibraryItems(), physicalBook1);
        libServ.addItem(lib.getLibraryItems(), physicalBook2);

        libServ.addItem(lib.getLibraryItems(), onlineBook1);
        libServ.addItem(lib.getLibraryItems(), onlineBook2);

//        ===============================================================
        BorrowerServices borrowerServices = new BorrowerServices();
        borrowerServices.assignBorrower(lib.getBorrowers(), borrower1);
        borrowerServices.assignBorrower(lib.getBorrowers(), borrower2);

//        ====================================================================
        InHandBorrowStrategyService inHandStrategy = new InHandBorrowStrategyService();
        OnlineBorrowStrategyService onlineStrategy = new OnlineBorrowStrategyService();

        BorrowStrategyFactory borrowStrategyFactory = new BorrowStrategyFactory();
        borrowStrategyFactory.setInHandStrategy(inHandStrategy);
        borrowStrategyFactory.setOnlineStrategy(onlineStrategy);

        LoanServices loanServices = new LoanServices();
        loanServices.setLibraryItemOperations(new LibraryItemServices());
        loanServices.setBorrowerOperations(new BorrowerServices());
        loanServices.setBorrowStrategyFactory(borrowStrategyFactory);

        System.out.println("Before: " + physicalBook2);
        loanServices.borrowLibraryItem(lib.getLoans(), lib.getLibraryItems(), lib.getBorrowers(), borrower1.getId(), physicalBook2.getId());
        System.out.println("After 1 borrow (numOfCopies was 1): " + physicalBook2);

        System.out.println("=============");

        loanServices.borrowLibraryItem(lib.getLoans(), lib.getLibraryItems(), lib.getBorrowers(), borrower1.getId(), onlineBook1.getId());
        loanServices.borrowLibraryItem(lib.getLoans(), lib.getLibraryItems(), lib.getBorrowers(), borrower2.getId(), onlineBook1.getId());
        System.out.println("Online book after 2 borrows: " + onlineBook1);

        System.out.println("===============");

        System.out.println(lib.getLoans());

    }
}
