import lehoang.example.AccountService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import static org.junit.jupiter.api.Assertions.*;

public class AccountServiceTest {
    AccountService service;
    @BeforeEach
    void setUp() {
        service = new AccountService();
    }

    @ParameterizedTest(name = "Test {index} => username={0}, password={1}, email={2}, expected={3}")
    @CsvFileSource(resources = "/test-data.csv", numLinesToSkip = 1)
    void testRegisterAccount(String username, String password, String email, String expected) {
        boolean expectedResult = Boolean.parseBoolean(expected);
        boolean actualResult = service.registerAccount(username, password, email);

        assertEquals(expectedResult, actualResult);
    }
}
