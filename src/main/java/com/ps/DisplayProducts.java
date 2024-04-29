package com.ps;

import java.util.ArrayList;
import java.util.Scanner;

public class DisplayProducts {
    private ArrayList<Product> inventory;
    private ArrayList<Product> cart;

    // construct class with current inventory and cart
    public DisplayProducts(ArrayList<Product> inventory, ArrayList<Product> cart) {
        this.inventory = inventory;
        this.cart = cart;
    }

    public void display(Scanner scanner) {
        // list current inventory
        System.out.println("Inventory:");
        for (Product product : inventory) {
            System.out.println(product);
        }

        // display products menu
        while (true) {
            System.out.println("Product Options:");
            System.out.println("1. Search or Filter Products");
            System.out.println("2. Add to Cart");
            System.out.println("3. Go Back");
            System.out.print("Please enter your selection: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    searchProductList(scanner);
                    break;
                case 2:
                    addToCart(scanner);
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Invalid choice! Try again!");
            }
        }
    }

    // ability to add to cart via sku
    private void addToCart(Scanner scanner) {
        System.out.print("Enter the SKU of the product you wish to add to cart: ");
        String sku = scanner.nextLine().trim();
        Product product = findBySku(sku);
        if (product != null) {
            cart.add(product);
            System.out.println("Product has been added to cart.");
        } else {
            System.out.println("Sorry, there is no such product.");
        }
    }

    private void searchProductList(Scanner scanner) {
        // enter any key term to find associated products
        System.out.print("Enter search key phrase: ");
        String keyword = scanner.nextLine().trim().toLowerCase();
        ArrayList<Product> searchResults = new ArrayList<>();
        for (Product product : inventory) {
            if (product.getProductName().toLowerCase().contains(keyword) ||
                    product.getDepartment().toLowerCase().contains(keyword) ||
                    String.valueOf(product.getPrice()).contains(keyword)) {
                searchResults.add(product);
            }
        }
        // output from search data
        if (!searchResults.isEmpty()) {
            System.out.println("Search results:");
            for (Product product : searchResults) {
                System.out.println(product);
            }
        } else {
            System.out.println("No matching products found.");
        }
    }

    // match product result via sku
    private Product findBySku(String sku) {
        for (Product product : inventory) {
            if (product.getSku().equalsIgnoreCase(sku)) {
                return product;
            }
        }
        return null;
    }
}
