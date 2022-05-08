package databaseTest.jdbcTest;

import databaseTest.jdbcConnection.MicroSQLConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MicroSQLConnectionTest {

    public static void main(String[] args) throws SQLException {

        // Lấy ra đối tượng Connection kết nối vào Database
        Connection conn = MicroSQLConnection.getSQLSeverConnection();
        System.out.println("Opened Connection: " + conn);

        // taọ ra statement để tạo 1 câu lệnh
        Statement statement = conn.createStatement();

        String sqlSelect = "select id, shop_id, name, discount_type, is_disabled from coupon";
        String sqlUpdate = "update coupon set discount_value=133 where id=3@";

        // thực thi câu lệnh SQL trả về kết quả query
        //ResultSet rs = statement.executeQuery(sql);

        try {
            boolean exe = statement.execute(sqlUpdate);
            System.out.println("exe: " + exe);

            int count = statement.getUpdateCount();
            System.out.println("count: " + count);
        } catch(java.sql.SQLException e) {
            e.printStackTrace();
        }

        ResultSet rs = statement.executeQuery(sqlSelect);

        // Duyệt kết quả trả về
        while (rs.next()) {
            int id = rs.getInt(1);
            int shopID = rs.getInt("shop_id");
            String name = rs.getString("name");
            int isDisable = rs.getInt("is_disabled");

            System.out.println("------------------");
            System.out.println("ID: " + id);
            System.out.println("shopID: " + shopID);
            System.out.println("name: " + name);
            System.out.println("isDisable: " + isDisable);
        }
        conn.close();
        System.out.println("-----Closed Connection-----");
    }
}
