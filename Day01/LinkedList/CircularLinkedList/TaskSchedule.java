package CircularLinkedList;

class Task {
    int taskId;
    String taskName;
    int priority;
    String dueDate;
    Task next;

    public Task(int taskId, String taskName, int priority, String dueDate) {
        this.taskId = taskId;
        this.taskName = taskName;
        this.priority = priority;
        this.dueDate = dueDate;
        this.next = null;
    }

    @Override
    public String toString() {
        return "Task ID: " + taskId + ", Task Name: " + taskName +
                ", Priority: " + priority + ", Due Date: " + dueDate;
    }
}

class TaskScheduler {
    private Task head;
    private Task current;

    // Add a task at the beginning
    public void addTaskAtBeginning(int taskId, String taskName, int priority, String dueDate) {
        Task newTask = new Task(taskId, taskName, priority, dueDate);
        if (head == null) {
            head = newTask;
            head.next = head; // Circular reference
            current = head;
        } else {
            Task temp = head;
            while (temp.next != head) {
                temp = temp.next;
            }
            newTask.next = head;
            temp.next = newTask;
            head = newTask;
        }
    }

    // Add a task at the end
    public void addTaskAtEnd(int taskId, String taskName, int priority, String dueDate) {
        Task newTask = new Task(taskId, taskName, priority, dueDate);
        if (head == null) {
            head = newTask;
            head.next = head; // Circular reference
            current = head;
        } else {
            Task temp = head;
            while (temp.next != head) {
                temp = temp.next;
            }
            temp.next = newTask;
            newTask.next = head;
        }
    }

    // Add a task at a specific position
    public void addTaskAtPosition(int position, int taskId, String taskName, int priority, String dueDate) {
        Task newTask = new Task(taskId, taskName, priority, dueDate);
        if (position <= 0 || head == null) {
            addTaskAtBeginning(taskId, taskName, priority, dueDate);
        } else {
            Task temp = head;
            int count = 1;
            while (count < position - 1 && temp.next != head) {
                temp = temp.next;
                count++;
            }
            newTask.next = temp.next;
            temp.next = newTask;
        }
    }

    // Remove a task by Task ID
    public void removeTask(int taskId) {
        if (head == null) {
            System.out.println("No tasks to remove.");
            return;
        }

        Task temp = head, prev = null;

        // If head node holds the task to be deleted
        if (temp.taskId == taskId) {
            if (temp.next == head) { // Only one node in the list
                head = null;
                current = null;
                return;
            }

            // More than one node
            while (temp.next != head) {
                temp = temp.next;
            }
            temp.next = head.next;
            head = head.next;
            current = head;
            return;
        }

        // Search for the task
        while (temp.next != head && temp.taskId != taskId) {
            prev = temp;
            temp = temp.next;
        }

        // If task was not found
        if (temp.taskId != taskId) {
            System.out.println("Task not found.");
            return;
        }

        // Remove the task
        prev.next = temp.next;
    }

    // View the current task and move to the next task
    public void viewCurrentTask() {
        if (current == null) {
            System.out.println("No tasks available.");
            return;
        }
        System.out.println("Current Task: " + current);
        current = current.next;
    }

    // Display all tasks in the list
    public void displayAllTasks() {
        if (head == null) {
            System.out.println("No tasks available.");
            return;
        }
        Task temp = head;
        System.out.println("Task List:");
        do {
            System.out.println(temp);
            temp = temp.next;
        } while (temp != head);
    }

    // Search for a task by Priority
    public void searchTaskByPriority(int priority) {
        if (head == null) {
            System.out.println("No tasks available.");
            return;
        }
        Task temp = head;
        boolean found = false;
        System.out.println("Tasks with Priority " + priority + ":");
        do {
            if (temp.priority == priority) {
                System.out.println(temp);
                found = true;
            }
            temp = temp.next;
        } while (temp != head);

        if (!found) {
            System.out.println("No tasks found with the given priority.");
        }
    }
}

public class TaskSchedule {
    public static void main(String[] args) {
        TaskScheduler scheduler = new TaskScheduler();

        // Add tasks
        scheduler.addTaskAtEnd(1, "Task A", 1, "2025-02-01");
        scheduler.addTaskAtEnd(2, "Task B", 2, "2025-02-02");
        scheduler.addTaskAtBeginning(3, "Task C", 1, "2025-02-03");
        scheduler.addTaskAtPosition(2, 4, "Task D", 3, "2025-02-04");

        // Display all tasks
        scheduler.displayAllTasks();

        // View and move to the next task
        scheduler.viewCurrentTask(); // Current task: Task C
        scheduler.viewCurrentTask(); // Current task: Task D

        // Remove a task
        scheduler.removeTask(2); // Remove Task B
        scheduler.displayAllTasks();

        // Search for tasks by priority
        scheduler.searchTaskByPriority(1);
    }
}
