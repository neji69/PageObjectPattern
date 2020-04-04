package com.github.neji69;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;

public class TestCaseHomework {

    private static final Logger log = LoggerFactory.getLogger(TestCaseHomework.class);
    WebDriver driver;
    WebDriverWait webDriverWait;


    @BeforeTest
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        webDriverWait = new WebDriverWait(driver, 30);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://idemo.bspb.ru");
        driver.manage().window().maximize();

    }

    @Test
    public void testCase20() {
        //Данные для теста (логин,пароль и смс для авторизации)
        String login = "demo";
        String password = "demo";
        String smsCode = "0000";

        BspbRuAuth bspbRuAuth = new BspbRuAuth(driver);
        TwoFactAuth twoFactAuth = bspbRuAuth.authorize(login, password, driver);

        Home home = twoFactAuth.smsAuth(smsCode, driver);
        assertThat(driver.getTitle())
                .isEqualTo("Старт - Интернет банк - Банк Санкт-Петербург");
        OverviewPage overviewPage = home.clickButtonOverview(driver);

        assertThat(driver.getTitle())
                .as("Открылась страница «Обзор»")
                .contains("Обзор");

        assertThat(driver.findElement(overviewPage.financeFreeLocator).getText())
                .as("На странице отображается блок «Финансовая свобода»")
                .contains("Финансовая свобода");


        System.out.println("1 " + driver.findElement(overviewPage.financeFreeBalanceLocator).getText());
        System.out.println("2 " + driver.findElement(overviewPage.financeFreeBalanceLocator).getText());
        System.out.println("3 " + driver.findElement(overviewPage.financeFreeBalanceLocator).getText());
        System.out.println("4 " + driver.findElement(overviewPage.financeFreeBalanceLocator).getText());
        System.out.println("5 " + driver.findElement(overviewPage.financeFreeBalanceLocator).getText());
        System.out.println("6 " + driver.findElement(overviewPage.financeFreeBalanceLocator).getText());
        System.out.println("7 " + driver.findElement(overviewPage.financeFreeBalanceLocator).getText());
        System.out.println("8 " + driver.findElement(overviewPage.financeFreeBalanceLocator).getText());
        System.out.println("9 " + driver.findElement(overviewPage.financeFreeBalanceLocator).getText());
        System.out.println("10 " + driver.findElement(overviewPage.financeFreeBalanceLocator).getText());
        System.out.println("11 " + driver.findElement(overviewPage.financeFreeBalanceLocator).getText());
        System.out.println("12 " + driver.findElement(overviewPage.financeFreeBalanceLocator).getText());
        System.out.println("13 " + driver.findElement(overviewPage.financeFreeBalanceLocator).getText());
        System.out.println("14 " + driver.findElement(overviewPage.financeFreeBalanceLocator).getText());
        System.out.println("15 " + driver.findElement(overviewPage.financeFreeBalanceLocator).getText());

        try {
            // webDriverWait.until (ExpectedConditions.textMatches(overviewPage.financeFreeBalanceLocator,2 718 764.83"))
            assertThat(driver.findElement(overviewPage.financeFreeBalanceLocator).getText())
                    .as(" с указанием суммы в формате 123 456 789.00 ")
                    .contains("123 456 789.00");
        } catch (AssertionError e) {
            log.error("Не соответствие баланса", e);

        }
        //Навести курсор на сумму в блоке «Финансовая свобода»
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(overviewPage.financeFreeLocator)).build().perform();

        try {
            assertThat(driver.findElement(overviewPage.financeFreeLocator).getText())
                    .as("Появляется надпись: «Моих средств» с указанием суммы в формате 123 456 789.00 ")
                    .contains("123 456 789.00");
        } catch (AssertionError e) {
            log.error("Не соответствие баланса", e);
        }
    }

    @AfterTest
    public void exitDriver() {
        driver.quit();
    }

}

