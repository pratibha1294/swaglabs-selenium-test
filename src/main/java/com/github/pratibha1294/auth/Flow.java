package com.github.pratibha1294.auth;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.util.logging.Logger;

public class Flow {
    Logger log;
    ChromeDriver driver;

    @BeforeSuite
    private void setUp() {
        log = Logger.getAnonymousLogger();
        // declaration and instantiation of objects/variables
        System.setProperty("webdriver.chrome.driver", "/home/prathiba/chromedriver_linux64 (1)/chromedriver");
        driver = new ChromeDriver();
    }

    @AfterSuite
    private void teardown(){
        driver.quit();
    }

    private void login(String userName, String password) {
        WebDriver.Navigation ngv = driver.navigate();
        ngv.to("http://saucedemo.com/");
        log.info("Please check navigation");
//         ngv.wait(8000);
        WebElement usernameEL = driver.findElement(By.id("user-name"));
        WebElement passwordEL = driver.findElement(By.id("password"));
        usernameEL.sendKeys(userName);
        passwordEL.sendKeys(password);
        driver.findElement(By.name("login-button")).click();
        log.info("Please check login");
    }

    private void logout() throws InterruptedException {
        WebElement hamburgerButton = driver.findElement(By.id("react-burger-menu-btn"));
        hamburgerButton.click();
        log.info("Please check menu");
        Thread.sleep(9000);

        WebElement logoutButton = driver.findElement(By.id("logout_sidebar_link"));
        logoutButton.click();
        log.info("Please check logout");
        Thread.sleep(5000);

    }

    @Test
    public void test_StandardUser() throws InterruptedException {
        login("standard_user", "secret_sauce");
        Thread.sleep(5000);
        String currentUrl = driver.getCurrentUrl();
        Assert.assertEquals(currentUrl,"https://www.saucedemo.com/inventory.html");
        log.info("Please check waiting");
        logout();

    }

    @Test
    public void test_LockedOutUser() throws InterruptedException {

        login("locked_out_user", "secret_sauce");
        String currentUrl = driver.getCurrentUrl();
        Assert.assertEquals(currentUrl,"https://www.saucedemo.com/");
        WebElement errorMessageEl= driver.findElement(By.xpath("//*[@id='login_button_container']/div/form/div[3]/h3"));

        String errMsg = errorMessageEl.getText();

        Assert.assertEquals(errMsg,"Epic sadface: Sorry, this user has been locked out.");

        Thread.sleep(5000);
        log.info("Please check waiting");


    }


    public void test_ProblemUser() {

    }
}
