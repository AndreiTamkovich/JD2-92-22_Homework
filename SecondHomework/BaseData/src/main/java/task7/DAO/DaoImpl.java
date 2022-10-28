package task7.DAO;

import my.DataSourse.MySQLJDBCDataSource;
import task7.DTO.Expense;
import task7.DTO.Receiver;

import java.sql.*;
import java.util.ArrayList;

public class DaoImpl implements Dao {

    private MySQLJDBCDataSource dataSource;
    private static DaoImpl instance;

    private DaoImpl() throws ClassNotFoundException {
        this.dataSource = new MySQLJDBCDataSource();
    }

    public static DaoImpl getDao() throws ClassNotFoundException {
        if (instance == null) {
            instance = new DaoImpl();
        }
        return instance;
    }

    @Override
    public Receiver getReceiver(int num) {
        Receiver receiver = new Receiver();
        try (final Connection connection = dataSource.getConnection()) {
            final Statement statement = connection.createStatement();
            final ResultSet resultSet = statement.executeQuery("SELECT * " +
                    "FROM homework2.receivers WHERE num=" + num);
            while (resultSet.next()) {
                receiver.setNum(resultSet.getInt("num"));
                receiver.setName(resultSet.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return receiver;
    }

    @Override
    public ArrayList<Receiver> getReceivers() {
        ArrayList<Receiver> receivers = new ArrayList<>();
        try (final Connection connection = dataSource.getConnection()) {
            final Statement statement = connection.createStatement();
            final ResultSet resultSet = statement.executeQuery("SELECT * " +
                    "FROM receivers ORDER BY num");
            while (resultSet.next()) {
                final Receiver receiver = new Receiver();
                receiver.setNum(resultSet.getInt("num"));
                receiver.setName(resultSet.getString("name"));
                receivers.add(receiver);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return receivers;
    }

    @Override
    public Expense getExpense(int num) {
        Expense expense = new Expense();
        try (final Connection connection = dataSource.getConnection()) {
            final Statement statement = connection.createStatement();
            final ResultSet resultSet = statement.executeQuery("SELECT * " +
                    "FROM homework2.expenses WHERE num=" + num);
            while (resultSet.next()) {
                expense.setNum(resultSet.getInt("num"));
                expense.setPaydate(resultSet.getString("paydate"));
                expense.setReceiver(resultSet.getInt("receiver"));
                expense.setPrice(resultSet.getFloat("price"));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return expense;
    }

    @Override
    public ArrayList<Expense> getExpenses() {
        ArrayList<Expense> expenses = new ArrayList<>();
        try (final Connection connection = dataSource.getConnection()) {
            final Statement statement = connection.createStatement();
            final ResultSet resultSet = statement.executeQuery("SELECT * " +
                    "FROM expenses");
            while (resultSet.next()) {
                final Expense expense = new Expense();
                expense.setNum(resultSet.getInt("num"));
                expense.setPaydate(resultSet.getString("paydate"));
                expense.setReceiver(resultSet.getInt("receiver"));
                expense.setPrice(resultSet.getFloat("price"));
                expenses.add(expense);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return expenses;
    }

    @Override
    public int addReceiver(Receiver receiver) {
        int result = 0;
        try (final Connection connection = dataSource.getConnection()) {
            String query = "INSERT INTO homework2.receivers (name) " +
                    "SELECT * FROM (SELECT ? AS name) AS temp " +
                    "WHERE NOT EXISTS (SELECT name FROM homework2.receivers" +
                    " WHERE name = ?) LIMIT 1";
            PreparedStatement pStatement = connection.prepareStatement(query);
            pStatement.setString(1, receiver.getName());
            pStatement.setString(2, receiver.getName());
            result = pStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public int addExpense(Expense expense) {
        int result = 0;
        try (final Connection connection = dataSource.getConnection()) {
            String query = "INSERT INTO " + "homework2.expenses (paydate, " +
                    "receiver, value) VALUES (?, ?, ?)";
            PreparedStatement pStatement = connection.prepareStatement(query);
            pStatement.setString(1, expense.getPaydate());
            pStatement.setInt(2, expense.getReceiver());
            pStatement.setFloat(3, expense.getPrice());
            result = pStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}
