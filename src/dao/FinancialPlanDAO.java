package dao;

import model.FinancialPlan;
import util.DBConnection;
import java.sql.*;

public class FinancialPlanDAO {

    public void savePlan(FinancialPlan plan) throws Exception {

        Connection con = DBConnection.getConnection();

        String sql = "INSERT INTO financial_plans(user_id,monthly_income,monthly_expense,monthly_savings) VALUES(?,?,?,?)";
        PreparedStatement ps = con.prepareStatement(sql);

        ps.setInt(1, plan.getUserId());
        ps.setDouble(2, plan.getIncome());
        ps.setDouble(3, plan.getExpense());
        ps.setDouble(4, plan.getSavings());

        ps.executeUpdate();
        con.close();
    }
}
