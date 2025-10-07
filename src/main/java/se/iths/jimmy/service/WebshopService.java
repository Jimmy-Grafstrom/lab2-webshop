package se.iths.jimmy.service;

import se.iths.jimmy.model.Product;
import se.iths.jimmy.ui.Ui;

import java.util.ArrayList;
import java.util.List;


public class WebshopService {

    private RepositoryService repositoryService;
    private List<Product> cart;
    private Ui ui;

    public WebshopService(Ui ui, RepositoryService repositoryService) {
        this.repositoryService = repositoryService;
        cart = new ArrayList<>();
        this.ui = ui;
        repositoryService.createRepository();
    }

    public void webshopCompose() {
        boolean running = true;
        ui.info("Welcome to IKEA's webshop, what would you like to do?");
        while (running) {
            String choice = ui.menu();
            switch (choice) {
                case "1" -> webshopAdd();

                case "2" -> webshopRemove();

                case "3" -> listProductsInCart();

                case "4" -> webshopPrintInfo();

                case "5" -> listAvailableProducts();

                case "q" -> {
                    ui.info("Quitting...");
                    ui.info("Please come again!");
                    running = false;
                }
                default -> ui.info("Wrong input, please try again.");
            }
        }
    }

    private void webshopAdd() {
        String productName = ui.prompt("Enter the 'title' or 'article number' of the product to add: ");
        for (Product productNameMatch : repositoryService.getProductList()) {
            if (productNameMatch.getTitle().equalsIgnoreCase(productName) || productNameMatch.getArticleNumber().equalsIgnoreCase(productName)) {
                addProduct(productNameMatch);
                ui.info(productNameMatch.getTitle() + " has been added!");
                return;
            }
        }
        ui.info("Product not found, please try again.");
    }

    private void webshopRemove() {
        String productName = ui.prompt("Enter the 'title' or 'article number' of the product to remove: ");
        for (Product productNameMatch : repositoryService.getProductList()) {
            if (productNameMatch.getTitle().equalsIgnoreCase(productName) || productNameMatch.getArticleNumber().equalsIgnoreCase(productName)) {
                removeProduct(productNameMatch);
                ui.info(productNameMatch.getTitle() + " has been removed!");
                return;
            }
        }
        ui.info("Product not found, please try again.");
    }

    private void webshopPrintInfo() {
        String productName = ui.prompt("Enter the 'title' or 'article number' of the product for more information: ");
        for (Product productNameMatch : repositoryService.getProductList()) {
            if (productNameMatch.getTitle().equalsIgnoreCase(productName) || productNameMatch.getArticleNumber().equalsIgnoreCase(productName)) {
                printProductInfo(productNameMatch);
                return;
            }
        }
        ui.info("Product not found, please try again.");
    }

    private void addProduct(Product product) {
        cart.add(product);
    }

    private void removeProduct(Product product) {
        cart.remove(product);
    }

    private void listProductsInCart() {
        StringBuilder stringBuilder = new StringBuilder();
        if (cart.isEmpty()) {
            ui.info("Your cart is empty, please add a product first.");
        } else {
            for (Product product : cart) {
                String concatenatedString = "Product name: " + product.getTitle() + "\n" +
                        "Article number: " + product.getArticleNumber() + "\n" +
                        "Price: " + product.getPrice() + " SEK" + "\n";
                stringBuilder.append(concatenatedString);
            }
            ui.info("Listing all added products from the cart: \n" +
                    "----------------------------------------\n" +
                    stringBuilder.toString());
        }
    }

    private void listAvailableProducts() {
        StringBuilder combinedString = new StringBuilder();
        for (Product product : repositoryService.getProductList()) {
            String concatenatedStr = product.getTitle() + " : " + product.getArticleNumber() + "\n";
            combinedString.append(concatenatedStr);
        }
        ui.info("Available products: \n" + combinedString.toString());
    }

    private void printProductInfo(Product product) {
        String nameArticleNumber = "Product name: " + product.getTitle() + "\nArticle number: " + product.getArticleNumber() + "\n";
        String category = "Category: " + product.category() + "\n";
        String description = "Description: \n" + "'" + product.getDescription() + "'";
        ui.info(nameArticleNumber + category + description);
    }
}
