package se.iths.jimmy;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Webshop webshop = new Webshop();

        webshop.webshopCompose(scanner);

    }
}