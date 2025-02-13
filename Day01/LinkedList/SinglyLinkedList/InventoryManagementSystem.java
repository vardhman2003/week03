package SinglyLinkedList;

class ItemNode {
    String itemName;
    int itemID;
    int quantity;
    double price;
    ItemNode next;

    public ItemNode(String itemName, int itemID, int quantity, double price) {
        this.itemName = itemName;
        this.itemID = itemID;
        this.quantity = quantity;
        this.price = price;
        this.next = null;
    }
}

public class InventoryManagementSystem {
    private ItemNode head;

    // Constructor
    public InventoryManagementSystem() {
        this.head = null;
    }

    // Add an item at the beginning, end, or at a specific position
    public void addItem(String itemName, int itemID, int quantity, double price, Integer position) {
        ItemNode newItem = new ItemNode(itemName, itemID, quantity, price);

        if (head == null) { // If the list is empty
            head = newItem;
        } else if (position == null || position >= countItems()) { // Add at the end
            ItemNode temp = head;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = newItem;
        } else if (position == 0) { // Add at the beginning
            newItem.next = head;
            head = newItem;
        } else { // Add at a specific position
            ItemNode temp = head;
            for (int i = 0; i < position - 1; i++) {
                temp = temp.next;
            }
            newItem.next = temp.next;
            temp.next = newItem;
        }
    }

    // Remove an item based on Item ID
    public void removeItem(int itemID) {
        if (head == null) {
            System.out.println("Inventory is empty.");
            return;
        }

        // If the item to be deleted is the head
        if (head.itemID == itemID) {
            head = head.next;
            System.out.println("Item with ID " + itemID + " has been removed.");
            return;
        }

        // Search for the item and delete
        ItemNode temp = head;
        while (temp.next != null) {
            if (temp.next.itemID == itemID) {
                temp.next = temp.next.next;
                System.out.println("Item with ID " + itemID + " has been removed.");
                return;
            }
            temp = temp.next;
        }

        System.out.println("Item with ID " + itemID + " not found.");
    }

    // Update the quantity of an item by Item ID
    public void updateItemQuantity(int itemID, int newQuantity) {
        ItemNode temp = head;
        while (temp != null) {
            if (temp.itemID == itemID) {
                temp.quantity = newQuantity;
                System.out.println("Item with ID " + itemID + " quantity updated to: " + newQuantity);
                return;
            }
            temp = temp.next;
        }
        System.out.println("Item with ID " + itemID + " not found.");
    }

    // Search for an item by Item ID or Item Name
    public void searchItem(int itemID) {
        ItemNode temp = head;
        while (temp != null) {
            if (temp.itemID == itemID) {
                System.out.println("Item Found: Name: " + temp.itemName + ", ID: " + temp.itemID +
                        ", Quantity: " + temp.quantity + ", Price: " + temp.price);
                return;
            }
            temp = temp.next;
        }
        System.out.println("Item with ID " + itemID + " not found.");
    }

    public void searchItem(String itemName) {
        ItemNode temp = head;
        while (temp != null) {
            if (temp.itemName.equalsIgnoreCase(itemName)) {
                System.out.println("Item Found: Name: " + temp.itemName + ", ID: " + temp.itemID +
                        ", Quantity: " + temp.quantity + ", Price: " + temp.price);
                return;
            }
            temp = temp.next;
        }
        System.out.println("Item with name " + itemName + " not found.");
    }

    // Calculate and display the total value of inventory
    public void calculateTotalValue() {
        double totalValue = 0;
        ItemNode temp = head;
        while (temp != null) {
            totalValue += temp.quantity * temp.price;
            temp = temp.next;
        }
        System.out.println("Total value of inventory: " + totalValue);
    }

    // Sort the inventory based on Item Name or Price
    public void sortInventory(String sortBy, boolean ascending) {
        if (head == null) {
            System.out.println("Inventory is empty.");
            return;
        }

        // Sorting using Merge Sort (ascending or descending based on user preference)
        head = mergeSort(head, sortBy, ascending);
        System.out.println("Inventory sorted by " + sortBy + " in " + (ascending ? "ascending" : "descending") + " order.");
    }

    // Merge Sort Helper Methods
    private ItemNode mergeSort(ItemNode node, String sortBy, boolean ascending) {
        if (node == null || node.next == null) {
            return node;
        }

        ItemNode middle = getMiddle(node);
        ItemNode nextOfMiddle = middle.next;
        middle.next = null;

        ItemNode left = mergeSort(node, sortBy, ascending);
        ItemNode right = mergeSort(nextOfMiddle, sortBy, ascending);

        return merge(left, right, sortBy, ascending);
    }

    private ItemNode merge(ItemNode left, ItemNode right, String sortBy, boolean ascending) {
        if (left == null) {
            return right;
        }
        if (right == null) {
            return left;
        }

        int comparison = compare(left, right, sortBy);

        if ((ascending && comparison <= 0) || (!ascending && comparison >= 0)) {
            left.next = merge(left.next, right, sortBy, ascending);
            return left;
        } else {
            right.next = merge(left, right.next, sortBy, ascending);
            return right;
        }
    }

    private int compare(ItemNode left, ItemNode right, String sortBy) {
        if (sortBy.equalsIgnoreCase("name")) {
            return left.itemName.compareToIgnoreCase(right.itemName);
        } else if (sortBy.equalsIgnoreCase("price")) {
            return Double.compare(left.price, right.price);
        } else {
            return 0;
        }
    }

    private ItemNode getMiddle(ItemNode node) {
        if (node == null) {
            return null;
        }

        ItemNode slow = node;
        ItemNode fast = node.next;

        while (fast != null) {
            fast = fast.next;
            if (fast != null) {
                slow = slow.next;
                fast = fast.next;
            }
        }
        return slow;
    }

    // Display all inventory items
    public void displayAllItems() {
        if (head == null) {
            System.out.println("Inventory is empty.");
            return;
        }

        ItemNode temp = head;
        System.out.println("Inventory Items:");
        while (temp != null) {
            System.out.println("Name: " + temp.itemName + ", ID: " + temp.itemID +
                    ", Quantity: " + temp.quantity + ", Price: " + temp.price);
            temp = temp.next;
        }
    }

    // Count the total number of items in inventory
    public int countItems() {
        int count = 0;
        ItemNode temp = head;
        while (temp != null) {
            count++;
            temp = temp.next;
        }
        return count;
    }

    public static void main(String[] args) {
        InventoryManagementSystem ims = new InventoryManagementSystem();

        // Add items
        ims.addItem("Laptop", 101, 5, 1000.00, null);
        ims.addItem("Smartphone", 102, 10, 500.00, null);
        ims.addItem("Tablet", 103, 7, 300.00, null);

        // Display all items
        ims.displayAllItems();

        // Search for an item
        ims.searchItem(102);

        // Update item quantity
        ims.updateItemQuantity(101, 8);

        // Remove an item
        ims.removeItem(103);

        // Calculate total inventory value
        ims.calculateTotalValue();

        // Sort inventory by Item Name
        ims.sortInventory("name", true);
        ims.displayAllItems();

        // Sort inventory by Price in descending order
        ims.sortInventory("price", false);
        ims.displayAllItems();

        // Count total items
        System.out.println("Total Items: " + ims.countItems());
    }
}

