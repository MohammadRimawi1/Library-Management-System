package library;

import library.models.Book;
import library.models.Borrower;

public class Main {
    public static void main(String[] args) {
//        ==== BOOKS ====
        Book book1 = new Book("Physics", true);
        Book book2 = new Book("Calculas", false);
        Book book3 = new Book("Software Engineer", true);
        Book book4 = new Book("", true);
//        ==== BOOKS ====

//        ==== BORROWERS ====
        Borrower borrower1 = new Borrower("Ahmad");
        Borrower borrower2 = new Borrower("Mohammad");
        Borrower borrower3 = new Borrower("Zaid");
//        ==== BORROWERS ====

//        ==== LIBRARY ====
        Library lib = new Library();
        lib.addBook(book1);
        lib.addBook(book2);
        lib.addBook(book3);
        lib.addBook(book4);
        lib.addBook(new Book("Computer Networks", false));
        lib.addBook(new Book("Compiler Princples", true));

        lib.assignBorrower(borrower1);
        lib.assignBorrower(borrower2);
        lib.assignBorrower(borrower3);
//        ==== LIBRARY ====

//        ==== BORROW BOOKS ====
        borrower1.borrowBook(book1.getId());
        borrower1.borrowBook(book2.getId());
        borrower2.borrowBook(book3.getId());
        borrower2.borrowBook(book4.getId());
        borrower3.borrowBook(book4.getId());
//        ==== BORROW BOOKS ====

//        ======= PRINTS ======
        System.out.println("======= BOOKS ======= ");
        System.out.println(book1.toString());
        System.out.println(book2.toString());
        System.out.println(book3.toString());

        System.out.println("======= BORROWED BOOKS ======= ");
        System.out.println(borrower1.toString());
        System.out.println(borrower2.toString());
        System.out.println(borrower3.toString());

        System.out.println("====== ALL BOOKS ======");
        lib.listAllBooks();

        System.out.println("===== FIND BOOK =====");
        System.out.println(lib.findBook(3));

        System.out.println("====== DOES THE BOOK EXIST? ======");
        System.out.println(lib.exists(3));

        System.out.println("====== HOW MANY BOOKS ======");
        System.out.println(lib.listHowMuchBooksExists());

        System.out.println("====== HOW MANY BORROWERS ======");
        System.out.println(lib.listHowMuchBorrowersExists());

        System.out.println("====== SORTED BOOKS LIST ======");
        System.out.println(lib.sortBooks());

        System.out.println("====== BOOKS WITH NO TITLE EXISTS OR NOT ======");
        System.out.println(lib.allBooksHaveTitles());

        System.out.println("====== ARE ALL BOOKS AVAILABLE ======");
        System.out.println(lib.areALlBooksAvailable());

        System.out.println("====== BORROWER WITH THE HIGHEST BORROWS ======");
        System.out.println(lib.topBorrower());

        System.out.println("====== BORROWER WITH THE LEAST BORROWS ======");
        System.out.println(lib.leastBorrower());

        System.out.println("====== SUM OF ALL BOOKS ID ======");
        System.out.println(lib.sumOfAllBooksId());
//        ======= PRINTS ======

    }
}