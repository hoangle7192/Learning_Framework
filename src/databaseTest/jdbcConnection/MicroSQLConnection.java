package databaseTest.jdbcConnection;

import java.sql.*;
public class MicroSQLConnection {

    public static Connection getSQLSeverConnection() {
        String hostName = "192.168.68.124"; // IP public
        String sqlInstance = "SQLEXPRESS";
        String dbName = "database=reblitzdb";
        String userName = "sa";
        String password = "Password123";
        return getSQLSeverConnection(hostName, sqlInstance, dbName, userName, password);
    }

    private static Connection getSQLSeverConnection(String hostName, String sqlInstance, String dbName, String userName, String password) {
        Connection conn = null;
        try {
            // Khai báo Class Driver cho SQLSever
            // Việc này cần thiết voi Java5
            // Java 6 tự động tìm kiếm Driver thích hợp-ko bắt buộc dòng code dưới đây
            //Class.forName("net.sourceforge.jtds.jdbc.Driver");

            // Cấu trúc URL Connection dành cho MySQL
            // Ví dụ: jdbc:jtds:sqlserver://localhost:1433/automation;instance=SQLEXPRESS
            String connectionURL = "jdbc:sqlserver://" + hostName + ":1433;" + dbName + ";encrypt=true;trustServerCertificate=true";

            conn = DriverManager.getConnection(connectionURL, userName, password);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

}
