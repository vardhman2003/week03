package DoublyLinkedList;

class Book {
    String title;
    String author;
    String genre;
    int bookId;
    boolean isAvailable;
    Book next;
    Book prev;

    public Book(String title, String author, String genre, int bookId, boolean isAvailable) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.bookId = bookId;
        this.isAvailable = isAvailable;
        this.next = null;
        this.prev = null;
    }

    @Override
    public String toString() {
        return "Book ID: " + bookId + ", Title: " + title + ", Author: " + author +
                ", Genre: " + genre + ", Availability: " + (isAvailable ? "Available" : "Not Available");
    }
}

class Library {
    private Book head;
    private Book tail;
    private int totalBooks;

    // Add a book at the beginning
    public void addBookAtBeginning(String title, String author, String genre, int bookId, boolean isAvailable) {
        Book newBook = new Book(title, author, genre, bookId, isAvailable);
        if (head == null) {
            head = tail = newBook;
        } else {
            newBook.next = head;
            head.prev = newBook;
            head = newBook;
        }
        totalBooks++;
    }

    // Add a book at the end
    public void addBookAtEnd(String title, String author, String genre, int bookId, boolean isAvailable) {
        Book newBook = new Book(title, author, genre, bookId, isAvailable);
        if (tail == null) {
            head = tail = newBook;
        } else {
            tail.next = newBook;
            newBook.prev = tail;
            tail = newBook;
        }
        totalBooks++;
    }

    // Add a book at a specific position
    public void addBookAtPosition(int position, String title, String author, String genre, int bookId, boolean isAvailable) {
        if (position <= 0 || head == null) {
            addBookAtBeginning(title, author, genre, bookId, isAvailable);
            return;
        }
        if (position >= totalBooks) {
            addBookAtEnd(title, author, genre, bookId, isAvailable);
            return;
        }
        Book newBook = new Book(title, author, genre, bookId, isAvailable);
        Book temp = head;
        for (int i = 0; i < position - 1; i++) {
            temp = temp.next;
        }
        newBook.next = temp.next;
        temp.next.prev = newBook;
        temp.next = newBook;
        newBook.prev = temp;
        totalBooks++;
    }

    // Remove a book by Book ID
    public void removeBook(int bookId) {
        if (head == null) {
            System.out.println("No books to remove.");
            return;
        }
        Book temp = head;

        // If the book to remove is at the head
        if (temp.bookId == bookId) {
            if (head == tail) { // Only one book in the library
                head = tail = null;
            } else {
                head = head.next;
                head.prev = null;
            }
            totalBooks--;
            System.out.println("Book with ID " + bookId + " removed.");
            return;
        }

        // Traverse the list to find the book
        while (temp != null && temp.bookId != bookId) {
            temp = temp.next;
        }

        if (temp == null) {
            System.out.println("Book with ID " + bookId + " not found.");
            return;
        }

        // If the book is in the middle or at the end
        if (temp.next != null) {
            temp.next.prev = temp.prev;
        } else { // Book is at the tail
            tail = temp.prev;
        }
        if (temp.prev != null) {
            temp.prev.next = temp.next;
        }
        totalBooks--;
        System.out.println("Book with ID " + bookId + " removed.");
    }

    // Search for a book by Title or Author
    public void searchBook(String query) {
        if (head == null) {
            System.out.println("No books in the library.");
            return;
        }
        Book temp = head;
        boolean found = false;
        System.out.println("Search Results:");
        while (temp != null) {
            if (temp.title.equalsIgnoreCase(query) || temp.author.equalsIgnoreCase(query)) {
                System.out.println(temp);
                found = true;
            }
            temp = temp.next;
        }
        if (!found) {
            System.out.println("No books found with the given title or author.");
        }
    }

    // Update a book's availability status
    public void updateAvailability(int bookId, boolean isAvailable) {
        if (head == null) {
            System.out.println("No books in the library.");
            return;
        }
        Book temp = head;
        while (temp != null) {
            if (temp.bookId == bookId) {
                temp.isAvailable = isAvailable;
                System.out.println("Updated availability for Book ID " + bookId);
                return;
            }
            temp = temp.next;
        }
        System.out.println("Book with ID " + bookId + " not found.");
    }

    // Display all books in forward order
    public void displayBooksForward() {
        if (head == null) {
            System.out.println("No books in the library.");
            return;
        }
        Book temp = head;
        System.out.println("Books in Forward Order:");
        while (temp != null) {
            System.out.println(temp);
            temp = temp.next;
        }
    }

    // Display all books in reverse order
    public void displayBooksReverse() {
        if (tail == null) {
            System.out.println("No books in the library.");
            return;
        }
        Book temp = tail;
        System.out.println("Books in Reverse Order:");
        while (temp != null) {
            System.out.println(temp);
            temp = temp.prev;
        }
    }

    // Count the total number of books
    public int countBooks() {
        return totalBooks;
    }
}

public class LibraryManagementSystem {
    public static void main(String[] args) {
        Library library = new Library();

        // Add books
        library.addBookAtEnd("Book A", "Author A", "Fiction", 101, true);
        library.addBookAtBeginning("Book B", "Author B", "Science", 102, false);
        library.addBookAtPosition(1, "Book C", "Author C", "History", 103, true);

        // Display books
        library.displayBooksForward();
        library.displayBooksReverse();

        // Search for a book
        library.searchBook("Author B");

        // Update availability
        library.updateAvailability(101, false);

        // Remove a book
        library.removeBook(102);

        // Display books and count
        library.displayBooksForward();
        System.out.println("Total Books: " + library.countBooks());
    }
}

