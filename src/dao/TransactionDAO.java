package dao;

import java.sql.*;
import model.Transaction;
import util.DBConnection;

public class TransactionDAO {

    private static TransactionDAO instance;

    private TransactionDAO() {}

    public static TransactionDAO getInstance() {
        if (instance == null) {
            instance = new TransactionDAO();
        }
        return instance;
    }

public void recordTransaction(Transaction txn) throws Exception {

    Connection con = DBConnection.getConnection();

    String sql = "INSERT INTO transactions(account_id,type,amount,channel) VALUES(?,?,?,?)";
    PreparedStatement ps = con.prepareStatement(sql);

    ps.setInt(1, txn.getAccountId());
    ps.setString(2, txn.getType());
    ps.setDouble(3, txn.getAmount());
    ps.setString(4, txn.getChannel());

    ps.executeUpdate();
}

    public void getTransactionsByAccount(int accountId) throws Exception {
        Connection con = DBConnection.getConnection();

        String sql = "SELECT * FROM transactions WHERE account_id = ? ORDER BY transaction_date DESC";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, accountId);

        ResultSet rs = ps.executeQuery();

        System.out.println("---- Transaction History ----");

        while (rs.next()) {
            System.out.println(
                "Type: " + rs.getString("type") +
                " | Amount: " + rs.getDouble("amount") +
                " | Channel: " + rs.getString("channel") +
                " | Date: " + rs.getTimestamp("transaction_date")
            );
        }

        con.close();
    }
}
