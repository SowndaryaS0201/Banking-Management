package dao;

import model.InternationalWire;
import util.DBConnection;
import java.sql.*;

public class WireTransferDAO {

    public void saveWireTransfer(InternationalWire wire) throws Exception {

        Connection con = DBConnection.getConnection();

        String sql = "INSERT INTO international_wires(sender_account_id,receiver_bank,receiver_account,swift_code,country,amount,status) VALUES(?,?,?,?,?,?,?)";

        PreparedStatement ps = con.prepareStatement(sql);

        ps.setInt(1, wire.getSenderAccountId());
        ps.setString(2, wire.getReceiverBank());
        ps.setString(3, wire.getReceiverAccount());
        ps.setString(4, wire.getSwiftCode());
        ps.setString(5, wire.getCountry());
        ps.setDouble(6, wire.getAmount());
        ps.setString(7, wire.getStatus());

        ps.executeUpdate();
        con.close();
    }
}
