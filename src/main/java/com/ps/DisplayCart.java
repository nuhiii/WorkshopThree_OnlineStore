package com.ps;

import java.util.ArrayList;
import java.util.Scanner;

public class DisplayCart {
    private ArrayList<Product> cart;

    public DisplayCart(ArrayList<Product> cart) {
        this.cart = cart;
    }

    public void display(Scanner scanner) {
        System.out.println("Shopping Cart Status:");
        if (cart.isEmpty()) {
            System.out.println("Your cart is currently empty.");
        } else {
            double total = 0;
            for (Product product : cart) {
                System.out.println(product);
                total += product.getPrice();
            }
            System.out.printf("Your total is: $ %.2f\n", total);
        }

        while (true) {
            System.out.println("Cart Options:");
            System.out.println("1. Check Out");
            System.out.println("2. Remove Product");
            System.out.println("3. Go Back");
            System.out.print("Please enter your selection: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    checkout();
                    break;
                case 2:
                    removeProductFromCart(scanner);
                case 3:
                    return;
                default:
                    System.out.println("Invalid choice! Try again!");
            }
        }
    }

    private void removeProductFromCart(Scanner scanner) {
        System.out.print("Enter the SKU of the product you wish to remove from cart: ");
        String sku = scanner.nextLine().trim();
        Product product = findBySku(sku);
        if (product != null) {
            cart.remove(product);
            System.out.println("Product has been removed from the cart.");
        } else {
            System.out.println("Sorry, there is no such product in cart.");
        }
    }

    private void checkout() {
        System.out.println("Checkout successful. Thank you for your purchase. Love, NSJ!");
        cart.clear();
    }

    private Product findBySku(String sku) {
        for (Product product : cart) {
            if (product.getSku().equalsIgnoreCase(sku)) {
                return product;
            }
        }
        return null;
    }
}
