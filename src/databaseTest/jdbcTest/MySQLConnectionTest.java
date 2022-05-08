package databaseTest.jdbcTest;

import databaseTest.jdbcConnection.MySQLConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MySQLConnectionTest {

    static Connection conn;

    public static void main(String[] args) throws SQLException {

        conn = MySQLConnection.getMySQLConnection();

        // In ra để xem kết nối thành công hay chưa
        System.out.println("Opened connection: " + conn);

        // taọ ra statement để tạo 1 câu lệnh
        Statement statement = conn.createStatement();

        String sql = "cau_lenh";

        // thực thi câu lệnh SQL trả về kết quả query
        ResultSet rs = statement.executeQuery(sql);

        // Duyệt kết quả trả về
        while (rs.next()) {  //vì sao dùng while: vì nó là vòng lặp với đk nhận vào là boolean
            int ID = rs.getInt(1);
            String firstName = rs.getString(2);
            int price = rs.getInt("ten column");
            String lastName = rs.getString("ten column");
        }

        // ĐÓng kết nối
        conn.close();
    }
}
