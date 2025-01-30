package CircularLinkedList;

import java.time.LocalDateTime;

class Ticket {
    int ticketID;
    String customerName;
    String movieName;
    String seatNumber;
    LocalDateTime bookingTime;
    Ticket next;

    public Ticket(int ticketID, String customerName, String movieName, String seatNumber, LocalDateTime bookingTime) {
        this.ticketID = ticketID;
        this.customerName = customerName;
        this.movieName = movieName;
        this.seatNumber = seatNumber;
        this.bookingTime = bookingTime;
        this.next = null;
    }
}

class TicketReservationSystem {
    private Ticket head = null;
    private Ticket tail = null;

    // Add a new ticket reservation at the end of the circular list
    public void addTicket(int ticketID, String customerName, String movieName, String seatNumber) {
        Ticket newTicket = new Ticket(ticketID, customerName, movieName, seatNumber, LocalDateTime.now());
        if (head == null) {
            head = newTicket;
            tail = newTicket;
            tail.next = head; // Circular link
        } else {
            tail.next = newTicket;
            tail = newTicket;
            tail.next = head; // Update circular link
        }
        System.out.println("Ticket successfully added: " + ticketID);
    }

    // Remove a ticket by Ticket ID
    public void removeTicket(int ticketID) {
        if (head == null) {
            System.out.println("No tickets to remove.");
            return;
        }

        Ticket current = head;
        Ticket previous = null;

        // Traverse the circular list to find the ticket
        do {
            if (current.ticketID == ticketID) {
                if (current == head) { // Removing the head ticket
                    if (head == tail) { // Only one ticket in the list
                        head = null;
                        tail = null;
                    } else {
                        head = head.next;
                        tail.next = head; // Update circular link
                    }
                } else if (current == tail) { // Removing the tail ticket
                    previous.next = head;
                    tail = previous;
                } else { // Removing a ticket in between
                    previous.next = current.next;
                }
                System.out.println("Ticket removed: " + ticketID);
                return;
            }
            previous = current;
            current = current.next;
        } while (current != head);

        System.out.println("Ticket ID not found: " + ticketID);
    }

    // Display all current tickets in the list
    public void displayTickets() {
        if (head == null) {
            System.out.println("No tickets booked.");
            return;
        }

        System.out.println("Current Tickets:");
        Ticket current = head;
        do {
            System.out.println("Ticket ID: " + current.ticketID + ", Customer: " + current.customerName
                    + ", Movie: " + current.movieName + ", Seat: " + current.seatNumber
                    + ", Booking Time: " + current.bookingTime);
            current = current.next;
        } while (current != head);
    }

    // Search for a ticket by Customer Name or Movie Name
    public void searchTicket(String searchKey) {
        if (head == null) {
            System.out.println("No tickets booked.");
            return;
        }

        Ticket current = head;
        boolean found = false;

        do {
            if (current.customerName.equalsIgnoreCase(searchKey) || current.movieName.equalsIgnoreCase(searchKey)) {
                System.out.println("Ticket Found: Ticket ID: " + current.ticketID + ", Customer: " + current.customerName
                        + ", Movie: " + current.movieName + ", Seat: " + current.seatNumber
                        + ", Booking Time: " + current.bookingTime);
                found = true;
            }
            current = current.next;
        } while (current != head);

        if (!found) {
            System.out.println("No ticket found for the search key: " + searchKey);
        }
    }

    // Calculate the total number of booked tickets
    public int totalTickets() {
        if (head == null) {
            return 0;
        }

        int count = 0;
        Ticket current = head;

        do {
            count++;
            current = current.next;
        } while (current != head);

        return count;
    }
}

public class OnlineTicketReservation {
    public static void main(String[] args) {
        TicketReservationSystem system = new TicketReservationSystem();

        // Add some tickets
        system.addTicket(101, "Alice", "Avatar 2", "A10");
        system.addTicket(102, "Bob", "Inception", "B5");
        system.addTicket(103, "Charlie", "Titanic", "C7");

        // Display all tickets
        system.displayTickets();

        // Search for a ticket
        system.searchTicket("Alice");
        system.searchTicket("Inception");

        // Remove a ticket
        system.removeTicket(102);

        // Display all tickets after removal
        system.displayTickets();

        // Calculate the total number of booked tickets
        System.out.println("Total Tickets Booked: " + system.totalTickets());
    }
}
