package com.levanocode.steps;

import com.levanocode.hooks.Hooks;
import com.levanocode.pages.InventoryPage;
import com.levanocode.pages.LoginPage;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;

public class LoginSteps {

    LoginPage loginPage = new LoginPage(Hooks.driver);
    InventoryPage inventoryPage;


    @Given("que el usuario navega a la página de login de SauceDemo")
    public void navegarAPaginaLogin() {
        Hooks.driver.get("https://www.saucedemo.com/");
    }

    @When("ingresa el nombre de usuario {string} y la contraseña {string}")
    public void iingresarCredenciales(String usuario, String password) {
        loginPage.ingresarCredenciales(usuario, password);
    }

    @And("hace clic en el botón de iniciar sesión")
    public void clicBotonIniciarSesion() {
        inventoryPage = loginPage.clicBotonLogin();
    }

    @Then("el sistema redirige al catálogo de productos exitosamente")
    public void validarRedireccionCatalogo() {
        String tituloActual = inventoryPage.obtenerTituloPantalla();
        Assertions.assertEquals("Products",tituloActual,"Error en redirección.");
    }
}
