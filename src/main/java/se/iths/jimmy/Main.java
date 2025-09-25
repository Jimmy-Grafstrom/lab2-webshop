package se.iths.jimmy;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Ui ui = new UiScanner(scanner);
        Webshop webshop = new Webshop(ui);

        webshop.webshopCompose();

    }
}