package dao;

import model.Admin;
import util.DBConnection;
import java.sql.*;

public class AdminDAO {

    public void createAdmin(Admin admin) throws Exception {
        Connection con = DBConnection.getConnection();

        String sql = "INSERT INTO admins(name,email,password) VALUES(?,?,?)";
        PreparedStatement ps = con.prepareStatement(sql);

        ps.setString(1, admin.getName());
        ps.setString(2, admin.getEmail());
        ps.setString(3, admin.getPassword());

        ps.executeUpdate();
        con.close();
    }
}
