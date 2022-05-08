package databaseTest.jdbcConnection;

import java.sql.Connection;
import java.sql.DriverManager;

public class MySQLConnection {

    public static Connection getMySQLConnection() {
        String hostName = "localhost"; // IP public
        String dbName = "automation";
        String userName = "root";
        String password = "admin";
        return getMySQLConnection(hostName, dbName, userName, password);
    }

    private static Connection getMySQLConnection(String hostName, String dbName, String userName, String password) {
        Connection conn = null;
        try {
            // Khai báo Class Driver cho MySQL
            // Việc này cần thiết voiwsi Java5
            // Java 6 tự động tìm kiếm Driver thích hợp-ko bắt buộc dòng code dưới đây
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Cấu trúc URL Connection dành cho MySQL
            // Ví dụ: jdbc:mysql://localhost:3306/automation
            String connectionURL = "jdbc:mysql://" + hostName + ":3306/" + dbName;
            conn = DriverManager.getConnection(connectionURL, userName, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }
}
