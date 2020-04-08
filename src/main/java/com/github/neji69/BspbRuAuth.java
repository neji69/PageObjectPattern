package com.github.neji69;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class BspbRuAuth {
    private WebDriver driver;
    private By usernameLocator = By.name("username");
    private By passwordLocator = By.name("password");
    private By loginButtonLocator = By.id("login-button");


    public BspbRuAuth(WebDriver driver) {
        this.driver = driver;
    }


    private void inputLogin(String login) {
        driver.findElement(usernameLocator).clear();
        driver.findElement(usernameLocator).sendKeys(login);
    }

    private void inputPassword(String password) {
        driver.findElement(passwordLocator).clear();
        driver.findElement(passwordLocator).sendKeys(password);
    }

    private void clickButtonAuthorization() {
        driver.findElement(loginButtonLocator).click();
    }

    public TwoFactAuth authorize(String login, String password, WebDriver driver) {
        inputLogin(login);
        inputPassword(password);
        clickButtonAuthorization();
        return new TwoFactAuth(driver);
    }
}