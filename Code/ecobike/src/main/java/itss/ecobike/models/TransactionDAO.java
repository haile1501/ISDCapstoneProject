package itss.ecobike.models;

import itss.ecobike.utils.DBUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TransactionDAO {

    public static int insertTransaction(int rental_id, double amount, String type) throws SQLException, ClassNotFoundException {
        int status = 1;
        String insertStmt = "INSERT INTO \"Transaction\"(rental_id, amount, status, type, transaction_time) " +
                "VALUES (" + rental_id + ", " + amount + ", " + status + ", '" + type + "', current_timestamp);";
        try {
            return DBUtil.dbExecuteUpdateWithReturn(insertStmt);
        } catch (SQLException e) {
            System.out.print("Error occurred while INSERT Operation: " + e);
            throw e;
        }
    }

    public static ObservableList<Transaction> getTransaction() throws SQLException, ClassNotFoundException {
        String selectStmt = "SELECT * FROM \"Transaction\"";
        try {
            ResultSet transactions = DBUtil.dbExecuteQuery(selectStmt);
            return getTransactionList(transactions);
        } catch (SQLException e) {
            System.out.println("SQL select operation has failed: " + e);
            throw e;
        }
    }

    public static ObservableList<Transaction> getTransactionByRentalId(int rentalId) throws SQLException, ClassNotFoundException {
        String selectStmt = "SELECT * FROM \"Transaction\" WHERE rental_id = " + rentalId;
        try {
            ResultSet transactions = DBUtil.dbExecuteQuery(selectStmt);
            return getTransactionList(transactions);
        } catch (SQLException e) {
            System.out.println("SQL select operation has failed: " + e);
            throw e;
        }
    }

    public static ObservableList<Transaction> getTransactionByTransactionId(int transactionId) throws SQLException, ClassNotFoundException {
        String selectStmt = "SELECT * FROM \"Transaction\" WHERE transaction_id = " + transactionId;
        try {
            ResultSet transactions = DBUtil.dbExecuteQuery(selectStmt);
            return getTransactionList(transactions);
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("SQL select operation has failed: " + e);
            throw e;
        }
    }

    private static ObservableList<Transaction> getTransactionList(ResultSet rs) throws SQLException {
        ObservableList<Transaction> transactionsList = FXCollections.observableArrayList();
        while (rs.next()) {
            Transaction transaction = new Transaction(
                    rs.getInt("transaction_id"),
                    rs.getInt("rental_id"),
                    rs.getDouble("amount"),
                    rs.getInt("status"),
                    rs.getString("type"),
                    rs.getTimestamp("transaction_time").toLocalDateTime()
            );
            transactionsList.add(transaction);
        }
        return transactionsList;
    }
}
