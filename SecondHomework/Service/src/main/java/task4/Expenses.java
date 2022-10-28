package task4;

import my.DataSourse.MySQLJDBCDataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Expenses {
    private MySQLJDBCDataSource dataSource;

    {
        try {
            dataSource = new MySQLJDBCDataSource();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    void create(String[] args) {
        if (args.length == 3) {
            try {
                final Connection connection = dataSource.getConnection();
                final Statement statement = connection.createStatement();
                String queryRec = "INSERT INTO homework2.receivers (name) " +
                        "SELECT * FROM (SELECT '" + args[1] + "' AS name) AS temp " +
                        "WHERE NOT EXISTS (SELECT name FROM homework2.receivers WHERE name = '" +
                        args[1] + "') LIMIT 1";
                statement.executeUpdate(queryRec);
                String queryExp = "INSERT INTO homework2.expenses (paydate, receiver, price) " +
                        "SELECT '" + args[0] + "', num, " + args[2] + " FROM homework2.receivers " +
                        "WHERE name='" + args[1] + "'";
                statement.executeUpdate(queryExp);
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Ошибка в данных");
        }
    }

    public void readAll() {
        try (final Connection connection = dataSource.getConnection()) {
            final Statement statement = connection.createStatement();
            String query = "SELECT e.num, paydate, name, price " +
                    "FROM homework2.expenses as e, homework2.receivers as rs " +
                    "WHERE receiver=rs.num " +
                    "ORDER BY num";
            ResultSet result = statement.executeQuery(query);
            System.out.println("Таблица расходов:");
            while (result.next()) {
                System.out.println(result.getString("num") + " " +
                        result.getString("paydate") + " " +
                        result.getString("name") + " " +
                        result.getString("price"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
