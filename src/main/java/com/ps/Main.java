package com.ps;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    // list all items in store inventory
    private static ArrayList<Product> inventory = new ArrayList<>();
    // list items given the current cart
    private static ArrayList<Product> cart = new ArrayList<>();
    // use scanner for input throughout code
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        loadInventory("products.csv");

        while (true) {
            // nsj store brand name
            // using initials of each team member
            System.out.println("Welcome to the NSJ Store!");
            System.out.println("1. Display Products");
            System.out.println("2. Display Cart");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            // home screen menu
            switch (choice) {
                case 1:
                    DisplayProducts displayProducts = new DisplayProducts(inventory, cart);
                    displayProducts.display(scanner);
                    break;
                case 2:
                    //displayCart();
                    break;
                case 3:
                    System.out.println("Thank you for shopping with us at NSJ!");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice! Try again!");
            }
        }
    }

    // read data from file and enter each individual item into Product class
    // and store into inventory variable for use in current class
    private static void loadInventory(String filePath) {
        try (BufferedReader buffReader = new BufferedReader(new FileReader(filePath))) {
            // skip first line of csv file (header stuff)
            buffReader.readLine();
            String input;
            while ((input = buffReader.readLine()) != null) {
                String[] data = input.split("\\|");
                String sku = data[0];
                String productName = data[1];
                double price = Double.parseDouble(data[2]);
                String department = data[3];
                inventory.add(new Product(sku, productName, price, department));
            }
        } catch (IOException e) {
            // error handling for file
            e.printStackTrace();
        }
    }

}