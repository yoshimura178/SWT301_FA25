package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends BasePage {

    private final String baseUrl = "http://localhost:8080";
    private final String loginUrl = baseUrl + "/login";

    // Locators
    private final By usernameInput = By.id("username");
    private final By passwordInput = By.id("password");
    private final By submitButton = By.cssSelector("button[type='submit']");
    private final By errorAlert = By.cssSelector(".alert.alert--error");

    // Logout UI elements
    private final By userMenuBtn = By.id("userMenuBtn");
    private final By logoutButton = By.cssSelector("button.logout");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void openLoginPage() {
        driver.get(loginUrl);
        waitVisible(usernameInput);
    }

    public void login(String username, String password) {
        type(usernameInput, username);
        type(passwordInput, password);
        click(submitButton);
    }

    /** success = URL changes from /login */
    public boolean isLoginSuccessful() {
        return !driver.getCurrentUrl().contains("/login");
    }

    /** failure = show error alert or ?error */
    public boolean isLoginFailed() {
        if (driver.getCurrentUrl().contains("error")) return true;
        try { return waitVisible(errorAlert).isDisplayed(); }
        catch (Exception e) { return false; }
    }

    /** Click menu + click logout button */
    public void logout() {
        try {
            click(userMenuBtn);
            click(logoutButton);
        } catch (Exception ignored) {}
    }

    /** Ensure we start test in logged-out state */
    public void ensureLoggedOut() {
        if (isLoginSuccessful()) logout();
        openLoginPage();
    }
}
