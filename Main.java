// Main.java
// Author: Ishanvi Kaur
// Date: 30 November 2024
// Description: Manages products and their operations.

import java.time.LocalDate;
import java.util.*;

public class Main {
    private static final List<Product> products = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\nMenu:");
            System.out.println("1) Create Product");
            System.out.println("2) Create Perishable Product");
            System.out.println("3) Edit Product by SKU");
            System.out.println("4) Delete Product by SKU");
            System.out.println("5) Display Product by SKU");
            System.out.println("6) Display all Products");
            System.out.println("7) Exit");
            System.out.print("Enter your choice: ");

            try {
                choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1 -> createProduct(scanner);
                    case 2 -> createPerishableProduct(scanner);
                    case 3 -> editProduct(scanner);
                    case 4 -> deleteProduct(scanner);
                    case 5 -> displayProduct(scanner);
                    case 6 -> displayAllProducts();
                    case 7 -> System.out.println("Exiting program...");
                    default -> System.out.println("Invalid choice. Try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
                choice = -1;
            }
        } while (choice != 7);
        scanner.close();
    }

    private static void createProduct(Scanner scanner) {
        try {
            System.out.print("Enter SKU (8+ digits): ");
            long sku = Long.parseLong(scanner.nextLine());

            System.out.print("Enter product name: ");
            String name = scanner.nextLine();

            System.out.print("Enter unit cost: ");
            double unitCost = Double.parseDouble(scanner.nextLine());

            System.out.print("Enter sale price: ");
            double salePrice = Double.parseDouble(scanner.nextLine());

            System.out.print("Enter quantity on hand: ");
            int quantityOnHand = Integer.parseInt(scanner.nextLine());

            System.out.print("Enter quantity needed: ");
            int quantityNeeded = Integer.parseInt(scanner.nextLine());

            System.out.print("Enter special instructions: ");
            String specialInstructions = scanner.nextLine();

            products.add(new Product(sku, name, unitCost, salePrice, quantityOnHand, quantityNeeded, specialInstructions));
            System.out.println("Product created successfully.");
        } catch (Exception e) {
            System.out.println("Invalid input. Product creation failed.");
        }
    }

    private static void createPerishableProduct(Scanner scanner) {
        try {
            System.out.print("Enter SKU (8+ digits): ");
            long sku = Long.parseLong(scanner.nextLine());

            System.out.print("Enter product name: ");
            String name = scanner.nextLine();

            System.out.print("Enter unit cost: ");
            double unitCost = Double.parseDouble(scanner.nextLine());

            System.out.print("Enter sale price: ");
            double salePrice = Double.parseDouble(scanner.nextLine());

            System.out.print("Enter quantity on hand: ");
            int quantityOnHand = Integer.parseInt(scanner.nextLine());

            System.out.print("Enter quantity needed: ");
            int quantityNeeded = Integer.parseInt(scanner.nextLine());

            System.out.print("Enter special instructions: ");
            String specialInstructions = scanner.nextLine();

            System.out.print("Enter expiry date (YYYY-MM-DD): ");
            LocalDate expiryDate = LocalDate.parse(scanner.nextLine());

            products.add(new PerishableProduct(sku, name, unitCost, salePrice, quantityOnHand, quantityNeeded, specialInstructions, expiryDate));
            System.out.println("Perishable product created successfully.");
        } catch (Exception e) {
            System.out.println("Invalid input. Product creation failed.");
        }
    }

    private static void editProduct(Scanner scanner) {
        System.out.print("Enter SKU of the product to edit: ");
        long sku = Long.parseLong(scanner.nextLine());

        for (Product product : products) {
            if (product.getSku() == sku) {
                System.out.println("Product found. Enter new details:");
                products.remove(product);
                createProduct(scanner);
                return;
            }
        }
        System.out.println("Product not found.");
    }

    private static void deleteProduct(Scanner scanner) {
        System.out.print("Enter SKU of the product to delete: ");
        long sku = Long.parseLong(scanner.nextLine());

        if (products.removeIf(product -> product.getSku() == sku)) {
            System.out.println("Product deleted successfully.");
        } else {
            System.out.println("Product not found.");
        }
    }

    private static void displayProduct(Scanner scanner) {
        System.out.print("Enter SKU of the product to display: ");
        long sku = Long.parseLong(scanner.nextLine());

        for (Product product : products) {
            if (product.getSku() == sku) {
                System.out.println(product);
                return;
            }
        }
        System.out.println("Product not found.");
    }

    private static void displayAllProducts() {
        if (products.isEmpty()) {
            System.out.println("No products to display.");
        } else {
            products.forEach(System.out::println);
        }
    }
}
