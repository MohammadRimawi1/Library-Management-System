import java.util.ArrayList;
import java.util.List;

public class Library {
    private List<Book> books;
    private List<Borrower> borrowers;

    public Library() {
        this.books = new ArrayList<>();
        this.borrowers = new ArrayList<>();
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public void assignBorrower(Borrower borrower) {
        borrowers.add(borrower);
    }

    public void listAllBooks() {
        for (Book book : books) {
            System.out.println(book + ", ");
        }
    }

    public Book findBook(int id) {
        return books.stream()
                .filter(book -> book.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public Borrower findBorrower(int id) {
        return borrowers.stream()
                .filter(book -> book.getId() == id)
                .findFirst()
                .orElse(null);
    }
}
