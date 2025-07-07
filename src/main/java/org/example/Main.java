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
            System.out.println(entry.getKey() + " - " + entry.getValue() + " pcs");
        }
    }

    public static String addProduct(String name, Scanner scanner) {
        // Validate if key exists already
        boolean productExists = products.containsKey(name.trim());

        if (productExists) {
            return "Product already exists.";
        } else {
            System.out.print("Enter quantity: ");
            int quantity = scanner.nextInt();
            scanner.nextLine();

            if (quantity <= 0 ) {
                return "Quantity must be a positive number.";
            }


            products.put(name, quantity);
            return "Product Added!";
        }
    }

    public static String checkProduct(String name) {
        boolean productExists = products.containsKey(name.trim());
        int stock = 0;

        if (productExists) {
            for (Map.Entry<String, Integer> entry : products.entrySet()) {
                if (Objects.equals(entry.getKey(), name)) {
                    stock = entry.getValue();
                }
            }
            return name + " is in stock: " + stock;
        } else {
            return name + " is not in stock.";
        }
    }

    public static String updateProduct(String name, Scanner scanner) {
        boolean productExists = products.containsKey(name.trim());

        if (productExists) {
            System.out.print("Enter new stock quantity: ");
            int newQuantity = scanner.nextInt();
            scanner.nextLine();

            if (newQuantity <= 0 ) {
                return "Quantity must be a positive number.";
            }

            products.replace(name.trim(), newQuantity);
            return "Stock updated!";
        } else {
            return "Product does not exist.";
        }
    }

    public static String removeProduct(String name) {
        boolean productExists = products.containsKey(name.trim());

        if (productExists) {
            products.remove(name);
            return "Product removed.";
        } else {
            return "Product does not exist.";
        }
    }

    public static String getProductName(Scanner scanner) {
        String input = scanner.nextLine();

        while(true) {
            if (input.isEmpty()) {
                System.out.println("Product name cannot be empty.");
            } else {
                return input;
            }
        }
    }
}
