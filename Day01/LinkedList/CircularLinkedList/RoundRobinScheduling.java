package CircularLinkedList;

class Process {
    int pid;          // Process ID
    int burstTime;    // Burst Time of the process
    int priority;     // Priority of the process
    Process next;     // Pointer to the next process

    public Process(int pid, int burstTime, int priority) {
        this.pid = pid;
        this.burstTime = burstTime;
        this.priority = priority;
        this.next = null;
    }
}

class CircularLinkedList {
    private Process head = null;
    private Process tail = null;

    // Add a new process at the end of the circular linked list
    public void addProcess(int pid, int burstTime, int priority) {
        Process newProcess = new Process(pid, burstTime, priority);
        if (head == null) {
            head = newProcess;
            tail = newProcess;
            tail.next = head; // Create the circular connection
        } else {
            tail.next = newProcess;
            tail = newProcess;
            tail.next = head; // Update circular connection
        }
    }

    // Remove a process by Process ID after its execution
    public void removeProcess(int pid) {
        if (head == null) {
            System.out.println("No processes to remove.");
            return;
        }

        Process current = head;
        Process previous = null;

        // Iterate through the circular list to find the process
        do {
            if (current.pid == pid) {
                if (current == head) { // If removing the head process
                    if (head == tail) { // If only one process is left
                        head = null;
                        tail = null;
                    } else {
                        head = head.next;
                        tail.next = head; // Update circular link
                    }
                } else if (current == tail) { // If removing the tail process
                    previous.next = head;
                    tail = previous;
                } else { // Removing a process in between
                    previous.next = current.next;
                }
                System.out.println("Process " + pid + " has been removed.");
                return;
            }
            previous = current;
            current = current.next;
        } while (current != head);

        System.out.println("Process " + pid + " not found.");
    }

    // Simulate the Round Robin Scheduling
    public void roundRobin(int timeQuantum) {
        if (head == null) {
            System.out.println("No processes to execute.");
            return;
        }

        int totalTime = 0;
        int totalProcesses = 0;
        double totalWaitingTime = 0;
        double totalTurnaroundTime = 0;

        // Count total processes
        Process current = head;
        do {
            totalProcesses++;
            current = current.next;
        } while (current != head);

        System.out.println("Starting Round Robin Scheduling...");

        // Round Robin execution
        while (head != null) {
            current = head;
            do {
                if (current.burstTime > 0) {
                    int executionTime = Math.min(current.burstTime, timeQuantum);
                    System.out.println("Executing Process " + current.pid + " for " + executionTime + " units.");
                    current.burstTime -= executionTime;
                    totalTime += executionTime;

                    if (current.burstTime == 0) {
                        System.out.println("Process " + current.pid + " completed execution.");
                        totalWaitingTime += totalTime - executionTime;
                        totalTurnaroundTime += totalTime;
                        int completedPid = current.pid;
                        current = current.next;
                        removeProcess(completedPid); // Safely remove the completed process
                        if (head == null) break; // Exit if all processes are removed
                    } else {
                        current = current.next;
                    }
                } else {
                    current = current.next;
                }
            } while (current != head);

            displayProcesses();
        }

        // Display average waiting and turnaround times
        System.out.println("Average Waiting Time: " + (totalWaitingTime / totalProcesses));
        System.out.println("Average Turnaround Time: " + (totalTurnaroundTime / totalProcesses));
    }

    // Display the list of processes
    public void displayProcesses() {
        if (head == null) {
            System.out.println("No processes in the queue.");
            return;
        }

        System.out.println("Current Processes in the Circular Queue:");
        Process current = head;
        do {
            System.out.println("Process ID: " + current.pid + ", Burst Time: " + current.burstTime + ", Priority: " + current.priority);
            current = current.next;
        } while (current != head);
    }
}

public class RoundRobinScheduling {
    public static void main(String[] args) {
        CircularLinkedList processList = new CircularLinkedList();

        // Adding processes to the circular linked list
        processList.addProcess(1, 10, 1);
        processList.addProcess(2, 5, 2);
        processList.addProcess(3, 8, 1);

        // Display the initial list of processes
        processList.displayProcesses();

        // Perform Round Robin Scheduling with a fixed time quantum
        int timeQuantum = 3;
        processList.roundRobin(timeQuantum);
    }
}
