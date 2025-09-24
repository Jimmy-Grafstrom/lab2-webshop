package se.iths.jimmy;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Webshop {

    private List<Product> productList;
    private List<Product> cart;

    private Furniture table = new Furniture("705.632.37", "SKANSNÄS", 7995, "SKANSNÄS table offers a modern flair inspired by Scandinavian design heritage. \n" +
            "Wooden elegance made for years of use thanks to a sturdy frame, \n" +
            "thick veneer tabletop and smooth butterfly leaf extension.");
    private Furniture chair = new Furniture("705.448.66", "SKOGSTA", 695, "Clear-lacquered solid acacia wood chair with arched backrest and characteristic spindles \n" +
            "– a classic look that ages beautifully and will keep its appeal in your home over many years.");
    private Plants flower = new Plants("468.040.05", "DYPSIS LUTESCENS", 349, "This plant has a tropical expression, ideal for creating a green indoor oasis. \n" +
            "It’s sometimes called a butterfly palm due to the shape of the leaves. \n" +
            "A wonderful roommate that makes any space more vibrant.");
    private Lamps ceilingLamp = new Lamps("404.720.31", "SOLKLINT", 299, "Like small jewels in shiny brass and grey clear glass, \n" +
            "the lamps in the SOLKLINT series spread a soft mood light that creates exciting shadows on walls and ceilings \n" +
            "– wherever you choose to place them.");

    public Webshop() {
        productList = new ArrayList<>();
        productList.add(table);
        productList.add(chair);
        productList.add(flower);
        productList.add(ceilingLamp);
        cart = new ArrayList<>();
    }

    public void webshopCompose(Scanner scanner) {
        boolean running = true;
        System.out.println("Welcome to IKEA's webshop, what would you like to do?");

        while (running) {
            System.out.println("1 - Add product to cart\n" +
                    "2 - Remove from cart\n" +
                    "3 - List products in cart\n" +
                    "4 - Show a product's details\n" +
                    "5 - List available products from the catalog\n" +
                    "q - Quit");
            System.out.println();
            System.out.print("Input: ");

            String choice = scanner.nextLine().toLowerCase();
            switch (choice) {
                case "1" -> webshopAdd(scanner);

                case "2" -> webshopRemove(scanner);

                case "3" -> listProductsInCart();

                case "4" -> webshopPrintInfo(scanner);

                case "5" -> listAvailableProducts();

                case "q" -> {
                    System.out.println("Quitting...");
                    System.out.println("Please come again!");
                    running = false;
                }
                default -> System.out.println("Wrong input, please try again.");
            }

        }
    }

    private void webshopAdd(Scanner scanner) {
        System.out.print("Enter the 'title' or 'article number' of the product to add: ");
        String productName = scanner.nextLine();
        for (Product productNameMatch : productList) {
            if (productNameMatch.getTitle().equalsIgnoreCase(productName) || productNameMatch.getArticleNumber().equalsIgnoreCase(productName)) {
                addProduct(productNameMatch);
                System.out.println(productNameMatch.getTitle() + " has been added!");
                return;
            }
        }
        System.out.println("Product not found, please try again.");
    }

    private void webshopRemove(Scanner scanner) {
        System.out.print("Enter the 'title' or 'article number' of the product to remove: ");
        String productName = scanner.nextLine();
        for (Product productNameMatch : productList) {
            if (productNameMatch.getTitle().equalsIgnoreCase(productName) || productNameMatch.getArticleNumber().equalsIgnoreCase(productName)) {
                removeProduct(productNameMatch);
                System.out.println(productNameMatch.getTitle() + " has been removed!");
                return;
            }
        }
        System.out.println("Product not found, please try again.");
    }

    private void webshopPrintInfo(Scanner scanner) {
        System.out.print("Enter the 'title' or 'article number' of the product for more information: ");
        String productName = scanner.nextLine();
        for (Product productNameMatch : productList) {
            if (productNameMatch.getTitle().equalsIgnoreCase(productName) || productNameMatch.getArticleNumber().equalsIgnoreCase(productName)) {
                printProductInfo(productNameMatch);
                return;
            }
        }
        System.out.println("Product not found, please try again.");
    }

    private void addProduct(Product product) {
        cart.add(product);
    }

    private void removeProduct(Product product) {
        cart.remove(product);
    }

    private void listProductsInCart() {
        if (cart.isEmpty()) {
            System.out.println("Your cart is empty, please add a product first.");
            System.out.println();
        } else {
            System.out.println("Listing all added products from the cart: ");
            for (Product product : cart) {
                System.out.println("Product name: " + product.getTitle());
                System.out.println("Article number: " + product.getArticleNumber());
                System.out.println("Price: " + product.getPrice() + " SEK");
                System.out.println();
            }
        }
    }

    private void listAvailableProducts() {
        System.out.println("Available products: ");
        for (Product product : productList) {
            System.out.println(product.getTitle() + " : " + product.getArticleNumber());
        }
    }

    private void printProductInfo(Product product) {
        System.out.println("Product name: " + product.getTitle() + ", Article number: " + product.getArticleNumber());
        System.out.println("Category: " + product.category() + ".");
        System.out.println("Description: \n" + "'" + product.getDescription() + "'");
        System.out.println();
    }


    // While

    // Switch
    // Meny
        /*
        1 - add
        vilken kategori?
        vilken produkt?
        addera till en fil
        2 - remove
        listar alla produkter
        switch på vilken produkt att ta bort.
        är du säker?
        3 - list
        visa alla i cart
        4 - show info about a product
        lista alla produkter
        skriv vilken produkt
        print info
        q - quit
        */
    // Val av metoder

}
