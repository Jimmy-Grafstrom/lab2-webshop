package se.iths.jimmy;

import java.util.Scanner;

public class UiScanner implements Ui{
    private final Scanner scanner;

    public UiScanner(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public String prompt(String message) {
        System.out.println(message);
        return scanner.nextLine();
    }

    @Override
    public void info(String message) {
        System.out.println(message);
    }

    @Override
    public String menu() {
        System.out.println("""
                1 - Add product to cart
                2 - Remove from cart
                3 - List products in cart
                4 - Show a product's details
                5 - List available products from the catalog
                q - Quit""");
        System.out.println();
        System.out.print("Input: ");

        return scanner.nextLine().toLowerCase();
    }
}
