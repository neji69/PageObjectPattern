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
import java.util.regex.Pattern;

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
    public void testCase20() throws InterruptedException {
        //Данные для теста (логин,пароль и смс для авторизации)
        String login = "demo";
        String password = "demo";
        String smsCode = "0000";
        Pattern pattern = Pattern.compile("[0-9]{1,3} [0-9]{1,3} [0-9]{1,3}[.][0-9][0-9] [₽]");

        BspbRuAuth bspbRuAuth = new BspbRuAuth(driver);
        TwoFactAuth twoFactAuth = bspbRuAuth.authorize(login, password, driver);

        Home home = twoFactAuth.smsAuth(smsCode, driver);
        assertThat(driver.getTitle())
                .isEqualTo("Старт - Интернет банк - Банк Санкт-Петербург");
        OverviewPage overviewPage = home.clickButtonOverview(driver);

        Thread.sleep(1000); //без этого не работает, страница не успевает прогружаться, а тест уже падает из за не найденных данных.
        assertThat(driver.getTitle())
                .as("Открылась страница «Обзор»")
                .contains("Обзор");

        assertThat(driver.findElement(overviewPage.getFinanceFreeLocator()).getText())
                .as("На странице отображается блок «Финансовая свобода» и сумма в блоке 'финансовая свобода' в формате 123 456 789.00 ")
                .contains("Финансовая свобода")
                .containsPattern(pattern);

        //Навести курсор на сумму в блоке «Финансовая свобода»
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(overviewPage.getFinanceFreeLocator())).build().perform();

            assertThat(driver.findElement(overviewPage.getFinanceFreeLocator()).getText())
                    .as("Появляется надпись: «Моих средств» с указанием суммы в формате 123 456 789.00 ")
                    .contains("Моих средств")
                    .containsPattern(pattern);
    }

    @AfterTest
    public void exitDriver() {
        driver.quit();
    }

}

