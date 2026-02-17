package dao;

import model.AuditLog;
import util.DBConnection;
import java.sql.*;

public class AuditLogDAO {

    public void saveAudit(AuditLog log) throws Exception {

        Connection con = DBConnection.getConnection();

        String sql = "INSERT INTO audit_logs(user_id,action,description) VALUES(?,?,?)";
        PreparedStatement ps = con.prepareStatement(sql);

        ps.setInt(1, log.getUserId());
        ps.setString(2, log.getAction());
        ps.setString(3, log.getDescription());

        ps.executeUpdate();
        con.close();
    }
}
