package se.iths.jimmy.ui;

import javax.swing.*;

public class UiJOptionPane implements Ui {

    private String appName = "IKEA webshop";

    @Override
    public String prompt(String message) {

        return JOptionPane.showInputDialog(message);
    }

    @Override
    public void info(String message) {
        JOptionPane.showMessageDialog(null, message, appName, JOptionPane.INFORMATION_MESSAGE);

    }

    @Override
    public String menu() {
        return JOptionPane.showInputDialog("""
                1 - Add product to cart
                2 - Remove from cart
                3 - List products in cart
                4 - Show a product's details
                5 - List available products from the catalog
                q - Quit""");
    }
}
