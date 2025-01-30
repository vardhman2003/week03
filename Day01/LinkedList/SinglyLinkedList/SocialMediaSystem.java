package SinglyLinkedList;

import java.util.*;

class FriendNode {
    int friendID;
    FriendNode next;

    public FriendNode(int friendID) {
        this.friendID = friendID;
        this.next = null;
    }
}

class UserNode {
    int userID;
    String name;
    int age;
    FriendNode friends;  // Head of the friend list (linked list)
    UserNode next;

    public UserNode(int userID, String name, int age) {
        this.userID = userID;
        this.name = name;
        this.age = age;
        this.friends = null;
        this.next = null;
    }
}

public class SocialMediaSystem {
    private UserNode head;

    // Constructor
    public SocialMediaSystem() {
        this.head = null;
    }

    // Add a friend connection between two users
    public void addFriend(int userID1, int userID2) {
        UserNode user1 = findUser(userID1);
        UserNode user2 = findUser(userID2);

        if (user1 == null || user2 == null) {
            System.out.println("One or both users not found.");
            return;
        }

        // Add user2 to user1's friend list
        addFriendToUser(user1, userID2);
        // Add user1 to user2's friend list
        addFriendToUser(user2, userID1);

        System.out.println("Friendship between User " + userID1 + " and User " + userID2 + " established.");
    }

    private void addFriendToUser(UserNode user, int friendID) {
        FriendNode newFriend = new FriendNode(friendID);
        newFriend.next = user.friends;
        user.friends = newFriend;
    }

    // Remove a friend connection
    public void removeFriend(int userID1, int userID2) {
        UserNode user1 = findUser(userID1);
        UserNode user2 = findUser(userID2);

        if (user1 == null || user2 == null) {
            System.out.println("One or both users not found.");
            return;
        }

        // Remove user2 from user1's friend list
        removeFriendFromUser(user1, userID2);
        // Remove user1 from user2's friend list
        removeFriendFromUser(user2, userID1);

        System.out.println("Friendship between User " + userID1 + " and User " + userID2 + " removed.");
    }

    private void removeFriendFromUser(UserNode user, int friendID) {
        FriendNode prev = null;
        FriendNode current = user.friends;

        while (current != null && current.friendID != friendID) {
            prev = current;
            current = current.next;
        }

        if (current != null) {
            if (prev == null) {
                user.friends = current.next;  // Removing the first node
            } else {
                prev.next = current.next;  // Removing a middle or last node
            }
        } else {
            System.out.println("Friend not found.");
        }
    }

    // Find mutual friends between two users
    public void findMutualFriends(int userID1, int userID2) {
        UserNode user1 = findUser(userID1);
        UserNode user2 = findUser(userID2);

        if (user1 == null || user2 == null) {
            System.out.println("One or both users not found.");
            return;
        }

        Set<Integer> mutualFriends = new HashSet<>();
        FriendNode friends1 = user1.friends;
        FriendNode friends2 = user2.friends;

        // Traverse both friend lists and find mutual friends
        while (friends1 != null) {
            while (friends2 != null) {
                if (friends1.friendID == friends2.friendID) {
                    mutualFriends.add(friends1.friendID);
                }
                friends2 = friends2.next;
            }
            friends1 = friends1.next;
            friends2 = user2.friends; // Reset for the next user1 friend
        }

        if (mutualFriends.isEmpty()) {
            System.out.println("No mutual friends found.");
        } else {
            System.out.println("Mutual friends: " + mutualFriends);
        }
    }

    // Display all friends of a specific user
    public void displayFriends(int userID) {
        UserNode user = findUser(userID);
        if (user == null) {
            System.out.println("User not found.");
            return;
        }

        System.out.println("Friends of User " + userID + ":");
        FriendNode friend = user.friends;
        while (friend != null) {
            System.out.println("Friend ID: " + friend.friendID);
            friend = friend.next;
        }
    }

    // Search for a user by Name or User ID
    public void searchUser(String name) {
        UserNode temp = head;
        while (temp != null) {
            if (temp.name.equalsIgnoreCase(name)) {
                System.out.println("User found: " + temp.name + " (ID: " + temp.userID + ", Age: " + temp.age + ")");
                return;
            }
            temp = temp.next;
        }
        System.out.println("User with name " + name + " not found.");
    }

    public void searchUser(int userID) {
        UserNode user = findUser(userID);
        if (user != null) {
            System.out.println("User found: " + user.name + " (ID: " + user.userID + ", Age: " + user.age + ")");
        } else {
            System.out.println("User with ID " + userID + " not found.");
        }
    }

    // Count the number of friends for each user
    public void countFriends(int userID) {
        UserNode user = findUser(userID);
        if (user != null) {
            int count = 0;
            FriendNode friend = user.friends;
            while (friend != null) {
                count++;
                friend = friend.next;
            }
            System.out.println("User " + userID + " has " + count + " friends.");
        } else {
            System.out.println("User with ID " + userID + " not found.");
        }
    }

    // Helper method to find a user by User ID
    private UserNode findUser(int userID) {
        UserNode temp = head;
        while (temp != null) {
            if (temp.userID == userID) {
                return temp;
            }
            temp = temp.next;
        }
        return null;
    }

    // Add a new user to the system
    public void addUser(int userID, String name, int age) {
        UserNode newUser = new UserNode(userID, name, age);
        if (head == null) {
            head = newUser;
        } else {
            UserNode temp = head;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = newUser;
        }
        System.out.println("User " + name + " added.");
    }

    // Display all users
    public void displayAllUsers() {
        if (head == null) {
            System.out.println("No users available.");
            return;
        }
        UserNode temp = head;
        while (temp != null) {
            System.out.println("User ID: " + temp.userID + ", Name: " + temp.name + ", Age: " + temp.age);
            temp = temp.next;
        }
    }

    public static void main(String[] args) {
        SocialMediaSystem sms = new SocialMediaSystem();

        // Adding users
        sms.addUser(1, "Alice", 25);
        sms.addUser(2, "Bob", 30);
        sms.addUser(3, "Charlie", 28);

        // Add friend connections
        sms.addFriend(1, 2);
        sms.addFriend(1, 3);
        sms.addFriend(2, 3);

        // Display all users and their friends
        sms.displayAllUsers();
        sms.displayFriends(1);

        // Search users by name and user ID
        sms.searchUser("Alice");
        sms.searchUser(2);

        // Count the number of friends for a user
        sms.countFriends(1);

        // Find mutual friends between two users
        sms.findMutualFriends(1, 2);

        // Remove a friend connection
        sms.removeFriend(1, 2);
        sms.displayFriends(1);
    }
}

