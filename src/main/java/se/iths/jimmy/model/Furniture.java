package se.iths.jimmy.model;

public class Furniture extends Product {

    public Furniture() {
        super();
    }

    public Furniture(String articleNumber, String title, double price, String description) {
        super(articleNumber, title, price, description);
    }

    @Override
    public String category() {
        return "Furniture";
    }
}
