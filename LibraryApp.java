/**
 * Generic Library Catalog Application
 * -----------------------------------
 * This Java program demonstrates the use of generic classes and methods to build a flexible library catalog
 * system that can handle multiple types of items such as books, DVDs, and magazines. The program uses generics
 * to ensure type safety and reusability.
 * 
 * Flow of Logic:
 * 1. The `LibraryItem` class represents a generic library item with basic attributes like title, author, and itemID.
 * 2. The `Catalog` class is a generic container that stores and manages items of any type extending `LibraryItem`.
 * 3. The catalog provides methods to add, remove, and retrieve items.
 * 4. Error handling ensures that invalid operations (like removing a non-existent item) are managed gracefully.
 * 5. The `LibraryApp` class provides a command-line interface that allows users to interact with the catalog:
 *    - Add new library items
 *    - Remove existing items
 *    - View all items currently in the catalog
 * 6. The system can easily be extended to handle new item types by simply defining new subclasses of `LibraryItem`.
 * 
 * This design showcases the power of Java generics, encapsulation, and object-oriented design principles.
 */

import java.util.*;

// Generic LibraryItem class representing any type of library material
class LibraryItem {
    private String title;
    private String author;
    private String itemID;

    public LibraryItem(String title, String author, String itemID) {
        this.title = title;
        this.author = author;
        this.itemID = itemID;
    }

    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public String getItemID() { return itemID; }

    @Override
    public String toString() {
        return String.format("Item ID: %s | Title: %s | Author: %s", itemID, title, author);
    }
}

// Generic Catalog class using Java Generics
class Catalog<T extends LibraryItem> {
    private Map<String, T> items = new HashMap<>();

    public void addItem(T item) {
        items.put(item.getItemID(), item);
        System.out.println("Item added successfully.");
    }

    public void removeItem(String itemID) {
        if (items.containsKey(itemID)) {
            items.remove(itemID);
            System.out.println("Item removed successfully.");
        } else {
            System.out.println("Error: Item with ID '" + itemID + "' not found.");
        }
    }

    public T getItem(String itemID) {
        return items.get(itemID);
    }

    public void displayAllItems() {
        if (items.isEmpty()) {
            System.out.println("Catalog is empty.");
        } else {
            System.out.println("\n--- Library Catalog ---");
            for (T item : items.values()) {
                System.out.println(item);
            }
        }
    }
}

// Example subclass representing a Book
class Book extends LibraryItem {
    public Book(String title, String author, String itemID) {
        super(title, author, itemID);
    }
}

// Example subclass representing a DVD
class DVD extends LibraryItem {
    public DVD(String title, String author, String itemID) {
        super(title, author, itemID);
    }
}

// Command-Line Interface for the Library Catalog
public class LibraryApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Catalog<LibraryItem> catalog = new Catalog<>();
        boolean running = true;

        System.out.println("Welcome to the Generic Library Catalog!");

        while (running) {
            System.out.println("\nChoose an option:");
            System.out.println("1. Add Item");
            System.out.println("2. Remove Item");
            System.out.println("3. View Catalog");
            System.out.println("4. Exit");
            System.out.print("Enter choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter item type (Book/DVD): ");
                    String type = scanner.nextLine();
                    System.out.print("Enter title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter author: ");
                    String author = scanner.nextLine();
                    System.out.print("Enter item ID: ");
                    String itemID = scanner.nextLine();

                    if (type.equalsIgnoreCase("book")) {
                        catalog.addItem(new Book(title, author, itemID));
                    } else if (type.equalsIgnoreCase("dvd")) {
                        catalog.addItem(new DVD(title, author, itemID));
                    } else {
                        System.out.println("Unsupported item type.");
                    }
                    break;

                case 2:
                    System.out.print("Enter item ID to remove: ");
                    String removeID = scanner.nextLine();
                    catalog.removeItem(removeID);
                    break;

                case 3:
                    catalog.displayAllItems();
                    break;

                case 4:
                    running = false;
                    System.out.println("Exiting system. Goodbye!");
                    break;

                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
        scanner.close();
    }
}

