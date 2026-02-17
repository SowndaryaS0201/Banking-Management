package dao;
import java.sql.*;
import model.Account;
import util.DBConnection;

public class AccountDAO {

    private static AccountDAO instance;

    private AccountDAO() {}

    public static AccountDAO getInstance() {
        if (instance == null) {
            instance = new AccountDAO();
        }
        return instance;
    }

    public void createAccount(Account account) throws Exception {
        Connection con = DBConnection.getConnection();

        String sql = "INSERT INTO accounts(user_id,account_type,balance) VALUES(?,?,?)";
        PreparedStatement ps = con.prepareStatement(sql);

        ps.setInt(1, account.getUserId());
        ps.setString(2, account.getAccountType());
        ps.setDouble(3, account.getBalance());

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


    public boolean accountExists(int accountId) throws Exception {
        Connection con = DBConnection.getConnection();

        String sql = "SELECT account_id FROM accounts WHERE account_id = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, accountId);

        ResultSet rs = ps.executeQuery();
        boolean exists = rs.next();

        con.close();
        return exists;
    }

    public int getUserIdByAccountId(int accountId) throws Exception {
        Connection con = DBConnection.getConnection();

        String sql = "SELECT user_id FROM accounts WHERE account_id = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, accountId);

        ResultSet rs = ps.executeQuery();

        int userId = 0;
        if (rs.next()) {
            userId = rs.getInt("user_id");
        }

        con.close();
        return userId;
    }

   public void depositAmount(int accountId, double amount) throws Exception {
    Connection con = DBConnection.getConnection();

    String sql = "UPDATE accounts SET balance = balance + ? WHERE account_id = ?";
    PreparedStatement ps = con.prepareStatement(sql);

    ps.setDouble(1, amount);
    ps.setInt(2, accountId);

    ps.executeUpdate();
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
