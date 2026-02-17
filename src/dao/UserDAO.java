package dao;
import java.sql.*;
import model.User;
import util.DBConnection;

public class UserDAO {

    private static UserDAO instance;

    private UserDAO() {}

    public static UserDAO getInstance() {
        if (instance == null) {
            instance = new UserDAO();
        }
        return instance;
    }

    public int createUser(User user) throws Exception {

    Connection con = DBConnection.getConnection();

    String sql = "INSERT INTO users(user_id,name,email,phone,password,role,status) VALUES(?,?,?,?,?,?,?)";
    PreparedStatement ps = con.prepareStatement(sql);

ps.setInt(1, user.getUserId());
ps.setString(2, user.getName());
ps.setString(3, user.getEmail());
ps.setString(4, "9999999999");   // dummy phone
ps.setString(5, user.getPassword());  // THIS STORES PIN
ps.setString(6, user.getRole());
ps.setString(7, user.getStatus());


    ps.executeUpdate();
    con.close();

    return user.getUserId();
}

public String validateLogin(int userId, String pin) throws Exception {
    Connection con = DBConnection.getConnection();

    String sql = "SELECT role FROM users WHERE user_id=? AND password=?";
    PreparedStatement ps = con.prepareStatement(sql);

    ps.setInt(1, userId);
    ps.setString(2, pin);

    ResultSet rs = ps.executeQuery();

    if (rs.next()) {
        return rs.getString("role");
    }

    return null;
}


public String getUserPin(int userId) throws Exception {
    Connection con = DBConnection.getConnection();

    String sql = "SELECT password FROM users WHERE user_id=?";
    PreparedStatement ps = con.prepareStatement(sql);
    ps.setInt(1, userId);

    ResultSet rs = ps.executeQuery();

    if (rs.next()) {
        return rs.getString("password");
    }

    return null;
}





    public void blockUser(int userId) throws Exception {
        Connection con = DBConnection.getConnection();

        String sql = "UPDATE users SET status = 'BLOCKED' WHERE user_id = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, userId);

        ps.executeUpdate();
        con.close();
    }

    public void unblockUser(int userId) throws Exception {
        Connection con = DBConnection.getConnection();

        String sql = "UPDATE users SET status = 'ACTIVE' WHERE user_id = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, userId);

        ps.executeUpdate();
        con.close();
    }

    public String getUserStatus(int userId) throws Exception {
        Connection con = DBConnection.getConnection();

        String sql = "SELECT status FROM users WHERE user_id = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, userId);

        ResultSet rs = ps.executeQuery();

        String status = "ACTIVE";
        if (rs.next()) {
            status = rs.getString("status");
        }

        con.close();
        return status;
    }
}
