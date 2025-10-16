package hoang.example;

import lombok.extern.slf4j.Slf4j;

import java.sql.*;

@Slf4j
public class SQLInjectionExample {

    public static void main(String[] args) throws SQLException {
        String userInput = args.length > 0 ? args[0] : "";

        // Câu lệnh tham số hóa: KHÔNG nội suy trực tiếp userInput
        final String sql = "SELECT 1 FROM users WHERE username = ?";

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, userInput);

            // Chỉ log template, không log mật khẩu / dữ liệu nhạy cảm
            log.debug("Executing prepared statement: {}", sql);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    // ví dụ: tiêu thụ kết quả (tránh log dữ liệu nhạy cảm)
                    log.debug("Row fetched with id={}", rs.getInt("id"));
                }
            }
        }
    }

    // Thay bằng DataSource trong ứng dụng thực; không hardcode credentials
    private static Connection getConnection() throws SQLException {
        String url  = System.getenv("DB_URL");   // vd: jdbc:postgresql://host:5432/db
        String user = System.getenv("DB_USER");
        String pass = System.getenv("DB_PASS");
        return DriverManager.getConnection(url, user, pass);
    }
}
