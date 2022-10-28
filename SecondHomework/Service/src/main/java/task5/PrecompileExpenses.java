package task5;

import my.DataSourse.MySQLJDBCDataSource;

import java.sql.*;

public class PrecompileExpenses {
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
                String queryRec = "INSERT INTO homework2.receivers (name) " +
                        "SELECT * " +
                        "FROM (SELECT ? AS name) AS temp " +
                        "WHERE NOT EXISTS (SELECT name FROM homework2.receivers WHERE name = ?) " +
                        "LIMIT 1";
                PreparedStatement pStatement = connection.prepareStatement(queryRec);
                pStatement.setString(1, args[1]);
                pStatement.setString(2, args[1]);
                pStatement.executeUpdate();
                String queryExp = "INSERT INTO homework2.expenses (paydate, receiver, price) " +
                        "SELECT ?, num, ? " +
                        "FROM homework2.receivers " +
                        "WHERE name=?";
                pStatement = connection.prepareStatement(queryExp);
                pStatement.setString(1, args[0]);
                pStatement.setString(3, args[1]);
                pStatement.setFloat(2, Float.parseFloat(args[2]));
                pStatement.executeUpdate();
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
