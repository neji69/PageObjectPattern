package com.github.neji69;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class OverviewPage {
    private WebDriver driver;

    public OverviewPage(WebDriver driver) {
        this.driver = driver;
    }

    private By financeFreeLocator = By.id("can-spend");

    public By getFinanceFreeLocator() {
        return financeFreeLocator;
    }

}
