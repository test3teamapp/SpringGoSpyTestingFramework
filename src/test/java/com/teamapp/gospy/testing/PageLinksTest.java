package com.teamapp.gospy.testing;

import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;

import java.net.MalformedURLException;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PageLinksTest {

    private static WebDriver wd;

    @BeforeAll
    static void setUp() throws MalformedURLException {
        wd = WebDriverFactory.getWebDriver();
    }

    @AfterAll
    static void tearDown() {
        WebDriverFactory.closeDriver();
    }

    @Test
    void indexPageIsDashboardPage() {
        wd.get(WebDriverFactory.siteUrl);
        assert (wd.getTitle().compareTo("GoSpy - Dashboard") == 0);
    }

    @Test
    void dashboardPageIsDashboardPage() {
        wd.get(WebDriverFactory.siteUrl + "/dashboard");
        assert (wd.getTitle().compareTo("GoSpy - Dashboard") == 0);
    }

    @Test
    void mapsPageIsMapsPage() {
        wd.get(WebDriverFactory.siteUrl + "/maps");
        assert (wd.getTitle().compareTo("GoSpy - Maps") == 0);
    }

    @Test
    void chatPageIsChatPage() {
        wd.get(WebDriverFactory.siteUrl + "/chat");
        assert (wd.getTitle().compareTo("GoSpy - Chat") == 0);
    }
}
