package hoang.example;

import lombok.extern.slf4j.Slf4j;

class AppConstants {
    private AppConstants() {}            // ngăn khởi tạo
    public static final int MAX_USERS = 100;
}

@Slf4j
public class InterfaceFieldModificationExample {
    public static void main(String[] args) {
        int limit = AppConstants.MAX_USERS;
        log.info("Max users: {}", limit);
        // AppConstants.MAX_USERS = 200; // vẫn compile-time error (final)
    }
}
