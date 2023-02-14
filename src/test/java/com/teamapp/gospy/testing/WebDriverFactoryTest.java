package com.teamapp.gospy.testing;

import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;

import java.net.MalformedURLException;
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class WebDriverFactoryTest {

    private static WebDriver wd;

    @BeforeAll
    static void setUp() {
        if (wd != null) {
            try {
                wd.close();
            } catch (Exception exc) {
                assert false;
            } finally {
                wd = null;
            }
        }
    }

    @Test
    @Order(1)
    @DisplayName("Get an instance of web driver")
    void getAnInstanceOfWebDriver() {
        try {
            wd = WebDriverFactory.getWebDriver();
        } catch (MalformedURLException e) {
            assert false;
        }
        assert (wd != null);
    }

    @Test
    @DisplayName("Close the web driver")
    void closeTheWebDriver() {
        assert (wd != null);
        if (wd != null) {
            try {
                wd.close();
            } catch (Exception exc) {
                System.err.println(exc.toString());
                assert false;
            } finally {
                wd = null;
            }
        }
    }

}
