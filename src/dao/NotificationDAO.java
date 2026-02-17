package dao;

import model.Notification;
import util.DBConnection;
import java.sql.*;

public class NotificationDAO {

    public void saveNotification(Notification notification) throws Exception {

        Connection con = DBConnection.getConnection();

        String sql = "INSERT INTO notifications(account_id,message,type) VALUES(?,?,?)";
        PreparedStatement ps = con.prepareStatement(sql);

        ps.setInt(1, notification.getAccountId());
        ps.setString(2, notification.getMessage());
        ps.setString(3, notification.getType());

        ps.executeUpdate();
        con.close();
    }
}
