package tests;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import pages.LoginPage;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class LoginTest extends BaseTest {

    private static LoginPage loginPage;

    @BeforeAll
    static void init() {
        loginPage = new LoginPage(driver);
    }

    @BeforeEach
    void beforeTest() {
        loginPage.ensureLoggedOut();
    }

    @AfterEach
    void afterTest() {
        if (loginPage.isLoginSuccessful()) {
            loginPage.logout();
        }
    }

    @ParameterizedTest(name = "Login: {0} / {1} -> {2}")
    @CsvFileSource(resources = "/login-data.csv", numLinesToSkip = 1)
    @DisplayName("Login test using CSV data")
    void testLoginViaCsv(String username, String password, String expected) {

        loginPage.login(username, password);

        switch (expected.toLowerCase()) {
            case "success" -> {
                assertTrue(loginPage.isLoginSuccessful(), "User should land on a non-login page");
                assertFalse(loginPage.isLoginFailed(), "No error message should appear");
            }
            case "error" -> {
                assertTrue(loginPage.isLoginFailed(), "Error message expected");
                assertFalse(loginPage.isLoginSuccessful(), "Should stay on login page");
            }
            default -> fail("Invalid expected outcome in CSV: " + expected);
        }
    }
}
