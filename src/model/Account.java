package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import util.DBConnection;

public class Account {
    private int accountId;
    private int userId;
    private String accountType;
    private double balance;

    public Account(int userId, String accountType, double balance) {
        this.userId = userId;
        this.accountType = accountType;
        this.balance = balance;
    }

    public int getUserId() { return userId; }
    public String getAccountType() { return accountType; }
    public double getBalance() { return balance; }

    public void depositAmount(int accountId, double amount) throws Exception {
    Connection con = DBConnection.getConnection();

    String sql = "UPDATE accounts SET balance = balance + ? WHERE account_id = ?";
    PreparedStatement ps = con.prepareStatement(sql);

    ps.setDouble(1, amount);
    ps.setInt(2, accountId);

    ps.executeUpdate();
    con.close();
}

public double getBalance(int accountId) throws Exception {
    Connection con = DBConnection.getConnection();

    String sql = "SELECT balance FROM accounts WHERE account_id = ?";
    PreparedStatement ps = con.prepareStatement(sql);
    ps.setInt(1, accountId);

    ResultSet rs = ps.executeQuery();

    double balance = 0;
    if (rs.next()) {
        balance = rs.getDouble("balance");
    }

    con.close();
    return balance;
}

public void withdrawAmount(int accountId, double amount) throws Exception {
    Connection con = DBConnection.getConnection();

    String sql = "UPDATE accounts SET balance = balance - ? WHERE account_id = ?";
    PreparedStatement ps = con.prepareStatement(sql);

    ps.setDouble(1, amount);
    ps.setInt(2, accountId);

    ps.executeUpdate();
    con.close();
}

}
