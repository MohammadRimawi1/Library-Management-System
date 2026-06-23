public class Book {
    private int id;
    private static int count = 1;
    private String title;
    private boolean isAvailable;

    public Book(String title, boolean isAvailable) {
        this.id = count;
        this.title = title;
        this.isAvailable = isAvailable;
        count++;
    }
//    ==== GETTERS ====
    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public boolean isAvailable() {
        return isAvailable;
    }
//    ==== GETTERS ====

//    ==== SETTERS ====
    public void setAvailable(boolean available) {
        isAvailable = available;
    }
//    ==== SETTERS ====

    @Override
    public String toString() {
        return "Book{ID = " + id + ", " +
                "title = '" + title +
                ", isAvailable = " + isAvailable +
                '}';
    }
}
