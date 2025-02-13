package SinglyLinkedList;

class StudentNode {
    int rollNumber;
    String name;
    int age;
    String grade;
    StudentNode next;

    public StudentNode(int rollNumber, String name, int age, String grade) {
        this.rollNumber = rollNumber;
        this.name = name;
        this.age = age;
        this.grade = grade;
        this.next = null;
    }
}

public class StudentRecordManagement {
    private StudentNode head;

    // Constructor
    public StudentRecordManagement() {
        this.head = null;
    }

    // Add a student at the beginning, end, or at a specific position
    public void addStudent(int rollNumber, String name, int age, String grade, Integer position) {
        StudentNode newStudent = new StudentNode(rollNumber, name, age, grade);

        if (head == null) { // If the list is empty
            head = newStudent;
        } else if (position == null || position >= countStudents()) { // Add at the end
            StudentNode temp = head;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = newStudent;
        } else if (position == 0) { // Add at the beginning
            newStudent.next = head;
            head = newStudent;
        } else { // Add at a specific position
            StudentNode temp = head;
            for (int i = 0; i < position - 1; i++) {
                temp = temp.next;
            }
            newStudent.next = temp.next;
            temp.next = newStudent;
        }
    }

    // Delete a student by roll number
    public void deleteStudent(int rollNumber) {
        if (head == null) {
            System.out.println("No student records available.");
            return;
        }

        // If the student to be deleted is the head
        if (head.rollNumber == rollNumber) {
            head = head.next;
            System.out.println("Student with roll number " + rollNumber + " has been deleted.");
            return;
        }

        // Search for the student and delete
        StudentNode temp = head;
        while (temp.next != null) {
            if (temp.next.rollNumber == rollNumber) {
                temp.next = temp.next.next;
                System.out.println("Student with roll number " + rollNumber + " has been deleted.");
                return;
            }
            temp = temp.next;
        }

        System.out.println("Student with roll number " + rollNumber + " not found.");
    }

    // Search for a student by roll number
    public void searchStudent(int rollNumber) {
        StudentNode temp = head;
        while (temp != null) {
            if (temp.rollNumber == rollNumber) {
                System.out.println("Student Found: Roll Number: " + temp.rollNumber + ", Name: " + temp.name +
                        ", Age: " + temp.age + ", Grade: " + temp.grade);
                return;
            }
            temp = temp.next;
        }
        System.out.println("Student with roll number " + rollNumber + " not found.");
    }

    // Display all student records
    public void displayAllStudents() {
        if (head == null) {
            System.out.println("No student records available.");
            return;
        }

        StudentNode temp = head;
        System.out.println("Student Records:");
        while (temp != null) {
            System.out.println("Roll Number: " + temp.rollNumber + ", Name: " + temp.name + ", Age: " + temp.age + ", Grade: " + temp.grade);
            temp = temp.next;
        }
    }

    // Update a student's grade based on roll number
    public void updateStudentGrade(int rollNumber, String newGrade) {
        StudentNode temp = head;
        while (temp != null) {
            if (temp.rollNumber == rollNumber) {
                temp.grade = newGrade;
                System.out.println("Grade of student with roll number " + rollNumber + " has been updated to: " + newGrade);
                return;
            }
            temp = temp.next;
        }
        System.out.println("Student with roll number " + rollNumber + " not found.");
    }

    // Count the total number of students
    public int countStudents() {
        int count = 0;
        StudentNode temp = head;
        while (temp != null) {
            count++;
            temp = temp.next;
        }
        return count;
    }

    public static void main(String[] args) {
        StudentRecordManagement srm = new StudentRecordManagement();

        // Add students
        srm.addStudent(1, "Alice", 20, "A", null);
        srm.addStudent(2, "Bob", 21, "B", null);
        srm.addStudent(3, "Charlie", 22, "C", 0);

        // Display all students
        srm.displayAllStudents();

        // Search for a student
        srm.searchStudent(2);

        // Update a student's grade
        srm.updateStudentGrade(1, "A+");

        // Delete a student
        srm.deleteStudent(3);

        // Display all students after deletion
        srm.displayAllStudents();

        // Count total students
        System.out.println("Total Students: " + srm.countStudents());
    }
}

