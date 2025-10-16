package hoang.example;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HardcodedCredentialsExample {
    public static void main(String[] args) {
        String username  = System.getenv("APP_USERNAME");
        String password  = System.getenv("APP_PASSWORD");
        if(authenticate(username, password)) {
            log.info("Access granted");
        } else {
            log.info("Access denied");
        }
    }

    private static boolean authenticate(String user, String pass) {
        // Dummy authentication logic
        return user.equals("admin") && pass.equals("123456");
    }
}
