package com.levanocode.pages;

import com.levanocode.core.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class InventoryPage extends BasePage {

    private By headerTitle = By.className("title");

    public InventoryPage(WebDriver driver) {
        super(driver);
    }

    public String obtenerTituloPantalla() {
        return getText(headerTitle);
    }
}