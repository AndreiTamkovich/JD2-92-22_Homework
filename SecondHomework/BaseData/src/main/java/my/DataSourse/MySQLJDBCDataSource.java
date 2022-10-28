package my.DataSourse;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLJDBCDataSource {
    public MySQLJDBCDataSource() throws ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/homework2?serverTimezone=UTC",
                "root",
                "root"
        );
    }
}
