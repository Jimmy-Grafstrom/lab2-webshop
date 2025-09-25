package se.iths.jimmy;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class Webshop {

    private static final String FILE_PATH = "products.json";
    private List<Product> productList;
    private List<Product> cart;
    private Ui ui;

    public Webshop(Ui ui) {
        cart = new ArrayList<>();
        this.ui = ui;
        loadProducts();
    }

    public void initialProductSetup() {
        productList = new ArrayList<>();
        productList.add(new Furniture("705.632.37", "SKANSNÄS", 7995, """
                SKANSNÄS table offers a modern flair inspired by Scandinavian design heritage.\s
                Wooden elegance made for years of use thanks to a sturdy frame,\s
                thick veneer tabletop and smooth butterfly leaf extension."""));
        productList.add(new Furniture("705.448.66", "SKOGSTA", 695, """
                Clear-lacquered solid acacia wood chair with arched backrest and characteristic spindles
                – a classic look that ages beautifully and will keep its appeal in your home over many years."""));
        productList.add(new Plants("468.040.05", "DYPSIS LUTESCENS", 349, """
                This plant has a tropical expression, ideal for creating a green indoor oasis.\s
                It’s sometimes called a butterfly palm due to the shape of the leaves.\s
                A wonderful roommate that makes any space more vibrant."""));
        productList.add(new Lamps("404.720.31", "SOLKLINT", 299, """
                Like small jewels in shiny brass and grey clear glass,\s
                the lamps in the SOLKLINT series spread a soft mood light that creates exciting shadows on walls and ceilings\s
                – wherever you choose to place them."""));
        ui.info("Catalogue successfully setup.");
    }

    public void loadProducts() {
        File file = new File(FILE_PATH);
        if (file.exists() && file.length() > 0) {
            ObjectMapper mapper = new ObjectMapper();
            try {
                List<Product> loadedList = mapper.readValue(file, mapper.getTypeFactory().constructCollectionType(List.class, Product.class));
                this.productList = loadedList;
                ui.info("Catalogue loaded from: " + FILE_PATH);
            } catch (Exception e) {
                ui.info("Something went wrong loading products, using initial product setup. (" + e.getMessage() + ")");
                initialProductSetup();
            }
        } else {
            ui.info("Setting up catalogue file...");
            initialProductSetup();
        }
    }

    public void saveProducts() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.writerWithDefaultPrettyPrinter().writeValue(new File(FILE_PATH), productList);
        } catch (Exception e) {
            ui.info("Something went wrong with saving products to file: " + FILE_PATH + " (" + e.getMessage() + ")");
        }
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
                    saveProducts();
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
        for (Product productNameMatch : productList) {
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
        for (Product productNameMatch : productList) {
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
        for (Product productNameMatch : productList) {
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
        if (cart.isEmpty()) {
            ui.info("Your cart is empty, please add a product first.");
            ui.info("");
        } else {
            ui.info("Listing all added products from the cart: \n" +
                    "----------------------------------------");
            for (Product product : cart) {
                ui.info("Product name: " + product.getTitle());
                ui.info("Article number: " + product.getArticleNumber());
                ui.info("Price: " + product.getPrice() + " SEK");
                ui.info("");
            }
        }
    }

    private void listAvailableProducts() {
        ui.info("Available products: ");
        for (Product product : productList) {
            ui.info(product.getTitle() + " : " + product.getArticleNumber());
        }
    }

    private void printProductInfo(Product product) {
        ui.info("Product name: " + product.getTitle() + ", Article number: " + product.getArticleNumber());
        ui.info("Category: " + product.category() + ".");
        ui.info("Description: \n" + "'" + product.getDescription() + "'");
        ui.info("");
    }
}
