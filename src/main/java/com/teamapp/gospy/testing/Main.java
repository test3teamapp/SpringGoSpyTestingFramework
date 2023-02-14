package com.teamapp.gospy.testing;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class Main {
    public static void main(String[] args) throws MalformedURLException {
       /* //WebDriver driver = new FirefoxDriver();
        //driver.get("http://rheotome.eu");
        //FirefoxOptions fxOptions = new FirefoxOptions();
        ChromeOptions chromeOptions = new ChromeOptions();
        WebDriver driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), chromeOptions);
        //System.setProperty("webdriver.chrome.driver", "/usr/bin/chromedriver");

        //chromeOptions.addArguments("--headless");
        ///chromeOptions.addArguments("--no-sandbox");
        //WebDriver driver = new ChromeDriver(chromeOptions);

        driver.get("http://rheotome.eu");
        WebElement usernameInput = driver.findElement(By.xpath("/html[1]/body[1]/app-root[1]/ng-component[1]/div[1]/ng-component[1]/div[1]/div[1]/form[1]/div[1]/input[1]"));
        usernameInput.click();
        usernameInput.sendKeys("loco");
        WebElement loginBtn = driver.findElement(By.xpath("/html[1]/body[1]/app-root[1]/ng-component[1]/div[1]/ng-component[1]/div[1]/div[1]/form[1]/div[3]/button[1]"));
        loginBtn.click();
        WebElement errorMessageInvalidFeedbackPass = driver.findElement(By.xpath("/html[1]/body[1]/app-root[1]/ng-component[1]/div[1]/ng-component[1]/div[1]/div[1]/form[1]/div[2]/div[1]"));
        System.out.println(errorMessageInvalidFeedbackPass.getText());*/
    }
}