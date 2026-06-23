import java.util.ArrayList;
import java.util.List;

public class Borrower {
    private int id;
    private static int count = 1;
    private String name;
    private List<Integer> borrowedBooksId;

    public Borrower(String name) {
        this.id = count;
        this.name = name;
        this.borrowedBooksId = new ArrayList<>();
        count++;
    }

//    ==== GETTERS ====
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Integer> getBorrowedBooksId() {
        return borrowedBooksId;
    }
//    ==== GETTERS ====

    public void borrowBook(int bookId) {
        borrowedBooksId.add(bookId);
    }

    public void returnBook(int bookId) {
        borrowedBooksId.remove(bookId);
    }

    @Override
    public String toString() {
        return "Borrower{" +
                "id = " + id +
                ", name = '" + name + '\'' +
                ", borrowedBooksId = " + borrowedBooksId +
                '}';
    }
}
