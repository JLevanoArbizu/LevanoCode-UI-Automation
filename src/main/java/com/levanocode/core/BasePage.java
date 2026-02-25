package com.levanocode.core;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class BasePage {
    protected WebDriver driver;

    // Constructor
    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    // Wrappers (Envoltorios) de Selenium
    protected WebElement find(By locator) {
        return driver.findElement(locator);
    }

    protected void type(String text, By locator) {
        find(locator).sendKeys(text);
    }

    protected void click(By locator) {
        find(locator).click();
    }

    protected String getText(By locator) {
        return find(locator).getText();
    }

    protected String metodooooq(By locator) {
        return find(locator).getText();
    }
}