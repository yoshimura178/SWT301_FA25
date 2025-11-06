package tests;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import pages.PracticeFormPage;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PracticeFormTest extends BaseTest {

    private PracticeFormPage form;

    @BeforeAll
    void initPage() {
        form = new PracticeFormPage(driver);
    }

    @BeforeEach
    void openForm() {
        form.open();
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/practice-form-data.csv", numLinesToSkip = 1)
    void testPracticeFormWithCsv(
            String firstName,
            String lastName,
            String email,
            String gender,
            String mobile,
            String dob,
            String subject1,
            String subject2,
            String hobbies,
            String picture,
            String address,
            String state,
            String city,
            String expectedResult
    ) {
        form.setFirstName(firstName);
        form.setLastName(lastName);
        form.setEmail(email);
        form.selectGender(gender);
        form.setMobile(mobile);
        form.setDateOfBirth(dob);

        if (subject1 != null && !subject1.isBlank()) form.addSubject(subject1);
        if (subject2 != null && !subject2.isBlank()) form.addSubject(subject2);

        form.selectHobby(hobbies);
        form.uploadPictureFromResources(picture);
        form.setCurrentAddress(address);
        form.selectState(state);
        form.selectCity(city);
        form.submit();

        if ("success".equalsIgnoreCase(expectedResult)) {
            form.assertPopupVisible();
            form.assertSubmissionName(firstName, lastName);
            form.assertSubmissionEmail(email);
            form.assertSubmissionGender(gender);
            form.assertSubmissionMobile(mobile);
        } else {
            form.assertPopupNotVisible();
        }
    }
}
