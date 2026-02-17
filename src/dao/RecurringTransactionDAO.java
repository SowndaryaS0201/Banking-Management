package dao;

import model.RecurringTransaction;
import util.DBConnection;
import java.sql.*;

public class RecurringTransactionDAO {

    public void createRecurringPayment(RecurringTransaction rt) throws Exception {

        Connection con = DBConnection.getConnection();

        String sql = "INSERT INTO recurring_transactions(account_id,amount,frequency,next_run_date,status) VALUES(?,?,?,?,?)";
        PreparedStatement ps = con.prepareStatement(sql);

        ps.setInt(1, rt.getAccountId());
        ps.setDouble(2, rt.getAmount());
        ps.setString(3, rt.getFrequency());
        ps.setDate(4, rt.getNextRunDate());
        ps.setString(5, rt.getStatus());

        ps.executeUpdate();
        con.close();
    }
}
