package dao;

import model.CreditCardApplication;
import util.DBConnection;
import java.sql.*;

public class CreditCardDAO {

    public void applyCard(CreditCardApplication card) throws Exception {

        Connection con = DBConnection.getConnection();

        String sql = "INSERT INTO credit_card_applications(user_id,card_type,credit_limit,status) VALUES(?,?,?,?)";
        PreparedStatement ps = con.prepareStatement(sql);

        ps.setInt(1, card.getUserId());
        ps.setString(2, card.getCardType());
        ps.setDouble(3, card.getLimit());
        ps.setString(4, card.getStatus());

        ps.executeUpdate();
        con.close();
    }
}
