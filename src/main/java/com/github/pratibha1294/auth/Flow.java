package com.github.pratibha1294.auth;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import java.util.logging.Logger;

public class Flow {
@Test
    public void test_StandardUser() throws InterruptedException {
        Logger log = Logger.getAnonymousLogger();
        // declaration and instantiation of objects/variables
        System.setProperty("webdriver.chrome.driver", "/home/prathiba/chromedriver_linux64 (1)/chromedriver");
        ChromeDriver driver = new ChromeDriver();



// Launch website
        WebDriver.Navigation ngv= driver.navigate();
        ngv.to("http://saucedemo.com/");
        log.info("Please check navigation");
//         ngv.wait(8000);
        WebElement username = driver.findElement(By.id("user-name"));
        WebElement password = driver.findElement(By.id("password"));
        username.sendKeys("standard_user");
        password.sendKeys("secret_sauce");
        driver.findElement(By.name("login-button")).click();
        log.info("Please check login");

        Thread.sleep(5000);
        log.info("Please check waiting");

        WebElement hamburgerButton = driver.findElement(By.id("react-burger-menu-btn"));
        hamburgerButton.click();
        log.info("Please check menu");
        Thread.sleep(9000);

        WebElement logoutButton = driver.findElement(By.id("logout_sidebar_link"));
        logoutButton.click();
        log.info("Please check logout");
        Thread.sleep(5000);

        driver.quit();
    }

    public void test_LockedOutUser(){

    }

    public void test_ProblemUser(){

    }
}
