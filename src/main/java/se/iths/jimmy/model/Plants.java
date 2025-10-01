package se.iths.jimmy.model;

public class Plants extends Product {

    public Plants() {
        super();
    }

    public Plants(String articleNumber, String title, double price, String description) {
        super(articleNumber, title, price, description);
    }

    @Override
    public String category() {
        return "Plant";
    }

}
