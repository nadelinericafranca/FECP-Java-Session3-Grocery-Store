package org.example;

import java.util.*;

public class Main {
    static Map<String, Integer> products = new HashMap<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String name;

        while (true) {
            System.out.println("\n--- Grocery Inventory Menu ---");
            System.out.println("1. View Inventory");
            System.out.println("2. Add Product");
            System.out.println("3. Check Product");
            System.out.println("4. Update Stock");
            System.out.println("5. Remove Product");
            System.out.println("6. Exit\n");
            System.out.print("Choose an option: ");

            try {
                int choice = Integer.parseInt(scanner.nextLine());

                switch(choice) {
                    case 1: // View Inventory
                        if (products.isEmpty()) {
                            System.out.println("There are no products in the inventory.");
                            break;
                        };
                        viewInventory();
                        break;

                    case 2: // Add Product
                        System.out.print("Enter product name: ");
                        name = getProductName(scanner);

                        System.out.println(addProduct(name, scanner));
                        break;
                    case 3: // Check Product
                        if (products.isEmpty()) {
                            System.out.println("There are no products in the inventory.");
                            break;
                        };

                        System.out.print("Enter product name to check: ");
                        name = getProductName(scanner);

                        System.out.println(checkProduct(name));
                        break;
                    case 4: // Update Stock
                        if (products.isEmpty()) {
                            System.out.println("There are no products in the inventory.");
                            break;
                        }

                        System.out.print("Enter product name to update: ");
                        name = getProductName(scanner);

                        System.out.println(updateProduct(name, scanner));
                        break;
                    case 5: // Remove Product
                        if (products.isEmpty()) {
                            System.out.print("There are no products in the inventory.");
                            break;
                        }

                        System.out.print("Enter product name to remove: ");
                        name = getProductName(scanner);

                        System.out.println(removeProduct(name));
                        break;
                    case 6: // Exit
                        System.out.println("Exiting system...");
                        System.exit(0);
                    default:
                        System.out.println("Invalid input. Please enter a number between 1 and 6.");
                        break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number between 1 and 6.\n");
            }
        }
    }

    public static void viewInventory() {
        System.out.println("\nCurrent Inventory:");
        for (Map.Entry<String, Integer> entry : products.entrySet()) {
            System.out.println(toProperCase(entry.getKey()) + " - " + entry.getValue() + " pcs");
        }
    }

    public static String addProduct(String name, Scanner scanner) {
        // Validate if key exists already
        boolean productExists = products.containsKey(name);

        if (productExists) {
            return "Product already exists.";
        } else {
            System.out.print("Enter quantity: ");
            int quantity = getQuantity(scanner);

            products.put(name, quantity);
            return "Product Added!";
        }
    }

    public static String checkProduct(String name) {
        boolean productExists = products.containsKey(name);
        int stock = 0;

        if (productExists) {
            for (Map.Entry<String, Integer> entry : products.entrySet()) {
                if (Objects.equals(entry.getKey(), name)) {
                    stock = entry.getValue();
                }
            }
            return toProperCase(name) + " is in stock: " + stock;
        } else {
            return toProperCase(name) + " not found.";
        }
    }

    public static String updateProduct(String name, Scanner scanner) {
        boolean productExists = products.containsKey(name);

        if (productExists) {
            System.out.print("Enter new stock quantity: ");
            int newQuantity = getQuantity(scanner);

            products.replace(name, newQuantity);
            return "Stock updated!";
        } else {
            return "Product not found.";
        }
    }

    public static String removeProduct(String name) {
        boolean productExists = products.containsKey(name);

        if (productExists) {
            products.remove(name);
            return "Product removed.";
        } else {
            return "Product not found.";
        }
    }

    public static int getQuantity(Scanner scanner) {
        while (true) {
            try {
                int input = Integer.parseInt(scanner.nextLine());

                if (input <= 0 ) {
                    System.out.println("Quantity must be a positive number.");
                }

                return input;

            } catch (NumberFormatException e) {
                System.out.println("Quantity must be an integer.");
            }
        }
    }

    public static String getProductName(Scanner scanner) {
        while(true) {
            String input = scanner.nextLine().trim();

            if (input.isEmpty()) {
                System.out.println("Product name cannot be empty.");
            } else {
                return input.toLowerCase();
            }
        }
    }

    // Helper method to capitalize first letter of product name for printing
    public static String toProperCase(String name) {
        return name.substring(0, 1).toUpperCase() + name.substring(1);  // convert first letter to uppercase
    }
}
