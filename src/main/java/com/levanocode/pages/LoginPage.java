package com.levanocode.pages;

import com.levanocode.core.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {

    // 1. Locators (Privados)
    private By usernameInput = By.id("user-name");
    private By passwordInput = By.id("password");
    private By loginButton = By.id("login-button");

    // 2. Constructor
    public LoginPage(WebDriver driver) {
        super(driver);
    }

    // 3. Acciones (Públicas)
    public void ingresarCredenciales(String user, String pass) {
        type(user, usernameInput);
        type(pass, passwordInput);
    }

    public InventoryPage clicBotonLogin() {
        click(loginButton);
        // Retorna la siguiente página a la que somos redirigidos
        return new InventoryPage(driver);
    }

}