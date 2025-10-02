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

public class JsonService {

    private String FILE_PATH = "src/main/resources/products.json";
    private Ui ui;
    private List<Product> productList;

    public JsonService(Ui ui) {
        this.ui = ui;
    }

    public List<Product> getProductList() {
        return initialProductSetup();
    }

    public List<Product> initialProductSetup() {
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
        return productList;
    }


    public void loadProducts() {
        File file = new File(FILE_PATH);
        if (file.exists() && file.length() > 0) {
            ObjectMapper mapper = new ObjectMapper();
            mapper.enable(SerializationFeature.INDENT_OUTPUT);
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
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        try {
            // Steg 1: Skapa en lista för att hålla våra individuella JSON-strängar.
            List<String> individualJsonStrings = new ArrayList<>();

            // Steg 2 & 3: Loopa igenom produktlistan och konvertera varje objekt till en sträng.
            for (Product product : productList) {
                // Konvertera ETT produktobjekt till en JSON-sträng i minnet.
                String productAsJsonString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(product);

                // Steg 4: Lägg till strängen i vår lista.
                individualJsonStrings.add(productAsJsonString);
            }

            // Steg 5: Slå ihop alla strängar med ett kommatecken mellan varje.
            String allProductsString = String.join(",\n", individualJsonStrings);

            // Steg 6: Vira in allt i klamrar för att skapa en giltig JSON-array.
            String finalJsonArrayString = "[\n" + allProductsString + "\n]";

            // Steg 7: Spara den slutgiltiga strängen till filen.
            // Vi använder en FileWriter här eftersom vi redan har en färdig sträng.
            try (FileWriter writer = new FileWriter(FILE_PATH)) {
                writer.write(finalJsonArrayString);
            }

            ui.info("Products successfully saved to: " + FILE_PATH);

        } catch (IOException e) { // Vi behöver fånga IOException specifikt
            ui.info("Something went wrong when saving products to file: " + FILE_PATH + " (" + e.getMessage() + ")");
        }

    }
}
