import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// ---------------- Book Class ---------------
class Book {
    private String title;
    private String author;
    private String bookId;
    private boolean isBorrowed;

    public Book(String title, String author, String bookId) {
        this.title = title;
        this.author = author;
        this.bookId= bookId;
        this.isBorrowed = false;
    }

    public String getTitle() { return title; }
    public boolean isBorrowed() { return isBorrowed; }

    public void borrowBook() { isBorrowed = true; }
    public void returnBook() { isBorrowed = false; }

    @Override
    public String toString() {
        return title + " by " + author + " | BOOKID " + bookId+ " | " + (isBorrowed ? "Borrowed" : "Available");
    }
}

// ---------------- User Class ----------------
class User {
    private String name;
    private String id;
    private List<Book> borrowedBooks;

    public User(String name, String id) {
        this.name = name;
        this.id = id;
        this.borrowedBooks = new ArrayList<>();
    }

    public void borrowBook(Book book) {
        borrowedBooks.add(book);
        book.borrowBook();
    }

    public void returnBook(Book book) {
        borrowedBooks.remove(book);
        book.returnBook();
    }

    public List<Book> getBorrowedBooks() { return borrowedBooks; }

    @Override
    public String toString() {
        return name + " | ID: " + id + " | Borrowed Books: " + borrowedBooks.size();
    }
}

// ---------------- Library Class ----------------
class Library {
    private List<Book> books;

    public Library() {
        books = new ArrayList<>();
    }

    public void addBook(Book book) { books.add(book); }
    public void removeBook(Book book) { books.remove(book); }

    public void listBooks() {
        if (books.isEmpty()) {
            System.out.println("No books in the library.");
        } else {
            for (Book b : books) {
                System.out.println(b);
            }
        }           
    }

    public Book findBookByTitle(String title) {
        for (Book b : books) {
            if (b.getTitle().equalsIgnoreCase(title)) return b;
        }
        return null;
    }
}

// ---------------- Main Class ----------------
public class LibrarySystem {
    public static void main(String[] args) {                            
        Library library = new Library();
        User user = new User("Elif", "U001");
        Scanner sc = new Scanner(System.in);

        // Örnek kitaplar
        library.addBook(new Book("Clean Code", "Robert Martin", "12345"));
        library.addBook(new Book("Java Basics", "James Gosling", "67890"));
        library.addBook(new Book("Effective Java", "Joshua Bloch", "54321"));

        int choice = -1;
        while (choice != 0) {
            System.out.println("\n1- List Books\n2- Borrow Book\n3- Return Book\n0- Exit");
            choice = sc.nextInt();
            sc.nextLine(); // Enter tuşunu yakalamak için

            switch (choice) {
                case 1:
                    library.listBooks();
                    break;
                case 2:
                    System.out.print("Book title to borrow: ");
                    String borrowTitle = sc.nextLine();
                    Book borrowBook = library.findBookByTitle(borrowTitle);
                    if (borrowBook != null && !borrowBook.isBorrowed()) {
                        user.borrowBook(borrowBook);
                        System.out.println("Book borrowed!");
                    } else {
                        System.out.println("Book not available.");
                    }
                    break;
                case 3:
                    System.out.print("Book title to return: ");
                    String returnTitle = sc.nextLine();
                    Book returnBook = library.findBookByTitle(returnTitle);
                    if (returnBook != null && returnBook.isBorrowed()) {
                        user.returnBook(returnBook);
                        System.out.println("Book returned!");
                    } else {
                        System.out.println("Book not found or not borrowed.");
                    }
                    break;
                case 0:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice!");
            }
        }

        sc.close();
    }
}