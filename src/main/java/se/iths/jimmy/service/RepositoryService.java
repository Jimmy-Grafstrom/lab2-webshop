package se.iths.jimmy.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import se.iths.jimmy.model.Furniture;
import se.iths.jimmy.model.Lamps;
import se.iths.jimmy.model.Plants;
import se.iths.jimmy.model.Product;
import se.iths.jimmy.ui.Ui;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RepositoryService {

    private String filePath = "src/main/resources/products.json";
    private Ui ui;
    private List<Product> productList = new ArrayList<>();


    public RepositoryService(Ui ui) {
        this.ui = ui;
    }

    private File getFile() {
        return new File(filePath);
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void createRepository() {
        List<Product> loadedList = fetchObjectsFromJson();

        if (getFile().exists() && getFile().length() > 0) {
            this.productList = loadedList;
            ui.info("Repository file loaded successfully!");
        } else {
            System.out.println("Setting up initial repository...");
            initialSetup();
            saveToJson();
            ui.info("Saved new Json-file!");
        }
    }

    private List<Product> fetchObjectsFromJson() {
        ObjectMapper objectMapper = new ObjectMapper();
        List<Product> loadedList = List.of();
        try {
            loadedList = objectMapper.readValue(getFile(), objectMapper.getTypeFactory().constructCollectionType(List.class, Product.class));
        } catch (Exception e) {
            ui.info("Product repository doesn't exist...");
        }
        return loadedList;
    }

    public void saveToJson() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        try {
            List<String> jsonString = new ArrayList<>();
            String productAsString = "";

            for (Product product : productList) {
                productAsString = objectMapper.writeValueAsString(product);
                jsonString.add(productAsString);
            }
            String joinedJsonString = String.join(",\n", jsonString);
            String finalJsonString = "[\n" + joinedJsonString + "\n]";

            try (FileWriter fileWriter = new FileWriter(getFile())) {
                fileWriter.write(finalJsonString);
                if (productList.size() != 1) {
                    ui.info("Successfully initialized " + productList.size() + " products to repository!");
                } else {
                    ui.info("Successfully initialized " + productList.size() + " product to repository!");
                }
            }
        } catch (IOException e) {
            ui.info("Failed to write products to repository..." + e.getMessage());
            e.printStackTrace();
        }
    }

    public void initialSetup() {
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
        ui.info("Product list created...");
    }
}
