package com.levanocode.pages;

import com.levanocode.core.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class InventoryPage extends BasePage {

    private By headerTitle = By.className("title");
    // Nuevos Locators para el ticket LQAE-4
    private By btnAgregarMochila = By.id("add-to-cart-sauce-labs-backpack");
    private By badgeCarrito = By.className("shopping_cart_badge");

    public InventoryPage(WebDriver driver) {
        super(driver);
    }

    public String obtenerTituloPantalla() {
        return getText(headerTitle);
    }

    // Nuevas acciones para el ticket LQAE-4
    public void clicAgregarMochila() {
        click(btnAgregarMochila);
    }

    public String obtenerCantidadCarrito() {
        return getText(badgeCarrito);
    }
}