package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PracticeFormPage extends BasePage {

    private final String URL = "https://demoqa.com/automation-practice-form";

    public PracticeFormPage(WebDriver driver) {
        super(driver);
    }

    public void open() {
        driver.get(URL);
    }

    public void setFirstName(String firstName) {
        sendKeys(By.id("firstName"), firstName);
    }

    public void setLastName(String lastName) {
        sendKeys(By.id("lastName"), lastName);
    }

    public void setEmail(String email) {
        sendKeys(By.id("userEmail"), email);
    }

    public void selectGender(String gender) {
        if (gender == null || gender.isBlank()) {
            return; // cho negative case
        }

        String id = switch (gender.trim().toLowerCase()) {
            case "male" -> "gender-radio-1";
            case "female" -> "gender-radio-2";
            default -> "gender-radio-3";
        };

        By labelLocator = By.cssSelector("label[for='" + id + "']");
        WebElement label = waitForClickable(labelLocator);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", label);
        label.click();
    }


    public void setMobile(String mobile) {
        sendKeys(By.id("userNumber"), mobile);
    }

    public void setDateOfBirth(String dob) {
        if (dob == null || dob.isBlank()) {
            return; // allow missing dob for negative tests
        }

        By dobInput = By.id("dateOfBirthInput");
        click(dobInput);
        WebElement input = waitForVisibility(dobInput);
        input.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        input.sendKeys(dob);
        input.sendKeys(Keys.ENTER);
    }

    public void addSubject(String subject) {
        if (subject == null || subject.isBlank()) {
            return;
        }
        By input = By.id("subjectsInput");
        WebElement el = waitForVisibility(input);
        el.sendKeys(subject.trim());
        el.sendKeys(Keys.ENTER);
    }

    public void selectHobby(String hobbies) {
        if (hobbies == null || hobbies.isBlank()) {
            return;
        }

        String[] hobbyList = hobbies.split("[|,]");

        for (String hobby : hobbyList) {
            String trimmed = hobby.trim().toLowerCase();
            String id;

            switch (trimmed) {
                case "sports" -> id = "hobbies-checkbox-1";
                case "reading" -> id = "hobbies-checkbox-2";
                case "music" -> id = "hobbies-checkbox-3";
                default -> { continue; }
            }

            By labelLocator = By.cssSelector("label[for='" + id + "']");
            WebElement label = waitForClickable(labelLocator);
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", label);
            label.click();
        }
    }

    public void uploadPictureFromResources(String fileName) {
        if (fileName == null || fileName.isBlank()) {
            return;
        }

        // Lấy file từ thư mục src/test/resources/upload
        String projectPath = System.getProperty("user.dir");
        Path path = Paths.get(projectPath, "src", "test", "resources", "upload", fileName);
        File file = path.toFile();

        if (!file.exists()) {
            throw new RuntimeException("Upload file not found: " + file.getAbsolutePath());
        }

        WebElement input = waitForVisibility(By.id("uploadPicture"));
        input.sendKeys(file.getAbsolutePath());
    }

    public void setCurrentAddress(String address) {
        sendKeys(By.id("currentAddress"), address);
    }

    public void selectState(String state) {
        if (state == null || state.isBlank()) return;

        By stateDiv = By.id("state");
        click(stateDiv);
        By input = By.cssSelector("#state input");
        WebElement in = waitForVisibility(input);
        in.sendKeys(state.trim());
        in.sendKeys(Keys.ENTER);
    }

    public void selectCity(String city) {
        if (city == null || city.isBlank()) return;

        By cityDiv = By.id("city");
        click(cityDiv);
        By input = By.cssSelector("#city input");
        WebElement in = waitForVisibility(input);
        in.sendKeys(city.trim());
        in.sendKeys(Keys.ENTER);
    }

    public void submit() {
        WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(By.id("submit")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", btn);
        btn.click();
    }

    public boolean isSubmissionModalDisplayed() {
        try {
            WebElement modal = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            By.id("example-modal-sizes-title-lg")
                    )
            );
            return modal.isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }

    public void assertPopupVisible() {
        if (!isSubmissionModalDisplayed()) {
            throw new AssertionError("Expected submission modal to be visible, but it was not.");
        }
    }

    public void assertPopupNotVisible() {
        if (isSubmissionModalDisplayed()) {
            throw new AssertionError("Submission modal is visible but this row expected to fail.");
        }
    }

    // Các assert đơn giản với table submit (DemoQA modal)
    public void assertSubmissionName(String first, String last) {
        assertTextInResultRow("Student Name", first + " " + last);
    }

    public void assertSubmissionEmail(String email) {
        assertTextInResultRow("Student Email", email);
    }

    public void assertSubmissionGender(String gender) {
        assertTextInResultRow("Gender", gender);
    }

    public void assertSubmissionMobile(String mobile) {
        assertTextInResultRow("Mobile", mobile);
    }

    private void assertTextInResultRow(String label, String expected) {
        String xpath = "//td[text()='" + label + "']/following-sibling::td";
        String actual = waitForVisibility(By.xpath(xpath)).getText();
        if (!actual.equalsIgnoreCase(expected)) {
            throw new AssertionError("Expected " + label + " = " + expected + " but got " + actual);
        }
    }
}
