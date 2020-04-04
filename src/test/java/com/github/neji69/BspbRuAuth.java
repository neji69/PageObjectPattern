package com.github.neji69;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class BspbRuAuth {
    private WebDriver driver;
    By usernameLocator = By.name("username");
    By passwordLocator = By.name("password");
    By loginButtonLocator = By.id("login-button");


    public BspbRuAuth(WebDriver driver) {
        this.driver = driver;
    }


    void inputLogin(String login) {
        driver.findElement(usernameLocator).clear();
        driver.findElement(usernameLocator).sendKeys(login);
    }

    public void inputPassword(String password) {
        driver.findElement(passwordLocator).clear();
        driver.findElement(passwordLocator).sendKeys(password);
    }

    public void clickButtonAuthorization() {
        driver.findElement(loginButtonLocator).click();
    }

    public TwoFactAuth authorize(String login, String password, WebDriver driver) {
        inputLogin(login);
        inputPassword(password);
        clickButtonAuthorization();
        return new TwoFactAuth(driver);
    }
}


//    @BeforeTest
//    public void setUp() {
//        WebDriverManager.chromedriver().setup();
//        ChromeOptions chromeOptions = new ChromeOptions();
//        driver = new ChromeDriver(chromeOptions);
//        webDriverWait = new WebDriverWait(driver, 30);
//        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//        driver.get("https://idemo.bspb.ru");
//        driver.manage().window().maximize();
//    }