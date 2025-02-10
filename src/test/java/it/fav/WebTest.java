package it.fav;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WebTest
{
    WebDriver driver;

    @BeforeEach
    public void setup() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new");
        driver = new ChromeDriver(options);
    }

    @AfterEach
    public void teardown() {
        driver.close();
    }
    @Test
    public void testLoginMenuItemAppears() {
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
        driver.get("https://ecommerce-playground.lambdatest.io");
        var myAccount = driver.findElement(By.xpath("/html/body/div[1]/div[5]/header/div[3]/div[1]/div/div[3]/nav/div/ul/li[6]/a/div/span"));
        new Actions(driver).moveToElement(myAccount).perform();
        var login = driver.findElement(By.cssSelector("#widget-navbar-217834 > ul > li:nth-child(6) > ul > li:nth-child(1) > a > div > span"));
        assertEquals("Login", login.getText());
    }

    @Test
    public void testLoginFromHomeWithMenu() {
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
        driver.get("https://ecommerce-playground.lambdatest.io");
        var myAccount = driver.findElement(By.xpath("/html/body/div[1]/div[5]/header/div[3]/div[1]/div/div[3]/nav/div/ul/li[6]/a/div/span"));
        new Actions(driver).moveToElement(myAccount).perform();
        var login = driver.findElement(By.cssSelector("#widget-navbar-217834 > ul > li:nth-child(6) > ul > li:nth-child(1) > a > div > span"));
        login.click();
        var loginBreadCrumb = driver.findElement(By.xpath("//*[@id=\"account-login\"]/nav/ol/li[3]"));
        var wait = new WebDriverWait(driver, Duration.ofSeconds(2));
        wait.until(_ -> loginBreadCrumb.isDisplayed());
        assertEquals("https://ecommerce-playground.lambdatest.io/index.php?route=account/login", driver.getCurrentUrl());
    }
}
