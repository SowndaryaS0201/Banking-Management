package dao;

import util.DBConnection;
import java.sql.*;

public class SecurityLogDAO {

    public void logActivity(int userId, String activity, String status) throws Exception {

        Connection con = DBConnection.getConnection();

        String sql = "INSERT INTO security_logs(user_id,activity,status) VALUES(?,?,?)";
        PreparedStatement ps = con.prepareStatement(sql);

        ps.setInt(1, userId);
        ps.setString(2, activity);
        ps.setString(3, status);

        ps.executeUpdate();
        con.close();
    }
}
