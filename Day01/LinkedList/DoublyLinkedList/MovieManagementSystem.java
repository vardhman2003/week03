package DoublyLinkedList;

class MovieNode {
    String title;
    String director;
    int year;
    double rating;
    MovieNode next;
    MovieNode prev;

    public MovieNode(String title, String director, int year, double rating) {
        this.title = title;
        this.director = director;
        this.year = year;
        this.rating = rating;
        this.next = null;
        this.prev = null;
    }
}

class MovieManagement {
    private MovieNode head;
    private MovieNode tail;

    // Add a movie at the beginning, end, or at a specific position
    public void addMovie(String title, String director, int year, double rating, Integer position) {
        MovieNode newMovie = new MovieNode(title, director, year, rating);

        if (head == null) { // If the list is empty
            head = tail = newMovie;
        } else if (position == null || position >= countMovies()) { // Add at the end
            tail.next = newMovie;
            newMovie.prev = tail;
            tail = newMovie;
        } else if (position == 0) { // Add at the beginning
            newMovie.next = head;
            head.prev = newMovie;
            head = newMovie;
        } else { // Add at a specific position
            MovieNode temp = head;
            for (int i = 0; i < position - 1; i++) {
                temp = temp.next;
            }
            newMovie.next = temp.next;
            if (temp.next != null) {
                temp.next.prev = newMovie;
            }
            newMovie.prev = temp;
            temp.next = newMovie;
        }
    }

    // Remove a movie by title
    public void removeMovie(String title) {
        if (head == null) {
            System.out.println("No movies in the system to remove.");
            return;
        }

        MovieNode temp = head;
        while (temp != null) {
            if (temp.title.equalsIgnoreCase(title)) {
                if (temp == head) { // Remove the head
                    head = head.next;
                    if (head != null) {
                        head.prev = null;
                    }
                } else if (temp == tail) { // Remove the tail
                    tail = tail.prev;
                    if (tail != null) {
                        tail.next = null;
                    }
                } else { // Remove a middle node
                    temp.prev.next = temp.next;
                    if (temp.next != null) {
                        temp.next.prev = temp.prev;
                    }
                }
                System.out.println("Movie \"" + title + "\" removed.");
                return;
            }
            temp = temp.next;
        }
        System.out.println("Movie \"" + title + "\" not found.");
    }

    // Search for a movie by director or rating
    public void searchMovies(String director, Double rating) {
        MovieNode temp = head;
        boolean found = false;

        while (temp != null) {
            if ((director != null && temp.director.equalsIgnoreCase(director)) ||
                    (rating != null && temp.rating == rating)) {
                System.out.println("Found Movie: Title: " + temp.title + ", Director: " + temp.director +
                        ", Year: " + temp.year + ", Rating: " + temp.rating);
                found = true;
            }
            temp = temp.next;
        }

        if (!found) {
            System.out.println("No movies found matching the criteria.");
        }
    }

    // Display all movies in forward order
    public void displayMoviesForward() {
        if (head == null) {
            System.out.println("No movies in the system.");
            return;
        }

        System.out.println("Movies in forward order:");
        MovieNode temp = head;
        while (temp != null) {
            System.out.println("Title: " + temp.title + ", Director: " + temp.director +
                    ", Year: " + temp.year + ", Rating: " + temp.rating);
            temp = temp.next;
        }
    }

    // Display all movies in reverse order
    public void displayMoviesReverse() {
        if (tail == null) {
            System.out.println("No movies in the system.");
            return;
        }

        System.out.println("Movies in reverse order:");
        MovieNode temp = tail;
        while (temp != null) {
            System.out.println("Title: " + temp.title + ", Director: " + temp.director +
                    ", Year: " + temp.year + ", Rating: " + temp.rating);
            temp = temp.prev;
        }
    }

    // Update a movie's rating by title
    public void updateMovieRating(String title, double newRating) {
        MovieNode temp = head;

        while (temp != null) {
            if (temp.title.equalsIgnoreCase(title)) {
                temp.rating = newRating;
                System.out.println("Updated rating of \"" + title + "\" to " + newRating);
                return;
            }
            temp = temp.next;
        }
        System.out.println("Movie \"" + title + "\" not found.");
    }

    // Count the total number of movies
    public int countMovies() {
        int count = 0;
        MovieNode temp = head;

        while (temp != null) {
            count++;
            temp = temp.next;
        }

        return count;
    }
}

public class MovieManagementSystem {
    public static void main(String[] args) {
        MovieManagement mms = new MovieManagement();

        // Add movies
        mms.addMovie("Inception", "Christopher Nolan", 2010, 8.8, null);
        mms.addMovie("Interstellar", "Christopher Nolan", 2014, 8.6, null);
        mms.addMovie("The Dark Knight", "Christopher Nolan", 2008, 9.0, 0);

        // Display movies
        mms.displayMoviesForward();
        mms.displayMoviesReverse();

        // Search for a movie by director
        mms.searchMovies("Christopher Nolan", null);

        // Update a movie's rating
        mms.updateMovieRating("Inception", 9.0);

        // Remove a movie
        mms.removeMovie("Interstellar");

        // Display movies after removal
        mms.displayMoviesForward();

        // Total movie count
        System.out.println("Total Movies: " + mms.countMovies());
    }
}

