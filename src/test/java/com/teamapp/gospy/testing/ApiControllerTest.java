package com.teamapp.gospy.testing;

import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;

import java.net.MalformedURLException;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ApiControllerTest {

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
    void allTrackedPeople() {
    }

    @Test
    void personById() {
    }

    @Test
    @Order(1)
    void createPerson() throws Exception {
       wd.get(WebDriverFactory.siteUrl + "/api/person/create/");
       System.out.println(wd.getPageSource());
    }

    @Test
    void allUsers() {
    }

    @Test
    void userById() {
    }
}