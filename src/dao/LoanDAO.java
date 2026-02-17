package dao;

import model.LoanApplication;
import util.DBConnection;
import java.sql.*;

public class LoanDAO {

    public void applyLoan(LoanApplication loan) throws Exception {

        Connection con = DBConnection.getConnection();

        String sql = "INSERT INTO loan_applications(user_id,loan_type,amount,status) VALUES(?,?,?,?)";
        PreparedStatement ps = con.prepareStatement(sql);

        ps.setInt(1, loan.getUserId());
        ps.setString(2, loan.getLoanType());
        ps.setDouble(3, loan.getAmount());
        ps.setString(4, loan.getStatus());

        ps.executeUpdate();
        con.close();
    }
}
