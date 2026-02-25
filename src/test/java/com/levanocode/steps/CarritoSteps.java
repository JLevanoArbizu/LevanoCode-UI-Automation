package com.levanocode.steps;

import com.levanocode.hooks.Hooks;
import com.levanocode.pages.InventoryPage;
import com.levanocode.pages.LoginPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;

public class CarritoSteps {

    LoginPage loginPage = new LoginPage(Hooks.driver);
    InventoryPage inventoryPage;

    @Given("que el usuario inicia sesion con {string} y {string}")
    public void iniciarSesionParaCarrito(String usuario, String password) {
        Hooks.driver.get("https://www.saucedemo.com/");
        loginPage.ingresarCredenciales(usuario, password);
        inventoryPage = loginPage.clicBotonLogin();
    }

    @When("agrega la mochila al carrito")
    public void agregarMochila() {
        inventoryPage.clicAgregarMochila();
    }

    @Then("el icono del carrito muestra {string} producto")
    public void validarCantidadCarrito(String cantidadEsperada) {
        String cantidadActual = inventoryPage.obtenerCantidadCarrito();
        Assertions.assertEquals(cantidadEsperada, cantidadActual, "La cantidad del carrito es incorrecta.");
    }
}