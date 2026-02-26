package com.levanocode.hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Hooks {
    public static WebDriver driver;

    @Before
    public void setUp() {
        ChromeOptions options = new ChromeOptions();

        // Detecta si está corriendo en Jenkins o si quieres forzar Headless
        String runHeadless = System.getProperty("headless", "false");

        if (runHeadless.equals("true") || System.getenv("JENKINS_URL") != null) {
            options.addArguments("--headless=new"); // Modo sin ventana
            options.addArguments("--no-sandbox"); // Requerido en Linux/Docker
            options.addArguments("--disable-dev-shm-usage"); // Estabilidad en contenedores
            options.addArguments("--window-size=1920,1080");
        }

        driver = new ChromeDriver(options);

        if (System.getenv("JENKINS_URL") == null) {
            driver.manage().window().maximize();
        }
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}