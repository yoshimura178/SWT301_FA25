package tests;

import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import utils.DriverFactory;

import java.time.Duration;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public abstract class BaseTest {
    protected static WebDriver driver;

    @BeforeAll
    void globalSetUp() {
        driver = DriverFactory.getDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        driver.manage().window().maximize();
    }

    @AfterAll
    void globalTearDown() {
        DriverFactory.quitDriver();
    }
}
