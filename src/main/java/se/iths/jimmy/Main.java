package se.iths.jimmy;

import se.iths.jimmy.service.JsonService;
import se.iths.jimmy.service.Webshop;
import se.iths.jimmy.ui.Ui;
import se.iths.jimmy.ui.UiScanner;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = UiScanner.getScanner();
        Ui ui = new UiScanner(scanner);
        JsonService jsonService = new JsonService(ui);
        Webshop webshop = new Webshop(ui, jsonService);

        webshop.webshopCompose();

    }
}