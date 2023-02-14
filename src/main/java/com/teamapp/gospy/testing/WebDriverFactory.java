package com.teamapp.gospy.testing;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class WebDriverFactory {
    public final static String siteUrl = "http://localhost:8080";

    private static WebDriver singleWebDriverObject = null;

    public static WebDriver getWebDriver() throws MalformedURLException {
        if (singleWebDriverObject == null ){
            ChromeOptions chromeOptions = new ChromeOptions();
            ChromeDriverService chromeDriverService = ChromeDriverService.createDefaultService();
            int port = chromeDriverService.getUrl().getPort();
            singleWebDriverObject = new ChromeDriver(chromeOptions);
            System.out.println("starting chromedriver on port " + port);
        }

        return singleWebDriverObject;
    }

    public static void closeDriver(){
        if (singleWebDriverObject != null){
            singleWebDriverObject.close();
            singleWebDriverObject.quit();
            singleWebDriverObject = null;
        }
    }
}
