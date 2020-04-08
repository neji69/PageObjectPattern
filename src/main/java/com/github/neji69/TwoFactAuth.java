package com.github.neji69;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class TwoFactAuth {
    private WebDriver driver;
    private By smsCodeLocator = By.id("otp-code");
    private By smsButtonLocator = By.id("login-otp-button");


    public TwoFactAuth(WebDriver driver) {
        this.driver = driver;
    }

    private void inputSmsCode(String smsCode) {
        driver.findElement(smsCodeLocator).clear();
        driver.findElement(smsCodeLocator).sendKeys(smsCode);
    }

    private void clickButtonSmsAuthorization() {
        driver.findElement(smsButtonLocator).click();

    }

    public Home smsAuth(String smsCode, WebDriver driver) {
        inputSmsCode(smsCode);
        clickButtonSmsAuthorization();
        return new Home(driver);
    }

}