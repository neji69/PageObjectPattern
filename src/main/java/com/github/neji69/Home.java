package com.github.neji69;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Home {
    private WebDriver driver;
    private By buttonOverview = By.id("bank-overview");

    public Home(WebDriver driver) {
        this.driver = driver;
    }

    public OverviewPage clickButtonOverview(WebDriver driver) {
        driver.findElement(buttonOverview).click();
        return new OverviewPage(driver);
    }
}

