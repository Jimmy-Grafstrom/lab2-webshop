package se.iths.jimmy;

import se.iths.jimmy.service.RepositoryService;
import se.iths.jimmy.service.WebshopService;
import se.iths.jimmy.ui.Ui;
import se.iths.jimmy.ui.UiJOptionPane;

public class Main {
    public static void main(String[] args) {
        //Scanner scanner = UiScanner.getScanner();
        //Ui ui = new UiScanner(scanner);

        Ui ui = new UiJOptionPane();

        RepositoryService repositoryService = new RepositoryService(ui);
        WebshopService webshopService = new WebshopService(ui, repositoryService);

        webshopService.webshopCompose();

    }
}