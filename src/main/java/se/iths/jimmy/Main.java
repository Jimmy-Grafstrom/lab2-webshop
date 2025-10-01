package se.iths.jimmy;

import se.iths.jimmy.service.Webshop;
import se.iths.jimmy.ui.Ui;
import se.iths.jimmy.ui.UiScanner;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Ui ui = new UiScanner(scanner);
        Webshop webshop = new Webshop(ui);

        webshop.webshopCompose();

    }
}