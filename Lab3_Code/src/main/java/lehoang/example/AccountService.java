package lehoang.example;

import java.util.HashSet;
import java.util.Set;

public class AccountService {
    private final Set<String> existingUsernames = new HashSet<>();

    public boolean registerAccount(String username, String password, String email) {
        // --- 1️⃣ Kiểm tra rỗng ---
        if (username == null || username.isEmpty() ||
                password == null || password.isEmpty() ||
                email == null || email.isEmpty()) {
            return false;
        }

        // --- 2️⃣ Username phải > 3 ký tự ---
        if (username.length() <= 3) {
            return false;
        }

        // --- 3️⃣ Username chưa tồn tại ---
        if (existingUsernames.contains(username.toLowerCase())) {
            return false;
        }

        // --- 4️⃣ Kiểm tra password ---
        if (!isValidPassword(password)) {
            return false;
        }

        // --- 5️⃣ Kiểm tra email hợp lệ ---
        if (!isValidEmail(email)) {
            return false;
        }

        // ✅ Nếu tất cả hợp lệ → thêm vào “database”
        existingUsernames.add(username.toLowerCase());
        return true;
    }

    // Kiểm tra pattern email
    public boolean isValidEmail(String email) {
        return email != null && email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");
    }

    // Kiểm tra pattern password
    private boolean isValidPassword(String password) {
        if (password.length() <= 6) return false;

        boolean hasUppercase = password.matches(".*[A-Z].*");
        boolean hasNumber = password.matches(".*[0-9].*");
        boolean hasSpecial = password.matches(".*[!@#$%^&*(),.?\":{}|<>].*");

        return hasUppercase && hasNumber && hasSpecial;
    }
}
