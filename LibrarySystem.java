/*
 * Java program implementing a basic library management system.
 * 
 * This program allows users to:
 * 1. Add books to a collection.
 * 2. Borrow books from the library.
 * 3. Return books to the library.
 * 4. Exit the program.
 * 
 * The code makes use of:
 * - Loops to repeatedly display the menu and handle multiple user interactions.
 * - Conditional statements (if/switch) for user input decisions.
 * - The 'break' and 'continue' keywords to control loop flow.
 * - Exception handling to ensure inputs are valid integers and within expected ranges.
 * - ArrayLists to dynamically manage a collection of books.
 * - A custom Book class to encapsulate title, author, and quantity information.
 * 
 * Advanced features:
 * - The use of trim() ensures input strings don’t contain unwanted spaces.
 * - equalsIgnoreCase() allows case-insensitive title/author comparison.
 * - Input validation with try-catch prevents the program from crashing when non-integer input is provided.
 * - Loops around user input ensure the program keeps asking until a valid number is given.
 */

import java.util.*;

class Book {
    String title;
    String author;
    int quantity;

    Book(String title, String author, int quantity) {
        this.title = title;
        this.author = author;
        this.quantity = quantity;
    }
}

public class LibrarySystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Book> books = new ArrayList<>();
        ArrayList<String> borrowedBooks = new ArrayList<>();

        while (true) {
            System.out.println("\n==== Library Menu ====");
            System.out.println("1. Add Book");
            System.out.println("2. Borrow Book");
            System.out.println("3. Return Book");
            System.out.println("4. Exit");
            System.out.print("Enter your choice (1-4): ");

            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine());
                if (choice < 1 || choice > 4) {
                    System.out.println("Invalid choice. Please enter a number between 1 and 4.");
                    continue;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                continue;
            }

            switch (choice) {
                case 1:
                    // Add Book functionality
                    System.out.print("Enter book title: ");
                    String title = scanner.nextLine().trim();

                    System.out.print("Enter author name: ");
                    String author = scanner.nextLine().trim();

                    int quantity;
                    while (true) {
                        System.out.print("Enter quantity: ");
                        try {
                            quantity = Integer.parseInt(scanner.nextLine());
                            if (quantity <= 0) {
                                System.out.println("Quantity must be a positive number.");
                                continue;
                            }
                            break;
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid quantity. Please enter a valid number.");
                        }
                    }

                    boolean bookExists = false;
                    for (Book book : books) {
                        if (book.title.equalsIgnoreCase(title) && book.author.equalsIgnoreCase(author)) {
                            book.quantity += quantity;
                            System.out.println("Updated quantity of existing book.");
                            bookExists = true;
                            break;
                        }
                    }

                    if (!bookExists) {
                        books.add(new Book(title, author, quantity));
                        System.out.println("New book added to the library.");
                    }
                    break;
                case 2:
                    // Borrow Book functionality
                    System.out.print("Enter book title to borrow: ");
                    String borrowTitle = scanner.nextLine().trim();

                    int borrowQuantity;
                    while (true) {
                        System.out.print("Enter quantity to borrow: ");
                        try {
                            borrowQuantity = Integer.parseInt(scanner.nextLine());
                            if (borrowQuantity <= 0) {
                                System.out.println("Please enter a positive quantity.");
                                continue;
                            }
                            break;
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid input. Please enter a valid number.");
                        }
                    }

                    boolean found = false;
                    for (Book book : books) {
                        if (book.title.equalsIgnoreCase(borrowTitle)) {
                            found = true;
                            if (book.quantity >= borrowQuantity) {
                                book.quantity -= borrowQuantity;
                                System.out.println("Successfully borrowed " + borrowQuantity + " copy/copies of '" + book.title + "'.");
                            } else {
                                System.out.println("Not enough copies available. Only " + book.quantity + " left in the library.");
                            }
                            break;
                        }
                    }
                    if (!found) {
                        System.out.println("Book not found in the library.");
                    }
                    break;
                case 3:
                    // Return Book functionality
                    System.out.print("Enter book title to return: ");
                    String returnTitle = scanner.nextLine().trim();

                    int returnQuantity;
                    while (true) {
                        System.out.print("Enter quantity to return: ");
                        try {
                            returnQuantity = Integer.parseInt(scanner.nextLine());
                            if (returnQuantity <= 0) {
                                System.out.println("Please enter a positive quantity.");
                                continue;
                            }
                            break;
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid input. Please enter a valid number.");
                        }
                    }

                    boolean returned = false;
                    for (Book book : books) {
                        if (book.title.equalsIgnoreCase(returnTitle)) {
                            book.quantity += returnQuantity;
                            System.out.println("Successfully returned " + returnQuantity + " copy/copies of '" + book.title + "'.");
                            returned = true;
                            break;
                        }
                    }
                    if (!returned) {
                        System.out.println("Book not recognized in the library system. Please check the title.");
                    }
                    break;
                case 4:
                    System.out.println("Exiting the program. Goodbye!");
                    scanner.close();
                    return; // Exit the program
            }
        }
    }
}
