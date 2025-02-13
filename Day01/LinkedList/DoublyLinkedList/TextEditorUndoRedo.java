package DoublyLinkedList;

class TextState {
    String content;
    TextState prev, next;

    public TextState(String content) {
        this.content = content;
        this.prev = null;
        this.next = null;
    }
}

class TextEditor {
    private TextState head, tail, current;
    private int maxHistorySize;
    private int currentSize;

    public TextEditor(int maxHistorySize) {
        this.head = null;
        this.tail = null;
        this.current = null;
        this.maxHistorySize = maxHistorySize;
        this.currentSize = 0;
    }

    // Add a new text state
    public void addState(String content) {
        TextState newState = new TextState(content);

        if (current == null) {
            // First state
            head = newState;
            tail = newState;
            current = newState;
        } else {
            // Clear redo history
            current.next = null;
            tail = current;

            // Add new state to the end
            tail.next = newState;
            newState.prev = tail;
            tail = newState;
            current = newState;
        }

        currentSize++;

        // Ensure history size does not exceed the limit
        if (currentSize > maxHistorySize) {
            head = head.next;
            head.prev = null;
            currentSize--;
        }
    }

    // Undo functionality
    public void undo() {
        if (current != null && current.prev != null) {
            current = current.prev;
            System.out.println("Undo: " + current.content);
        } else {
            System.out.println("Nothing to undo.");
        }
    }

    // Redo functionality
    public void redo() {
        if (current != null && current.next != null) {
            current = current.next;
            System.out.println("Redo: " + current.content);
        } else {
            System.out.println("Nothing to redo.");
        }
    }

    // Display the current state
    public void displayCurrentState() {
        if (current != null) {
            System.out.println("Current State: " + current.content);
        } else {
            System.out.println("No current state.");
        }
    }
}

public class TextEditorUndoRedo {
    public static void main(String[] args) {
        TextEditor editor = new TextEditor(10); // Limit to last 10 states

        // Simulate typing actions
        editor.addState("Hello");
        editor.addState("Hello World");
        editor.addState("Hello World!");
        editor.displayCurrentState(); // Should display: Hello World!

        // Perform undo operations
        editor.undo(); // Should display: Hello World
        editor.undo(); // Should display: Hello
        editor.undo(); // Should display: Nothing to undo.

        // Perform redo operations
        editor.redo(); // Should display: Hello World
        editor.redo(); // Should display: Hello World!
        editor.redo(); // Should display: Nothing to redo.

        // Add more states
        editor.addState("Hello World!!");
        editor.addState("Hello World!!!");
        editor.displayCurrentState(); // Should display: Hello World!!!

        // Undo after adding new states
        editor.undo(); // Should display: Hello World!!
        editor.undo(); // Should display: Hello World!
    }
}
