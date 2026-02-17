package dao;

import model.SupportTicket;
import util.DBConnection;
import java.sql.*;

public class SupportTicketDAO {

    public void createTicket(SupportTicket ticket) throws Exception {

        Connection con = DBConnection.getConnection();

        String sql = "INSERT INTO support_tickets(user_id,issue_type,description,channel,status) VALUES(?,?,?,?,?)";
        PreparedStatement ps = con.prepareStatement(sql);

        ps.setInt(1, ticket.getUserId());
        ps.setString(2, ticket.getIssueType());
        ps.setString(3, ticket.getDescription());
        ps.setString(4, ticket.getChannel());
        ps.setString(5, ticket.getStatus());

        ps.executeUpdate();
        con.close();
    }
}
