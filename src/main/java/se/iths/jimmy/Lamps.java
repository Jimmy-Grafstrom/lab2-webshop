package se.iths.jimmy;

public class Lamps extends Product {

    public Lamps(String articleNumber, String title, double price, String description) {
        super(articleNumber, title, price, description);
    }

    @Override
    public String category() {
        return "Lamp";

    }
}
