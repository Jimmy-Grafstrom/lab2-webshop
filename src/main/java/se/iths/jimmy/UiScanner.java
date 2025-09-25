package se.iths.jimmy;

import java.util.Scanner;

public class UiScanner implements Ui{

    Scanner scanner = new Scanner(System.in);

    public UiScanner(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public String prompt(String message) {
        return scanner.nextLine();
    }

    @Override
    public void info(String message) {
        System.out.println(message);
    }

    @Override
    public String menu() {
        System.out.println("1 - Add product to cart\n" +
                "2 - Remove from cart\n" +
                "3 - List products in cart\n" +
                "4 - Show a product's details\n" +
                "5 - List available products from the catalog\n" +
                "q - Quit");
        System.out.println();
        System.out.print("Input: ");

        return scanner.nextLine().toLowerCase();
    }
}
